package filrouge.app.opinion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import filrouge.app.cars.CarsApp;
import filrouge.app.R;

/*
    * auteur : clara et sabra
    * modifié par : sabra
    * Classe pour l'adaptateur de la liste des avis
*/
public class OpinionAdapter extends BaseAdapter {

    private final String TAG = "Clara + Sabra " + getClass().getSimpleName();
    private final List<Opinion> opinionList;
    private LayoutInflater mInflater;

    public OpinionAdapter(Context context, List<Opinion> opinionList) {
        this.opinionList = opinionList;
        this.mInflater = LayoutInflater.from(CarsApp.getContext());
    }

    @Override
    public int getCount() {
        return opinionList.size();
    }

    @Override
    public Object getItem(int position) {
        return opinionList.get(position);
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
        Opinion avis = opinionList.get(position);

        // Récupérer les vues de la mise en page
        TextView textViewEmail = layoutItem.findViewById(R.id.textEmail);
        TextView textViewOpinion = layoutItem.findViewById(R.id.textOpinion);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);

        // Définir les valeurs des vues avec les données de l'avis
        textViewEmail.setText(avis.getUserEmail());
        textViewOpinion.setText(avis.getOpinion());
        ratingBar.setRating(avis.getRanking());
        return layoutItem;



    }
}
