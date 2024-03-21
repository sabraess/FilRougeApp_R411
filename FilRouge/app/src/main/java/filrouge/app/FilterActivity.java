package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/*
* Author: TORRI Clara
* Modifier par:
* permet de filtrer les annonces
 */

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        /*pour retourner à l'acceuil*/
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        /*pour voir le panier*/
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, BasketActivity.class);
            startActivity(intent);
        });

        /*pour se connecter faire attention si deja connecter !!!!!*/
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });


    }
}