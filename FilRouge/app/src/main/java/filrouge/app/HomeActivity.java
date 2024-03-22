package filrouge.app;
/*
* author : TORRI Clara
* Modifié par : TORRI Clara
*  vue accueil
* */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements Clickable, PostExecuteActivity<Car> {
    private final String TAG = "HomeActivity";
    private CarsAdapter carsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String url = "https://raw.githubusercontent.com/sabraess/FilRougeApp_R411/fichierJsonImages/fichierJson.json";
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
        int itemIndex = findIndexInList(itemPosition);
        Log.d(TAG, "clicked on = " + CarsList.getDisplayCars(itemPosition).getName());

        Intent intent = new Intent(HomeActivity.this, SelectedCarActivity.class);
        intent.putExtra("cars", itemIndex);
        startActivity(intent);
    }

    /*a revoir cette methode c'est pas la meme avec l'ancienne version*/
    @Override
    public void onRatingChanged(int itemPosition, float value) {
    }

    private int findIndexInList(int index) {
        Car cars = CarsList.getDisplayCars(index);
        for(int i = 0; i < CarsList.getCarsList().size(); i++){
            if(CarsList.getCars(i).getName().equals(cars.getName())){
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
    public void onPostExecute(List<Car> itemList) {
        ListView listView = findViewById(R.id.listView);
        CarsList.getCarsList().addAll(itemList);
        CarsList.getDisplayCars().addAll(itemList);
        carsAdapter = new CarsAdapter(CarsList.getDisplayCars(), this);
        listView.setAdapter(carsAdapter);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }



}