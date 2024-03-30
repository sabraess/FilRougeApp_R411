package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * Author: TORRI Clara
 * Modifier par:
 * permet de voir le panier
 */

public class BasketActivity extends AppCompatActivity implements TaskbarInterface, Clickable{
    private final String TAG = "Clara et Sabra " + getClass().getSimpleName();
    private BasketAdapter basketAdapter;
    private static final List<CarsList> carsInBasket = ShoppingBasket.getCarsInBasket();
    private TextView totalPrice,numberCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        /*affiche la liste des voitures*/
        basketAdapter = new BasketAdapter(carsInBasket, this);
        ListView listView = findViewById(R.id.listViewPanier);
        listView.setAdapter(basketAdapter);

        /*mettre a jour le nombre de voiture dans le panier*/
        numberCars = findViewById(R.id.quantitePanier);
        updateNumberCars();

        // Gestion du clic sur l'image de la corbeille
        listView.setOnItemClickListener((parent, view, position, id) -> {
            clickPictureBin(position);
        });

        /*pour mettre a jour le prix total du panier*/
        updatePrice();

        /*si on appuie sur le bouton payer ça met un text pour confirmer puis ça enlève tout  */
        Button buttonPaid = findViewById(R.id.payer);
        buttonPaid.setOnClickListener(v -> {
            Toast.makeText(this, "Merci pour votre achat", Toast.LENGTH_SHORT).show();
            clear();
        });


        /*si on clique sur les images*/
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

        System.out.println(TAG + carsInBasket + "liste panier");

    }

    @Override
    public void onClickItem(int itemPosition) {
        Intent intent = new Intent(BasketActivity.this, SelectedCarActivity.class);
        CarsList selectedCar = (CarsList) basketAdapter.getItem(itemPosition);
        intent.putExtra("selectedCar", selectedCar);
        startActivity(intent);
    }

    /*Pour effacer la liste du panier apres avoir appuier sur payer*/
    public void clear(){
        ShoppingBasket.clearBasket();
        basketAdapter.notifyDataSetChanged();
        totalPrice = findViewById(R.id.prixPanier);
        updateNumberCars();
    }

    /*mettre à jour le prix*/
    public void updatePrice() {
        totalPrice = findViewById(R.id.prixPanier);
        int total = ShoppingBasket.getTotalPrice();
        totalPrice.setText(getString(R.string.price, String.valueOf(total)));
    }

    public void clickPictureBin(int itemPosition){
        ImageView imageBin = findViewById(R.id.iconBin);
        imageBin.setOnClickListener(v -> {
            CarsList car = carsInBasket.get(itemPosition);
            carsInBasket.remove(car);
            basketAdapter.notifyDataSetChanged();
            updatePrice();
            updateNumberCars();
        });
    }

    /*afficher nb d'élément dans le fichier*/
    public void updateNumberCars(){
        int nbCars = carsInBasket.size();
        if(nbCars > 0){
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        }else{
            numberCars.setVisibility(View.INVISIBLE);
        }
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