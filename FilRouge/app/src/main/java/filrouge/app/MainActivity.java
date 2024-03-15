package filrouge.app;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * author = TORRI Clara
 * vue au lancement de l'application
 *
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation de l'animation
        Animation exitAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.exit_cars);

        //initialisation de l'image et lancement de l'animation sur l'image
        ImageView imageView = findViewById(R.id.picture);
        imageView.startAnimation(exitAnimation);
    }
}