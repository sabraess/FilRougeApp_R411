package filrouge.app;

import android.os.Parcelable;

public interface AvisList  extends Parcelable {

    String getEmailUtilisateur();
    //    float getRating();
    String getAvis();

    float getRanking();

}
