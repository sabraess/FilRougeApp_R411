package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;

import java.util.Collections;
import java.util.List;

/*
* author :
* Modifié par : Sabra
* vue pour se connecter
*/

public class ConnectionActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;

    private Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        loginButton = findViewById(R.id.connection);

        /*si on clique sur le textView s'inscrire*/
        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(v -> {
            //lancement de l'activité SignUpActivity
            Intent intent = new Intent(ConnectionActivity.this, SignUpActivity.class);
            startSignInActivity();
            //startActivity(intent);
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
}