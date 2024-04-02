package filrouge.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RatingAdapter extends ArrayAdapter<RatingData> {

    public RatingAdapter(Context context, List<RatingData> avis) {
        super(context, 0, avis);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RatingData avis = getItem(position);

        if (convertView == null) {
         //   convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_avis, parent, false);
        }


        return convertView;
    }
}
