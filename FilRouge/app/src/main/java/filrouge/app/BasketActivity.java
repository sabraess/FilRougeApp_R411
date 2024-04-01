package filrouge.app;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

/**
 * Auteur: TORRI Clara et ESSALAH Sabra
 * Modifié par : clara
 * permet de voir le panier
 */

public class BasketActivity extends AppCompatActivity implements TaskbarInterface, Clickable {
    private final String TAG = "Clara et Sabra " + getClass().getSimpleName();
    private CarsAdapter adapter;
    private List<CarsList> carsInBasket;
    private TextView numberCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        carsInBasket = ShoppingBasket.getCarsInBasket();
        /*affiche la liste des voitures*/

        ListView listView = findViewById(R.id.listViewPanier);
        adapter = new CarsAdapter(carsInBasket, this);
        listView.setAdapter(adapter);

        //aide
        animationHelp();

        /*mettre a jour le nombre de voiture dans le panier*/
        numberCars = findViewById(R.id.nbCarInBasket);
        updateNumberCars();

        /*pour mettre a jour le prix total du panier*/
        updatePrice();

        /*si on appuie sur le bouton payer ça met un text pour confirmer puis ça enlève tout  */
        Button buttonPaid = findViewById(R.id.buttonBuy);
        buttonPaid.setOnClickListener(v -> {
            Toast.makeText(this, "Merci pour votre achat", Toast.LENGTH_SHORT).show();
            clear();
        });

        /*si on clique sur les images*/
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

    }

    @Override
    public void onClickItem(int itemPosition) {
        ShoppingBasket.removeCar(itemPosition);
        updatePrice();
        adapter.notifyDataSetChanged();
        updateNumberCars();
    }

    /*Pour effacer la liste du panier apres avoir appuier sur payer*/
    public void clear() {
        ShoppingBasket.clearBasket();
        adapter.notifyDataSetChanged();
        updatePrice();
        updateNumberCars();
    }

    /*mettre à jour le prix*/
    public void updatePrice() {
        TextView totalPrice = findViewById(R.id.priceBasket);
        int total = ShoppingBasket.getTotalPrice();
        totalPrice.setText(getString(R.string.price, String.valueOf(total)));
    }


    /*afficher nb d'élément dans le fichier*/
    public void updateNumberCars() {
        int nbCars = carsInBasket.size();
        if (nbCars > 0) {
            numberCars.setText(String.valueOf(nbCars));
            numberCars.setVisibility(View.VISIBLE);
        } else {
            numberCars.setVisibility(View.INVISIBLE);
        }
    }

    //action lorsqu'on appuie sur des images
    public void clickPictureConnection() {
        ImageView imageConnection = findViewById(R.id.iconConnection);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureBasket() {
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureHome() {
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(BasketActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    /*fait l'animation du bouton help et affiche l'aide*/
    public void animationHelp() {
        final ImageView imageHelp = findViewById(R.id.iconHelp);
        final ObjectAnimator animator = ObjectAnimator.ofFloat(imageHelp, "translationY", 0f, 10f);
        animator.setDuration(4000);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatCount(ObjectAnimator.INFINITE);

        // Démarrer l'animation
        animator.start();

        imageHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog(animator);
            }
        });
    }

    /*affiche l'aide dans une alert dialog*/
    private void showAlertDialog(final ObjectAnimator animator) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);
        builder.setMessage("Pour supprimer un produit, cliquez sur celui-ci")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Redémarrer l'animation après la fermeture de l'AlertDialog
                        animator.start();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}