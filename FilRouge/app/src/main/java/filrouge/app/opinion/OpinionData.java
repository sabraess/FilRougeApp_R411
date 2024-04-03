
package filrouge.app.opinion;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.NonNull;

@SuppressLint("ParcelCreator")
public class OpinionData implements OpinionList {
    private String emailUtilisateur;
    private String avis;
    private float ranking;

    public OpinionData() {
        // Constructeur vide requis pour Firebase
    }

    public OpinionData(String avis, String emailUtilisateur, float ranking) {
        this.emailUtilisateur = emailUtilisateur;
        this.avis = avis;
        this.ranking = ranking;

    }

    public String getEmailUtilisateur() {
        return emailUtilisateur;
    }

    public void setEmailUtilisateur(String emailUtilisateur) {
        this.emailUtilisateur = emailUtilisateur;
    }

    public String getAvis() {
        return avis;
    }

    @Override
    public float getRanking() {
        return this.ranking;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(this.emailUtilisateur);
        dest.writeString(this.avis);
        dest.writeFloat(this.ranking);

    }
}

