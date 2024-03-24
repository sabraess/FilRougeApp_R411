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

        TextView carSelectedName = findViewById(R.id.carSelectedName);
        // Récupération de l'image View de l'activity
        ImageView imageViewAnimation = findViewById(R.id.imageViewCarTurningAround);
        carSelectedName.setText(car.getName());
        Picasso.get().load(car.getPicture()).into(imageViewAnimation);
        Log.d(TAG, "car = " + car.getPicture());
        // Ajout de l'animation
        // imageViewAnimation.setBackgroundResource(R.drawable.car_turning_animation);
        // AnimationDrawable anim= (AnimationDrawable)imageViewAnimation.getBackground();
        // anim.start();


        Car carSelected = new Car();
/*
        displayedCars.getInstance().get(index);

        TextView name = findViewById(R.id.characterName);
        name.setText(character.getName());
        TextView description = findViewById(R.id.description);
        description.setText(character.getDescription());
        ImageView picture = findViewById(R.id.picture);
        picture.setImageResource(character.getPicture());

        Intent intent = getIntent();
        Car carSelectedD = intent.getParcelableExtra(getString(R.string.CHARACTER_KEY));

        Log.d(TAG, "character = " + currentCharacter);
        TextView name = findViewById(R.id.characterName);
        ToggleButton favorite = findViewById(R.id.characterFavorite);
        ImageView picture = findViewById(R.id.characterPicture);
        TextView description = findViewById(R.id.characterDescription);
        name.setText(currentCharacter.getName());
        favorite.setChecked( currentCharacter.isFavorite() );
        picture.setImageResource(currentCharacter.getPicture());
        description.setText(currentCharacter.getDescription());
*/
        /*
        Intent intent = getIntent();
        int index = intent.getIntExtra("cars",0);
        Cars currentCar = CarsList.getDisplayCars(index);

        Exemple :
        TextView name = findViewById(R.id.characterName);
        ToggleButton favorite = findViewById(R.id.characterFavorite);
        ImageView picture = findViewById(R.id.characterPicture);
        TextView description = findViewById(R.id.characterDescription);
        name.setText(currentCharacter.getName());
        favorite.setChecked( currentCharacter.isFavorite() );
        picture.setImageResource(currentCharacter.getPicture());
        description.setText(currentCharacter.getDescription());
        */
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