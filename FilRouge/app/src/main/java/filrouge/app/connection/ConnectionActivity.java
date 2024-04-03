package filrouge.app.connection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Collections;
import java.util.List;

import filrouge.app.main.HomeActivity;
import filrouge.app.R;
import filrouge.app.main.TaskbarInterface;
import filrouge.app.basket.BasketActivity;
import filrouge.app.basket.ShoppingBasket;
import filrouge.app.cars.CarsList;

/*
* auteur : clara et sabra
* Modifié par : clara et Sabra
* vue pour se connecter avec un email et un mot de passe grace à firebase
*/

public class ConnectionActivity extends AppCompatActivity implements TaskbarInterface {
    FirebaseUser user;
    private static final int RC_SIGN_IN = 123;
    private Button loginButton;
    private EditText emailInput, passwordInput;
    FirebaseAuth mAuth;

    /* methode qui sert à vérifier si l'utilisateur est déjà connecté */
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        mAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.connection);
        emailInput = findViewById(R.id.email);
        passwordInput = findViewById(R.id.password);
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


        /*si on clique sur le textView s'inscrire*/
        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(v -> {
            //lancement de l'activité SignUpActivity
            startSignInActivity();
        });

        /*si on clique sur le bouton connexion*/
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();

                /*si l'email ou le mot de passe est vide*/
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ConnectionActivity.this, "Entrez un email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ConnectionActivity.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*connexion avec l'email et le mot de passe*/
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                /*si la connexion est réussie*/
                                if (task.isSuccessful()) {
                                    Toast.makeText(ConnectionActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                } else { /*si la connexion est échouée*/
                                    Toast.makeText(ConnectionActivity.this, "Connexion echouée", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        /*methode pour les icones de la barre de tâches*/
        clickPictureHome();
        clickPictureBasket();
        clickPictureConnection();

        /*met à jour le nombre de voitures dans le panier*/
        updateNumberCars();
    }

    /*methode pour lancer l'activité de connexion*/
    private void startSignInActivity(){
        List<AuthUI.IdpConfig> providers =
                Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        /*lancement de l'activité de connexion*/
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }

    /*methode pour les icones de la barre de tâches*/
    /*return a l'accueil*/
    @Override
    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(ConnectionActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    /*Amène au panier*/
    @Override
    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(ConnectionActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    /*verife si la personne est connecter ou pas si elle est pas connecté amène ConnectionActivity sinon
    * amène à ProfilActivity*/
    @Override
    public void clickPictureConnection(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(ConnectionActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(ConnectionActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    /*modifie le nombre d'élément dans le panier*/
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