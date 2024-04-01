package filrouge.app;

import android.os.Parcelable;

public interface OpinionList extends Parcelable {


    String getName();
    float getRating();
    String getComment();
    int getCarId();
    @Override
    String toString();

}