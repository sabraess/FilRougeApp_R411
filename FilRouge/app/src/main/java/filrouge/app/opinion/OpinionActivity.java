package filrouge.app.opinion;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.RatingBar;
import android.widget.Toast;

import filrouge.app.basket.BasketActivity;
import filrouge.app.cars.CarsList;
import filrouge.app.connection.ConnectionActivity;
import filrouge.app.connection.ProfileActivity;
import filrouge.app.R;
import filrouge.app.main.HomeActivity;

/*
* auteur : clara et sabra
* modifié par sabra
*Activité pour afficher les avis et ajouter un avis
*/

public class OpinionActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser user;
    private HashMap<String , Object> avis;
    Integer nbrAvis = 0;
    private Button buttonAddOpinion;
    private EditText comment;
    private ListView listeAvis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avis);


        /* Appel des méthodes pour les actions*/
        clickPictureConnection();
        clickPictureBasket();
        clickPictureHome();

        /*Récupération de l'objet CarsList envoyé par l'activité précédente*/
        CarsList car = getIntent().getParcelableExtra("cars");

        /*affichage des avis*/
        listeAvis = findViewById(R.id.listViewOpinion);

        /*Création de la liste des avis*/
        ArrayList<Opinion> listOpinion = new ArrayList<>();
        OpinionAdapter adapter = new OpinionAdapter(this, listOpinion);
        listeAvis.setAdapter(adapter);

        /*Récupération des avis de la base de données*/
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Avis").child(car.getName());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                /*Vider la liste des avis*/
                listOpinion.clear();

                /*Parcours des avis*/
                for (DataSnapshot opinionSnapshot : dataSnapshot.getChildren()) {

                    /*Récupération des données de l'avis*/
                    String opinionSnapshotString = opinionSnapshot.getValue().toString().substring(1, opinionSnapshot.getValue().toString().length() - 1);

                    // Diviser la chaîne en parties basées sur ","
                    String[] parts = opinionSnapshotString.split(", ");

                    // Initialisation des variables pour stocker les données extraites
                    String userEmail = "";
                    String comment = "";
                    float ranking = 0;

                    // Parcours des parties pour extraire les clés et les valeurs
                    for (String part : parts) {
                        // Diviser selon "=" (clé - valeur)
                        String[] keyValue = part.split("=");

                        // Récupèrer la clé et la valeur des données
                        String key = keyValue[0];
                        String value = keyValue[1];

                        if (key.equals("Avis")) {
                            comment = value;
                        } else if (key.equals("EmailUtilisateur")) {
                            userEmail = value;
                        } else if (key.equals("Ranking")) {
                            ranking = Float.parseFloat(value);
                        }
                    }

                    Opinion opinion = new Opinion(comment,userEmail,ranking);
                    listOpinion.add(opinion);

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });

        /*ajout des avis*/
        avis = new HashMap<>();
        buttonAddOpinion = findViewById(R.id.buttonAddOpinion);
        comment = findViewById(R.id.commentOpinion);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        /*Action du bouton pour ajouter un avis*/
        buttonAddOpinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentString;
                commentString = comment.getText().toString();
                RatingBar ratingBarAvis = findViewById(R.id.ratingBarOpinion);
                float ranking = ratingBarAvis.getRating();

                /*Vérification de la connexion de l'utilisateur*/
                if (user != null) {
                    if (commentString.isEmpty()) {
                        Toast.makeText(OpinionActivity.this, "Écrire un avis avant de valider !!", Toast.LENGTH_LONG).show();

                    } else {
                        avis.put("EmailUtilisateur", user.getEmail());
                        avis.put("Avis", commentString);
                        avis.put("Ranking", ranking );
                        nbrAvis++;
                        // Générer automatiquement une clé unique pour cet avis
                        // Obtenir une référence à la base de données
                        DatabaseReference avisRef = FirebaseDatabase.getInstance().getReference().child("Avis").child(car.getName());

                        // Ajouter l'avis sous la clé unique générée
                        avisRef.child(user.getUid()).setValue(avis).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Avis ajouté avec succès
                                        Toast.makeText(OpinionActivity.this, "Avis ajouté avec succès !", Toast.LENGTH_LONG).show();
                                        comment.setText(null);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Échec de l'ajout de l'avis
                                        Toast.makeText(OpinionActivity.this, "Erreur lors de l'ajout de l'avis : " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                } else {
                    Toast.makeText(OpinionActivity.this, "Veuillez vous connecter afin d'ajouter un avis", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    /*Méthode pour les actions de la bar de tâche*/
    /*si l'utilisateur est connecté, l'icône de connexion redirige vers le profil de l'utilisateur
    * sinon redirige vers la page de connexion*/
    public void clickPictureConnection(){
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(OpinionActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });
        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(OpinionActivity.this, ProfileActivity.class);
                startActivity(intent);
            });
        }
    }

    /*redirige vers panier*/
    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(OpinionActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    /*redirige vers la page d'accueil*/
    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(OpinionActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
