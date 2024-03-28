package filrouge.app;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
* author : ESSALAH Sabra
* Modifié par :
* vue pour afficher les détails d'une voiture
*/
public class SelectedCarActivity extends AppCompatActivity {
    private final String TAG = "Clara " + getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_car);

        // Appel des méthodes pour les actions
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

        int indexCar = getIntent().getIntExtra("cars", 0);
        Car car = CarsList.getDisplayCars(indexCar);

        TextView name = findViewById(R.id.carSelectedName);
        name.setText(car.getName());

        ImageView image = findViewById(R.id.imageViewCarTurningAround);
        Picasso.get().load(car.getPicture()).into(image);
        Log.d(TAG, "car = " + car.getPicture());

        TextView description = findViewById(R.id.carDescription);
        description.setText(car.getDescription());

        TextView price = findViewById(R.id.carPrice);
        price.setText(getString(R.string.price, String.valueOf(car.getPrice())));

        TextView maxSpeed = findViewById(R.id.carMaxSpeed);
        maxSpeed.setText(getString(R.string.vitesse, String.valueOf(car.getMaxSpeed())));

        TextView power = findViewById(R.id.carPuissance);
        power.setText(getString(R.string.power, String.valueOf(car.getPower())));

        TextView energy = findViewById(R.id.carEnergie);
        energy.setText(car.getEnergy());
    }

    //action lorsqu'on appuie sur des images
    private void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(SelectedCarActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }


}