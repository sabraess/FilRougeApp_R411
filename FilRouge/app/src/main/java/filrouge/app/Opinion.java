package filrouge.app;

import android.graphics.Path;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Opinion implements OpinionList{
    private String name;
    private float rating;
    private String comment;

    private int carId;

    public Opinion(Parcel in){
        this.name = in.readString();
        this.rating = in.readFloat();
        this.comment = in.readString();
        this.carId = in.readInt();
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public int getCarId() {
        return carId;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeFloat(this.rating);
        dest.writeString(this.comment);
        dest.writeInt(this.carId);
    }

    public static final Parcelable.Creator<Opinion> CREATOR = new Parcelable.Creator<Opinion>() {
        public Opinion createFromParcel(Parcel in) {
            return new Opinion(in);
        }

        public Opinion[] newArray(int size) {
            return new Opinion[size];
        }

    };

    public static Parcelable.Creator<Opinion> getCREATOR(){
        return CREATOR;
    }
}

