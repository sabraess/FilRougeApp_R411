package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/*
* author :
* Modifié par :
* vue pour afficher les détails d'une voiture
*/
public class SelectedCarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_car);

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
}