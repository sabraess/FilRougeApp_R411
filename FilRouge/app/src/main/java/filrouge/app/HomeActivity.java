package filrouge.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.List;

/*
* author : TORRI Clara et ESSALAH Sabra
* vue qui de l'acceuil de l'application elle affiche la liste des voitures
*
* on a choisis d'utiliser finalement parcelable car lorsque j'ai commencé le code j'ai
* voulu faire les lien entre activité avec Singleton mais le probleme
* c'est que quand on etait dans d'autre activité et qu'on veut retourner au niveau
* il va créer une nouvelle liste qui va se rajouter a la liste de base ainsi a la fin
* on se retrouve avec une liste dupliquer.
* Alors qu'avec parcelable y a plus ce pobleme vu qu'on gère grace à des listes*/
public class HomeActivity extends AppCompatActivity implements Clickable, PostExecuteActivity<CarsList> {
    private static List<CarsList> carsList = new ArrayList<>(); /*liste de base*/
    private static List<CarsList> displayCars = new ArrayList<>();/*liste affiché*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*pour recuperer l'url des données du json*/
        String url = "https://raw.githubusercontent.com/sabraess/filrouge/jsonimages/fichierJson.json";
        new HttpAsyncGet<>(url, Car.class,this,new ProgressDialog(this));

        /*met a jour le nb de voiture dans le textView du panier*/
        updateNumberCars();


        clickPictureConnection(); /*si on clique sur connexion*/
        clickPictureFilter(); /*si on clique sur filtre*/
        clickPictureBasket(); /*si on clique sur le panier*/
    }

    /*action lorsqu'on appuie sur une voiture, ça redirige vers l'activité SeletedCarsActivity */
    @Override
    public void onClickItem(int itemPosition) {
        CarsList car = carsList.get(itemPosition);
        Intent intent = new Intent(HomeActivity.this, SelectedCarActivity.class);
        intent.putExtra("cars", car);
        startActivity(intent);
    }

    public void onRatingChanged(int itemPosition, float value) {
    }

    //action lorsqu'on appuie sur des images
    public void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureFilter(){
        ImageView imageFilter = findViewById(R.id.iconFiltered);
        imageFilter.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, FilterActivity.class);
            //intent.putExtra("cars", (CarsList) carsList);
            startActivity(intent);
        });
    }

    /*affiche la liste des voitures*/
    @Override
    public void onPostExecute(List<CarsList> itemList) {
        //création des deux listes
        if (carsList.isEmpty()) { carsList.addAll(itemList); }

        if (displayCars.isEmpty()) { displayCars.addAll(carsList);}

        ListView listView = findViewById(R.id.listView);
        CarsAdapter carsAdapter = new CarsAdapter(displayCars, this);
        listView.setAdapter(carsAdapter);

        //list filtré
       /* List<CarsList> filterCars = getIntent().getParcelableArrayListExtra("filteredCars");
        if(filterCars != null && !filterCars.isEmpty()){
            displayCars.clear();
            displayCars.addAll(filterCars);
        }

        carsAdapter.notifyDataSetChanged();*/

    }

    /*met a jour le nb de voiture dans le textView du panier*/
    public void updateNumberCars() {
        List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
        TextView numberCars = findViewById(R.id.quantitePanier);
        int nbCars = carsInBasket.size();
        if (nbCars > 0) {
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        } else {
            numberCars.setVisibility(View.INVISIBLE);
        }
    }


}