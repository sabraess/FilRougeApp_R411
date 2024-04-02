package filrouge.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class AvisAdapter extends BaseAdapter {

    private final String TAG = "Clara + Sabra " + getClass().getSimpleName();
    private final List<RatingData> avisList;
    private LayoutInflater mInflater;
    private Clickable callBackActivity;

    public AvisAdapter(Context context, List<RatingData> avisList) {
        this.avisList = avisList;
        this.mInflater = LayoutInflater.from(CarsApp.getContext());
//        this.callBackActivity = clickable;
    }

    @Override
    public int getCount() {
        return avisList.size();
    }

    @Override
    public Object getItem(int position) {
        return avisList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ConstraintLayout layoutItem;
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.avis_list_layout, parent, false);

        // Récupérer l'avis à la position donnée
        RatingData avis = avisList.get(position);

        // Récupérer les vues de la mise en page
        TextView textViewEmail = layoutItem.findViewById(R.id.textEmail);
        TextView textViewAvis = layoutItem.findViewById(R.id.textAvis);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);

        // Définir les valeurs des vues avec les données de l'avis
        textViewEmail.setText(avis.getEmailUtilisateur());
        textViewAvis.setText(avis.getAvis());
        ratingBar.setRating(avis.getRanking());
        return layoutItem;



    }
}
