package filrouge.app.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import filrouge.app.R;
import filrouge.app.basket.BasketActivity;
import filrouge.app.basket.ShoppingBasket;
import filrouge.app.cars.Car;
import filrouge.app.cars.CarsAdapter;
import filrouge.app.cars.CarsList;
import filrouge.app.cars.SelectedCarActivity;
import filrouge.app.connection.ConnectionActivity;
import filrouge.app.connection.ProfileActivity;

/*
* auteur : TORRI Clara et ESSALAH Sabra
* Modifié par : clara et sabra
* vue qui de l'accueil de l'application elle affiche la liste des voitures et les filtres
*/

public class HomeActivity extends AppCompatActivity implements Clickable, PostExecuteActivity<CarsList> {
    private final String TAG = "Clara et Sabra" + getClass().getSimpleName();
    private static List<CarsList> carsList = new ArrayList<>(); /*liste de base*/
    private static List<CarsList> displayCars = new ArrayList<>();/*liste affiché*/
    private ListView listView;
    private CarsAdapter carsAdapter ;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*pour recuperer l'url des données du json*/
        String url = "https://raw.githubusercontent.com/sabraess/filrouge/jsonimages/fichierJson.json";
        new HttpAsyncGet<>(url, Car.class,this,new ProgressDialog(this));

        /*met a jour le nb de voiture dans le textView du panier*/
        updateNumberCars();

        clickPictureConnection(); /*si on clique sur connexion*/
        clickPictureBasket(); /*si on clique sur le panier*/

        /*pour les filtres*/
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonTous = findViewById(R.id.all);
        radioButtonTous.setChecked(true);
        //pour les energies
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.essence) {
                displayCars.clear();
                for (CarsList car : carsList) {
                    if (car.getEnergy().equals("Essence")) {
                        displayCars.add(car);
                    }
                }
            } else if(checkedId == R.id.hybride){
                displayCars.clear();
                for (CarsList car : carsList) {
                    if (car.getEnergy().equals("Hybride")) {
                        displayCars.add(car);
                    }
                }
            } else {
                displayCars.clear();
                displayCars.addAll(carsList);
            }
            carsAdapter.notifyDataSetChanged();
        });
    }

    /*action lorsqu'on appuie sur une voiture, ça redirige vers l'activité SeletedCarsActivity */
    @Override
    public void onClickItem(int itemPosition) {
        // Récupérer l'élément de la liste de base correspondant à l'élément sélectionné dans la liste affichée
        CarsList car = displayCars.get(itemPosition);

        // Rechercher l'élément correspondant dans la liste de base (carsList) en utilisant son ID
        for (CarsList fullCar : carsList) {
            if (fullCar.getId() == car.getId()) {
                car = fullCar;
            }
        }

        // Rediriger vers l'activité de détails du produit avec l'élément complet 'car'
        Intent intent = new Intent(HomeActivity.this, SelectedCarActivity.class);
        intent.putExtra("cars", car);
        startActivity(intent);
    }

    /*si utilisateur pas connecter on redirige vers la page de connexion
     sinon on redirige vers la page de profil*/
    public void clickPictureConnection(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    /*amène au panier*/
    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    /*affiche la liste des voitures*/
    @Override
    public void onPostExecute(List<CarsList> itemList) {
        //création des deux listes
        /*liste des voitures*/
        if (carsList.isEmpty()) { carsList.addAll(itemList); }

        /*liste des voitures affichaient*/
        if (displayCars.isEmpty()) { displayCars.addAll(carsList);}

        listView = findViewById(R.id.listView);
        carsAdapter = new CarsAdapter(displayCars, this);
        listView.setAdapter(carsAdapter);
    }


    /*met a jour le nb de voiture dans le textView du panier*/
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
}