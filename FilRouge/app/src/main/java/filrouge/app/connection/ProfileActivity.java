package filrouge.app.connection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import filrouge.app.main.HomeActivity;
import filrouge.app.R;
import filrouge.app.main.TaskbarInterface;
import filrouge.app.basket.BasketActivity;
import filrouge.app.basket.ShoppingBasket;
import filrouge.app.cars.CarsList;
/*
* auteur : Clara et Sabra
* modifié par : Clara et sabra
* vue qui affiche le profil de l'utilisateur grâce a firebase
*/

public class ProfileActivity extends AppCompatActivity implements TaskbarInterface {
    FirebaseAuth mAuth;
    FirebaseUser user;
    Button decoButton;
    TextView emailTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        decoButton = findViewById(R.id.deconnexion);
        emailTV = findViewById(R.id.email2);
        user = mAuth.getCurrentUser();
        setTextUserData(user);

        /*pour la deconnexion*/
        decoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        clickPictureBasket();
        clickPictureHome();
        clickPictureConnection();
        updateNumberCars();
    }

    /*pour afficher l'email de l'utilisateur*/
    private void setTextUserData(FirebaseUser user){
        if (user != null) {
            emailTV.setText(user.getEmail());
        }
    }

    /*amène a l'accueil*/
    @Override
    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    /*amène au panier*/
    @Override
    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    /*amène à la connexion si utilisateur pas connecter sinon amène au profil*/
    @Override
    public void clickPictureConnection(){

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    /*met a jour le nb de voiture dans le textView du panier*/
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
}
