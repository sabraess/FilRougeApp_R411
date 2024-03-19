package filrouge.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * author: TORRI Clara
 * Modifié par: TORRI Clara
 * vue au lancement de l'application
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation de l'animation
        Animation startAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.exit_cars);

        //initialisation de l'image et lancement de l'animation sur l'image
        ImageView imageView = findViewById(R.id.picture);
        imageView.startAnimation(startAnimation);

        startAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //lancement de l'activité HomeActivity
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}