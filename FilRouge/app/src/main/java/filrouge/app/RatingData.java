package filrouge.app;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RatingData {

    private static final String TAG = "clara et sabra";
    private final DatabaseReference databaseReference;
    private PostExecuteActivity ratingCallback;

    public RatingData(PostExecuteActivity callBack) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.ratingCallback = callBack;
        createListener();
    }

    public void createListener() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<RatingData> ratings = new ArrayList<>(); //liste des notes des produits

                //on parcourt DataSnapshot pour récupérer les notes,avis de chaque produit
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.getKey();
                    RatingData rating = snapshot.getValue(RatingData.class);
                    float rate = snapshot.child("rate").getValue(Float.class);
                    String comment = snapshot.child("comment").getValue(String.class);

                    ratings.add(rating);
                }

                ratingCallback.onProductRate(ratings);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, "onCancelled", databaseError.toException());
            }
        });
    }

}
