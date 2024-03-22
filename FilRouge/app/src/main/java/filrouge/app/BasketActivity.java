package filrouge.app;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BasketActivity extends AppCompatActivity {

    private final String TAG = "BasketActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        ListView listView = findViewById(R.id.listViewPanier);



        // Adapter adapter = new A(CarsList.getDisplayCars(), this);
       // listView.setAdapter(carsAdapter);


    }
}