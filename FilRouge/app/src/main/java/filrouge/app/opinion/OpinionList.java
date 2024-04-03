package filrouge.app.opinion;

import android.os.Parcelable;

public interface OpinionList extends Parcelable {

    String getEmailUtilisateur();
    //    float getRating();
    String getAvis();

    float getRanking();

}
