package filrouge.app;

import android.app.ProgressDialog;
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

public class BasketActivity extends AppCompatActivity implements TaskbarInterface, PostExecuteActivity<CarsList>{

    private final String TAG = "Clara et Sabra " + getClass().getSimpleName();
    private BasketAdapter basketAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        String url = "https://raw.githubusercontent.com/sabraess/filrouge/jsonimages/fichierJson.json";
        new HttpAsyncGet<>(url, Car.class,this,new ProgressDialog(this));



        TextView totalPrice = findViewById(R.id.prixPanier);
        int total = ShoppingBasket.getTotalPrice();
        totalPrice.setText(getString(R.string.price, String.valueOf(total)));

        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();


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

    @Override
    public void onPostExecute(List<CarsList> itemList) {
        ListView listView = findViewById(R.id.listViewPanier);
        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
        basketAdapter = new BasketAdapter(carsInBasket);
        listView.setAdapter(basketAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}