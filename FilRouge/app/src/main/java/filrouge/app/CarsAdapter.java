package filrouge.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;

import java.util.List;

/*
    * classe qui permet de créer un adapter
 */

public class CarsAdapter extends BaseAdapter {
    private final String TAG = "CarsAdapter";
    private final List<Car> carsList;
    private LayoutInflater mInflater;
    private Clickable callBackActivity;

    public CarsAdapter(List<Car> carsList, Clickable clickable) {
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

        TextView name = layoutItem.findViewById(R.id.name);
        RatingBar ratingBar = layoutItem.findViewById(R.id.ratingBar);
        ImageView picture = layoutItem.findViewById(R.id.picture);


        name.setText(carsList.get(position).getName());
        //picture.setImageResource(carsList.get(position).getPicture());
        Picasso.get().load(carsList.get(position).getPicture()).into(picture);

        ratingBar.setOnRatingBarChangeListener((ratingBar1, value, b) -> {
            callBackActivity.onRatingChanged(position, value);
        });

        layoutItem.setOnClickListener(click -> {
            callBackActivity.onClickItem(position);
        });
        return layoutItem;

    }
}
