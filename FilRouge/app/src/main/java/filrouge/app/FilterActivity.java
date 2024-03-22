package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

        /*pour retourner à l'accueil*/
        clickPictureHome();

        /*pour voir le panier*/
        clickPictureBasket();

        /*pour se connecter faire attention si deja connecter !!!!!*/
        clickPictureConnection();

        Spinner spinner = findViewById(R.id.spinner);
        List<String> brands = new ArrayList<>();

        for(Car car : CarsList.getCarsList()){
            if(!brands.contains(car.getBrand())){
                brands.add(car.getBrand());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



//EditText prixMinEditText = findViewById(R.id.prixMin);
//EditText prixMaxEditText = findViewById(R.id.prixMax);
//
//String prixMin = prixMinEditText.getText().toString();
//String prixMax = prixMaxEditText.getText().toString();
    }



    //action lorsqu'on appuie sur les images
    private void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }
}