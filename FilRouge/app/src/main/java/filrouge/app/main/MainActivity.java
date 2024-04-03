package filrouge.app.main;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import filrouge.app.R;

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

        //creer objet animation avec le fichier car_sliding.xml
        ObjectAnimator animator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.car_sliding);
        animator.setTarget(imageView);

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