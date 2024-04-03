
package filrouge.app.opinion;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import filrouge.app.cars.Car;

/*
    * auteur : clara et sabra
    * modifié par : sabra
    * Classe pour les données des avis
 */

public class Opinion {
    private String userEmail;
    private String opinion;
    private float ranking;

    public Opinion() {
        // Constructeur vide requis pour Firebase
    }

    public Opinion(String opinion, String userEmail, float ranking) {
        this.userEmail = userEmail;
        this.opinion = opinion;
        this.ranking = ranking;
    }

    /*get et set des données des avis*/
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String user) {
        this.userEmail = user;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public float getRanking() {
        return this.ranking;
    }

}


