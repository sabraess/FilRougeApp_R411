package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
* auteur: clara et sabra
* Modifié par: clara
* permet de filtrer les annonces
 */

public class FilterActivity extends AppCompatActivity implements TaskbarInterface {
    private final String TAG = "Clara et Sabra" + getClass().getSimpleName();
    private static List<CarsList> carsList;
    private static List<CarsList> filteredCars;
    private Spinner spinner;
    private RadioGroup radioGroup;
    private EditText minPrice, maxPrice;
    private Button searchBouton;
    private String choiceEnergy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        /*on récupere les éléments */
        spinner = findViewById(R.id.spinner);
        minPrice = findViewById(R.id.editTextMin);
        maxPrice = findViewById(R.id.editTextMax);
        radioGroup = findViewById(R.id.radioGroup);

        /*on recupere la liste des voitures  */
        carsList = getIntent().getParcelableExtra("cars");

        updateNumberCars(); /*met à jour l'affiche du nb d'article dans le panier*/
        clickPictureHome(); /*pour retourner à l'accueil*/
        clickPictureBasket();/*pour voir le panier*/
        clickPictureConnection();/*pour se connecter faire attention si deja connecter !!!!!*/

        /*pour choisir la marque*/
        List<String> brands = new ArrayList<>();

        // Récupérer la liste des marques distinctes des voitures
        for (CarsList car : carsList) {
            brands.add("Toutes les marques");
            if (!brands.contains(car.getBrand())) {
                brands.add(car.getBrand());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        //recuperer la marque choisi
        String brand = spinner.getSelectedItem().toString();

        //pour choisir le prix
        int priceMin = Integer.parseInt(minPrice.getText().toString());
        int priceMax = Integer.parseInt(maxPrice.getText().toString());


        //pour choisir l'energie
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.essence) {
                    for(CarsList car : carsList){
                        if(car.getEnergy().equals("essence")){
                            choiceEnergy = "essence";
                            System.out.println(car.getName());
                        }
                    }
                } else if(checkedId == R.id.hybride){
                    for(CarsList car : carsList){
                        if(car.getEnergy().equals("hybride")){
                            choiceEnergy = "hybride";
                            System.out.println(car.getName());
                        }
                    }
                }
            }
        });

        //pour rechercher
        clickButtonSearch(brand, priceMin, priceMax, choiceEnergy);
    }

    //action lorsqu'on appuie sur les images
    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnection);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    /*met à jour l'affiche du nb d'article dans le panier*/
    @Override
    public void updateNumberCars() {
        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
        TextView numberCars = findViewById(R.id.nbCarInBasket);
        int nbCars = carsInBasket.size();
        if (nbCars > 0) {
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        } else {
            numberCars.setVisibility(View.INVISIBLE);
        }
    }

    /*lorsqu'on appuie sur le bouton rechercher*/
    private void clickButtonSearch(String brand,int priceMin, int priceMax, String choiceEnergy){
        searchBouton.setOnClickListener(v -> {
            // Filtrer les voitures
            filteredCars = filterCars(carsList, brand, priceMin, priceMax, choiceEnergy);

            // Passer les voitures filtrées à HomeActivity
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            intent.putParcelableArrayListExtra("filteredCars", (ArrayList<? extends Parcelable>) filteredCars);
            startActivity(intent);
        });
    }

    /*filtrer les voitures*/
    public static List<CarsList> filterCars(List<CarsList> cars, String brand,int priceMin, int priceMax, String choiceEnergy) {
       for (CarsList car : cars) {
           if (brand != null && !brand.isEmpty() && !car.getBrand().equals(brand)) {
               continue;
           }
           if (priceMin > 0 && car.getPrice() < priceMin) {
               continue;
           }
           if (priceMax > 0 && car.getPrice() > priceMax) {
               continue;
           }
           if (choiceEnergy != null && !choiceEnergy.isEmpty() && !car.getEnergy().equals(choiceEnergy)) {
               continue;
           }

           filteredCars.add(car);
       }
       return filteredCars;
   }


}