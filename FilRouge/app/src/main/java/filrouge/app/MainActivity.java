package filrouge.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/*
 * auteur: Clara et Sabra
 * Modifié par:  Clara
 * vue au lancement de l'application du début
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*initialisation de l'image et lancement de l'animation sur l'image*/
        ImageView imageView = findViewById(R.id.pictureCarAnimation);

        //creer objet animation
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 1000f);
        animator.setDuration(4000);

        /*lancer l'animation*/
        animator.start();

        /*apres la fin de l'animation, on lance l'activité HomeActivity*/
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

        });
    }
}