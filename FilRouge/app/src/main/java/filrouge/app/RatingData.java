
package filrouge.app;

import android.annotation.SuppressLint;
import android.os.Parcel;

import androidx.annotation.NonNull;

@SuppressLint("ParcelCreator")
public class RatingData implements AvisList{
    private String emailUtilisateur;
    private String avis;
    private float ranking;

    public RatingData() {
        // Constructeur vide requis pour Firebase
    }

    public RatingData( String avis, String emailUtilisateur, float ranking) {
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


