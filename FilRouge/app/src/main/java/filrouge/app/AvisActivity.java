package filrouge.app;

import android.app.Activity;
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
import java.util.Objects;

import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Toast;

public class AvisActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;

    private HashMap<String , Object> avis;
    Integer nbrAvis = 0;
    private Button buttonAjouterAvis;
    private EditText commentaire;
    private ListView listeAvis;



    // Déclaration de la liste des avis
    private List<RatingData> avisList = new ArrayList<>();
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

    // ************************************ L'affichage d'avis ************************************************* //

        listeAvis = findViewById(R.id.listViewOpinion);

        // ArrayList<String> list = new ArrayList<>();
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.avis_list_layout, android.R.id.text1, list);

        ArrayList<RatingData> listAvisA = new ArrayList<>();
        AvisAdapter adapterA = new AvisAdapter(this, listAvisA);

        listeAvis.setAdapter(adapterA);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Avis").child(car.getName());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@androidx.annotation.NonNull DataSnapshot dataSnapshot) {
                listAvisA.clear();
                for (DataSnapshot avisSnapshot : dataSnapshot.getChildren()) {


                    String avisSnapshotString = avisSnapshot.getValue().toString().substring(1, avisSnapshot.getValue().toString().length() - 1);
                    // Diviser la chaîne en parties basées sur ","
                    String[] parts = avisSnapshotString.split(", ");

                    // Initialisation des variables pour stocker les données extraites
                    String emailUtilisateur = "";
                    String avisString = "";
                    float ranking = 0;

                    // Parcours des parties pour extraire les clés et les valeurs
                    for (String part : parts) {
                        // Diviser selon "=" (clé - valeur)
                        String[] keyValue = part.split("=");

                        // Récupèrer la clé et la valeur des données que je veuxx
                        String key = keyValue[0];
                        String value = keyValue[1];

                        if (key.equals("Avis")) {
                            avisString = value;
                        } else if (key.equals("EmailUtilisateur")) {
                            emailUtilisateur = value;
                        } else if (key.equals("Ranking")) {
                            ranking = Float.parseFloat(value);
                        }
                    }

                    //  initialiser RatingData avec données réécupérées
                    RatingData avisObj = new RatingData(avisString, emailUtilisateur, ranking);

                    listAvisA.add(avisObj);
                    //}
                }
                adapterA.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

            }
        });

    // ####################################### L'ajout davis #######################################

        avis = new HashMap<>();
        buttonAjouterAvis = findViewById(R.id.buttonCommentaireAvis);
        commentaire = findViewById(R.id.commentaireAvis);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        buttonAjouterAvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentaireString;
                commentaireString = commentaire.getText().toString();
                RatingBar ratingBarAvis = findViewById(R.id.ratingBarAvis);


                float ranking = ratingBarAvis.getRating();

                if (user != null) {
                    if (commentaireString.isEmpty()) {
                        Toast.makeText(AvisActivity.this, "Écrire un avis avant de valider !!", Toast.LENGTH_LONG).show();

                    } else {
                        avis.put("EmailUtilisateur", user.getEmail());
                        avis.put("Avis", commentaireString);
                        avis.put("Ranking", ranking );
                        nbrAvis++;
                        // Générer automatiquement une clé unique pour cet avis
                        // Obtenir une référence à la base de données
                        DatabaseReference avisRef = FirebaseDatabase.getInstance().getReference().child("Avis").child(car.getName());

                        // Générer automatiquement une clé unique pour cet avis
                        // String nouvelAvisKey = avisRef.push().getKey();

                        // Ajouter l'avis sous la clé unique générée
                        avisRef.child(user.getUid()).setValue(avis).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Avis ajouté avec succès
                                        Toast.makeText(AvisActivity.this, "Avis ajouté avec succès !", Toast.LENGTH_LONG).show();
                                        commentaire.setText(null);
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        // Échec de l'ajout de l'avis
                                        Toast.makeText(AvisActivity.this, "Erreur lors de l'ajout de l'avis : " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                    }
                } else {
                    Toast.makeText(AvisActivity.this, "Veuillez vous connecter afin d'ajouter un avis", Toast.LENGTH_LONG).show();

                }
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
    }

    public void clickPictureConnection(){

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        ImageView imageConnection = findViewById(R.id.iconConnection);

        if (user == null ) {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(AvisActivity.this, ConnectionActivity.class);
                startActivity(intent);
            });

        }
        else {
            imageConnection.setOnClickListener(v -> {
                Intent intent = new Intent(AvisActivity.this, ProfileActivity.class);
                startActivity(intent);
            });

        }
    }

    public void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconBasket);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(AvisActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    public void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.returnHome);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(AvisActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}
