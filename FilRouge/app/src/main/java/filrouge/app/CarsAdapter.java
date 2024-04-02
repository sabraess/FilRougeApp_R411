package filrouge.app;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/*
* auteur : ESSALAH Sabra et TORRI Clara
* modifié par : clara
* classe qui permet de créer un adapter pour afficher la liste des voitures
*/

public class CarsAdapter extends BaseAdapter {
    private final String TAG = "Clara + Sabra " + getClass().getSimpleName();
    private final List<CarsList> carsList;
    private LayoutInflater mInflater;
    private Clickable callBackActivity;

    public CarsAdapter(List<CarsList> carsList, Clickable clickable) {
        this.carsList = carsList;
        this.mInflater = LayoutInflater.from(CarsApp.getContext());
        this.callBackActivity = clickable;
    }

    @Override
    public int getCount() {
        return carsList.size();
    }

    @Override
    public Object getItem(int position) {
        return carsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.ratingbar_layout, parent, false);

        TextView name = layoutItem.findViewById(R.id.nameCar);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);
        ImageView picture = layoutItem.findViewById(R.id.pictureCar);

        name.setText(carsList.get(position).getName());
        Picasso.get().load(carsList.get(position).getPicture()).into(picture);

        setRatingWithFirebase();
        ratingBar.setRating(carsList.get(position).getRating());

      /*  ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> {
            callBackActivity.onRatingChanged(position, value);
        });*/

        layoutItem.setOnClickListener(click -> {
            callBackActivity.onClickItem(position);
        });
        return layoutItem;

    }

    private void setRatingWithFirebase () {
        DatabaseReference ratingsRef = FirebaseDatabase.getInstance().getReference().child("Avis");

        ratingsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (CarsList car : carsList) {
                    String carName = car.getName();
                    List<Float> ratings = new ArrayList<>();

                    for (DataSnapshot carSnapshot : dataSnapshot.getChildren()) {
                        if (carSnapshot.getKey().equals(carName)) {
                            for (DataSnapshot ratingSnapshot : carSnapshot.getChildren()) {
                                float rating = ratingSnapshot.child("Ranking").getValue(Float.class);
                                ratings.add(rating);
                            }
                        }
                    }

                    // Calculer la moyenne des notes
                    float totalRating = 0;
                    for (float rating : ratings) {
                        totalRating += rating;
                    }
                    float averageRating = ratings.isEmpty() ? 0 : totalRating / ratings.size();

                    // Mettre à jour la note moyenne de la voiture dans la liste des voitures
                    car.setRating(averageRating);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs ici
            }
        });
    }

}
