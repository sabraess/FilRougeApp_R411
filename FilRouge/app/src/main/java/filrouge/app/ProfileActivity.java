package filrouge.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;
/*
* auteur : Clara et Sabra
* modifié par : Clara
* vue qui affiche le profil de l'utilisateur grâce a firebase
*/

public class ProfileActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button decoButton;
    TextView emailTV;
    FirebaseUser user;

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
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    /*pour afficher l'email de l'utilisateur*/
    private void setTextUserData(FirebaseUser user){
        if (user != null) {
            emailTV.setText(user.getEmail());
        }
    }
}
