package filrouge.app.cars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import java.util.List;

import filrouge.app.connection.ConnectionActivity;
import filrouge.app.main.HomeActivity;
import filrouge.app.connection.ProfileActivity;
import filrouge.app.R;
import filrouge.app.main.TaskbarInterface;
import filrouge.app.basket.BasketActivity;
import filrouge.app.basket.ShoppingBasket;
import filrouge.app.opinion.OpinionActivity;

/*
* auteur : ESSALAH Sabra et TORRI Clara
* Modifié par : Clara et sabra
* vue pour afficher les détails d'une voiture
*/
public class SelectedCarActivity extends AppCompatActivity implements TaskbarInterface {
    FirebaseAuth mAuth;
    FirebaseUser user;
    private Button buttonOpinion;
    private CarsList car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_car);

        /*Récupération de l'objet CarsList envoyé par HomeActivity*/
        car = getIntent().getParcelableExtra("cars");

        /*Bouton pour donner son avis et voir les avis sur la voiture*/
        buttonOpinion = findViewById(R.id.boutonAvis);
        buttonOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectedCarActivity.this, OpinionActivity.class);
                intent.putExtra("cars", car);
                startActivity(intent);
            }
        });

        /*Appel de la méthode pour mettre à jour le nombre de voitures dans le panier*/
        updateNumberCars();

        /*methode pour les icones de la barre de tâches*/
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

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

        /*action lorsqu'on appuie sur le bouton ajouter au panier*/
        Button buttonBasket = findViewById(R.id.buttonAddBasket);
        buttonBasket.setOnClickListener(v -> {
            clickAddToBasket(car);
        });

    }
    
    /*ça appel la méthode pour ajouter au panier et pour modifier le nombre d'élément dans le panier*/
    public void clickAddToBasket(CarsList car){
        addToBasket(car);
        updateNumberCars();
    }

    /*ajouter une voiture à la liste  panier*/
    public void addToBasket(CarsList car) {
        ShoppingBasket.addCar(car);
    }

    /*met à jour le textview qui nous permet de voir le nb de voiture dans le panier*/
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

    /*action lorsqu'on appuie sur des images*/
    /*si l'utilisateur est connecté on le redirige vers son profil
    sinon on le redirige vers la page de connexion*/
    public void clickPictureConnection(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(SelectedCarActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(SelectedCarActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    /*redirige vers le panier*/
    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    /*redirige vers la page d'accueil*/
    public void clickPictureHome(){
        ImageView iconArow = findViewById(R.id.returnHome);
        iconArow.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }



}