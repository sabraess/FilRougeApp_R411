package filrouge.app;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


/*
* author : ESSALAH Sabra et TORRI Clara
* Modifié par : ESSALAH Sabra et TORRI Clara
* vue pour afficher les détails d'une voiture
*/
public class SelectedCarActivity extends AppCompatActivity implements TaskbarInterface{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_car);

        /*Appel de la méthode pour mettre à jour le nombre de voitures dans le panier*/
        updateNumberCars();

        /* Appel des méthodes pour les actions*/
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

        /*Récupération de l'objet CarsList envoyé par l'activité précédente*/
        CarsList car = getIntent().getParcelableExtra("cars");

        /*Affichage des détails de la voiture*/
        TextView name = findViewById(R.id.carSelectedName);
        ImageView image = findViewById(R.id.imageViewCarTurningAround);
        TextView description = findViewById(R.id.carDescription);
        TextView price = findViewById(R.id.carPrice);
        TextView maxSpeed = findViewById(R.id.carMaxSpeed);
        TextView power = findViewById(R.id.carPower);
        TextView energy = findViewById(R.id.carEnergy);


        name.setText(car.getName());
        Picasso.get().load(car.getPicture()).into(image);
        description.setText(car.getDescription());
        price.setText(getString(R.string.price, String.valueOf(car.getPrice())));
        maxSpeed.setText(getString(R.string.speed, String.valueOf(car.getMaxSpeed())));
        power.setText(getString(R.string.power, String.valueOf(car.getPower())));
        energy.setText(getString(R.string.energy, car.getEnergy()));

        /*quand on appuie sur le bouton ajouter au panier*/
        Button buttonBasket = findViewById(R.id.buttonAddBasket);
        buttonBasket.setOnClickListener(v -> {
            clickAddToBasket(car);
        });

    }
    
    /*quand on appuie sur le bouton ajouter au panier*/
    public void clickAddToBasket(CarsList car){
        addToBasket(car);
        updateNumberCars();
    }

    /*ajouter une voiture à la liste  panier*/
    public void addToBasket(CarsList car) {
        ShoppingBasket.addCar(car);
    }

    /*met a jour le textview qui nous permet de voir le nb de voiture dans le panier*/
    @Override
    public void updateNumberCars() {
        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
        TextView numberCars = findViewById(R.id.nbCarInBasket);
        int nbCars = carsInBasket.size();
        if (nbCars > 0) { /*s'il n'y a aucune voiture on affiche rien*/
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        } else { /*sinon on affiche le nombre de voiture*/
            numberCars.setVisibility(View.INVISIBLE);
        }
    }

    /*action lorsqu'on appuie sur des images
    * pour ce connecter
    * pour aller au panier
    * pour revenir à l'accueil
    * */
    public void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnection);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }



}