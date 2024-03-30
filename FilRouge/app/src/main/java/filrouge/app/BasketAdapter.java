package filrouge.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.squareup.picasso.Picasso;
import java.util.List;

public class BasketAdapter extends BaseAdapter {
    private final String TAG = "Clara + Sabra " + getClass().getSimpleName();
    private final List<CarsList> carsInBasket;
    private LayoutInflater mInflater;
    private Clickable callBackActivity;


    public BasketAdapter(List<CarsList> carsInBasket, Clickable clickable) {
        this.carsInBasket = carsInBasket;
        this.mInflater = LayoutInflater.from(CarsApp.getContext());
        this.callBackActivity = clickable;
    }

    @Override
    public int getCount() {
        return carsInBasket.size();
    }

    @Override
    public Object getItem(int position) {
        return carsInBasket.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.listview_basket, parent, false);

        ImageView picture = layoutItem.findViewById(R.id.picture);
        TextView name = layoutItem.findViewById(R.id.name);
        TextView carPrice = layoutItem.findViewById(R.id.carPrice);

        name.setText(carsInBasket.get(position).getName());
        Picasso.get().load(carsInBasket.get(position).getPicture()).into(picture);
        carPrice.setText(carsInBasket.get(position).getPrice());

        layoutItem.setOnClickListener(click -> {
            callBackActivity.onClickItem(position);
        });
        return layoutItem;

    }
}

