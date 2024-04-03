package filrouge.app.basket;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import filrouge.app.R;
import filrouge.app.connection.ConnectionActivity;
import filrouge.app.connection.ProfileActivity;
import filrouge.app.main.HomeActivity;

public class NotificationActivity extends AppCompatActivity {

    TextView textViewVoitures;
    TextView textViewRemerciement;

    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        textViewRemerciement = findViewById(R.id.textViewRemerciement);
        textViewRemerciement.setText("Merci beaucoup pour votre achat !!");
        textViewVoitures = findViewById(R.id.textViewData);

        String nomVoitures = getIntent().getStringExtra("nomVoitures");

        // textViewVoitures.setText(nomVoitures);

        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();
        /*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        */

    }

    public void clickPictureConnection(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(NotificationActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(NotificationActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    public void clickPictureBasket() {
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureHome() {
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

}