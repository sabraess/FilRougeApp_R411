package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/*
* Author: TORRI Clara
* Modifier par:
* permet de filtrer les annonces
 */

public class FilterActivity extends AppCompatActivity {
    Spinner spinner;
    RadioGroup radioGroup;

    EditText prixMinEditText, prixMaxEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        /*pour retourner à l'accueil*/
        clickPictureHome();

        /*pour voir le panier*/
        clickPictureBasket();

        /*pour se connecter faire attention si deja connecter !!!!!*/
        clickPictureConnection();

        //pour choisir la marque
        spinner = findViewById(R.id.spinner);
        List<String> brands = new ArrayList<>();

        for(Car car : CarsList.getCarsList()){
            if(!brands.contains(car.getBrand())){
                brands.add(car.getBrand());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //pour choisir l'énergie
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.essence) {
                    for(Car car : CarsList.getCarsList()){
                        if(car.getEnergy().equals("essence")){
                            System.out.println(car.getName());
                        }
                    }
                } else if(checkedId == R.id.hybride){
                    for(Car car : CarsList.getCarsList()){
                        if(car.getEnergy().equals("hybride")){
                            System.out.println(car.getName());
                        }
                    }
                }
            }
        });

        //pour choisir le prix
        prixMinEditText = findViewById(R.id.prixMin);
        prixMaxEditText = findViewById(R.id.prixMax);

        //pour choisir la vitesse
        SeekBar vitesseSeekBar = findViewById(R.id.seekbar);
        vitesseSeekBar.setMax(450);
        TextView vitesseTextView = findViewById(R.id.textVitesse);

        vitesseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                vitesseTextView.setText(progressChangedValue + " km/h");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                vitesseTextView.setText(progressChangedValue + " km/h");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                vitesseTextView.setText(progressChangedValue + " km/h");
            }
        });

        //pour rechercher
        clickButtonSearch();
    }


    //action lorsqu'on appuie sur les images
    private void clickPictureHome(){
        ImageView imageRetour = findViewById(R.id.flecheRetour);
        imageRetour.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureBasket(){
        ImageView iconBasket = findViewById(R.id.iconPanier);
        iconBasket.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, BasketActivity.class);
            startActivity(intent);
        });
    }

    private void clickPictureConnection(){
        ImageView imageConnection = findViewById(R.id.iconConnexion);
        imageConnection.setOnClickListener(v -> {
            Intent intent = new Intent(FilterActivity.this, ConnectionActivity.class);
            startActivity(intent);
        });
    }

    private void clickButtonSearch(){
        Button searchButton = findViewById(R.id.button);
        searchButton.setOnClickListener(v -> {
            List<Car> carsFilter = new ArrayList<>();

            //récupérer les valeurs des filtres
            String brand = spinner.getSelectedItem().toString();
            String energy = "";
            if(radioGroup.getCheckedRadioButtonId() == R.id.essence){
                energy = "essence";
            }else{
                energy = "hybride";
            }

            String prixMin = prixMinEditText.getText().toString();
            String prixMax = prixMaxEditText.getText().toString();

            int vitesseMax = Integer.parseInt(((TextView)findViewById(R.id.textVitesse)).getText().toString().split(" ")[0]);

            //filtrer les voitures
            for (Car car : CarsList.getCarsList()) {
                // Filtre par marque
                if (!brand.isEmpty() && !car.getBrand().equals(brand)) {
                    continue; // Ignore cette voiture si elle ne correspond pas à la marque sélectionnée
                }
                // Filtre par type de carburant
                if (!energy.isEmpty() && !car.getEnergy().equals(energy)) {
                    continue; // Ignore cette voiture si elle ne correspond pas au type de carburant sélectionné
                }
                // Filtre par prix
                double prix = car.getPrice();
                if (!prixMin.isEmpty() && prix < Double.parseDouble(prixMin)) {
                    continue; // Ignore cette voiture si son prix est inférieur au prix minimum spécifié
                }
                if (!prixMax.isEmpty() && prix > Double.parseDouble(prixMax)) {
                    continue; // Ignore cette voiture si son prix est supérieur au prix maximum spécifié
                }
                // Filtre par vitesse maximale
                if (car.getMaxSpeed() > vitesseMax) {
                    continue; // Ignore cette voiture si sa vitesse maximale est supérieure à la vitesse maximale spécifiée
                }
                // Ajoutez la voiture filtrée à la liste des voitures filtrées
                carsFilter.add(car);
            }


            Intent intent = new Intent(FilterActivity.this, HomeActivity.class);
            intent.putExtra("carsFilter", carsFilter.toArray(new Car[0]));
            startActivity(intent);

        });
    }
}