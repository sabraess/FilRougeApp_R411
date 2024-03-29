package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * Author: TORRI Clara
 * Modifier par:
 * permet de voir le panier
 */

public class BasketActivity extends AppCompatActivity implements TaskbarInterface{

    private final String TAG = "Clara et Sabra " + getClass().getSimpleName();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ListView listView = findViewById(R.id.listViewPanier);
        TextView totalPrice = findViewById(R.id.prixPanier);

        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();

        int total = ShoppingBasket.getTotalPrice();

        totalPrice.setText(getString(R.string.price, String.valueOf(total)));





        // Adapter adapter = new A(CarsList.getDisplayCars(), this);
       // listView.setAdapter(carsAdapter);


    }

    //action lorsqu'on appuie sur des images
    public void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}