package filrouge.app;
/*
* author : TORRI Clara
* Modifié par : TORRI Clara
*  vue accueil
* */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
* faire une ratingbar COEUR
*
* parcelable car singleton me mettais la liste en double ou triple chiant*/
public class HomeActivity extends AppCompatActivity implements Clickable, PostExecuteActivity<CarsList> {
    private final String TAG = "Clara " + getClass().getSimpleName();
    private CarsAdapter carsAdapter;

    private static final List<CarsList> carsList = new ArrayList<>();
    private static final List<CarsList> displayCars = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String url = "https://raw.githubusercontent.com/sabraess/filrouge/jsonimages/fichierJson.json";
        new HttpAsyncGet<>(url, Car.class,this,new ProgressDialog(this));


        /*si on clique sur connexion*/
        clickPictureConnection();

        /*si on clique sur filtre*/
        clickPictureFilter();

        /*si on clique sur le panier*/
        clickPictureBasket();
    }

    @Override
    public void onClickItem(int itemPosition) {
        CarsList car = carsList.get(itemPosition);
        //Log.d(TAG, "clicked on = " + CarsList.getDisplayCars(itemPosition).getName());

        Intent intent = new Intent(HomeActivity.this, SelectedCarActivity.class);
        intent.putExtra("cars", car);
        startActivity(intent);
    }


    public void onRatingChanged(int itemPosition, float value) {
    }

    private int findIndexInList(int index) {
        CarsList car = displayCars.get(index);
        for(int i = 0; i < carsList.size(); i++){
            if(carsList.get(i).getName().equals(car)){
                return i;
            }
        }
        return -1;
    }

    //action lorsqu'on appuie sur des images
    private void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureFilter(){
        ImageView imageFilter = findViewById(R.id.iconFiltre);
        imageFilter.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FilterActivity.class);
            startActivity(intent);
        });
    }

    //pour fichier Json

    @Override
    public void onPostExecute(List<CarsList> itemList) {
        //création des deux listes
        if (carsList.isEmpty()) {
            carsList.addAll(itemList);
        }

        if (displayCars.isEmpty()) {
            displayCars.addAll(carsList);
        }

        ListView listView = findViewById(R.id.listView);
        carsAdapter = new CarsAdapter(displayCars, this);
        listView.setAdapter(carsAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}