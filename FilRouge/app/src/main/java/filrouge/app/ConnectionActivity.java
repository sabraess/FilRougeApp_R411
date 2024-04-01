package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.Collections;
import java.util.List;



/*
* author :
* Modifié par : Sabra
* vue pour se connecter
*/

public class ConnectionActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    // private UserManager userManager = UserManager.getInstance();
    private Button loginButton;
    private EditText emailInput, passwordInput;
    FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
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
        emailInput = findViewById(R.id.email2);
        passwordInput = findViewById(R.id.password);

        /*si on clique sur le textView s'inscrire*/
        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(v -> {
            //lancement de l'activité SignUpActivity
            Intent intent = new Intent(ConnectionActivity.this, SignUpActivity.class);
            startSignInActivity();
            //startActivity(intent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = emailInput.getText().toString();
                password = passwordInput.getText().toString();


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ConnectionActivity.this, "Entrez un email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ConnectionActivity.this, "Entrez un mot de passe", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Toast.makeText(ConnectionActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                    startActivity(intent);
                                    //finish();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(ConnectionActivity.this, "Connexion echouée", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }


    private void startSignInActivity(){

        // Choose authentication providers
        List<AuthUI.IdpConfig> providers =
                Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());

        // Launch the activity
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }

    // Launching Profile Activity
    private void startProfileActivity(){
        // Intent intent = new Intent(this, ProfileActivity.class);
        // startActivity(intent);
    }

}