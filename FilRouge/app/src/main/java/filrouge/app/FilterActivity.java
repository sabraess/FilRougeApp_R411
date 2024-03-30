package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
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
* Author: TORRI Clara
* Modifier par:
* permet de filtrer les annonces
 */

public class FilterActivity extends AppCompatActivity implements TaskbarInterface {
    private Spinner spinner;
    private SeekBar priceSeekBar;

    private final String TAG = "Clara et Sabra" + getClass().getSimpleName();


    private static List<CarsList> carsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        updateNumberCars();

        /*pour retourner à l'accueil*/
        clickPictureHome();

        /*pour voir le panier*/
        clickPictureBasket();

        /*pour se connecter faire attention si deja connecter !!!!!*/
        clickPictureConnection();

        //pour choisir la marque
        spinner = findViewById(R.id.spinner);
        List<String> brands = new ArrayList<>();

        // Récupérer la liste des marques distinctes des voitures
        for (CarsList car : carsList) {
            if (!brands.contains(car.getBrand())) {
                brands.add(car.getBrand());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //pour choisir le prix
        SeekBar priceSeekBar = findViewById(R.id.seekbar);
        priceSeekBar.setMax(3500000);
        TextView priceTextView = findViewById(R.id.textPrice);

        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int price = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                price = progress;
                priceTextView.setText(price + " €");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                priceTextView.setText(price + " €");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                priceTextView.setText(price + " €");
            }
        });

        //pour rechercher
       clickButtonSearch();
    }


    //action lorsqu'on appuie sur les images
    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void updateNumberCars() {
        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
        TextView numberCars = findViewById(R.id.quantitePanier);
        int nbCars = carsInBasket.size();
        if (nbCars > 0) {
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        } else {
            numberCars.setVisibility(View.INVISIBLE);
        }
    }

    private void clickButtonSearch() {
        Button searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(v -> {
            // Récupérer la marque sélectionnée dans le Spinner
            String brand = spinner.getSelectedItem().toString();

            // Récupérer le prix maximum souhaité
            int maxPrice = priceSeekBar.getProgress();

            // Filtrer les voitures
            List<CarsList> filteredCars = filterCars(carsList, brand, maxPrice);

            // Passer les voitures filtrées à HomeActivity
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            intent.putExtra("carsFilter", filteredCars.toArray(new CarsList[0]));
            startActivity(intent);
        });
    }


    public static List<CarsList> filterCars(List<CarsList> cars, String brand, int maxPrice) {
       List<CarsList> filteredCars = new ArrayList<>();
       for (CarsList car : cars) {
           if (brand != null && !brand.isEmpty() && !car.getBrand().equals(brand)) {
               continue;
           }
           if (car.getPrice() > maxPrice) {
               continue;
           }
           filteredCars.add(car);
       }
       return filteredCars;
   }


}