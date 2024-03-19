package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/*
* author :
* Modifié par :
* vue pour se connecter
*/

public class ConnectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        /*si on clique sur le textView s'inscrire*/
        TextView signUp = findViewById(R.id.signUp);
        signUp.setOnClickListener(v -> {
            //lancement de l'activité SignUpActivity
            Intent intent = new Intent(ConnectionActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

    }
}