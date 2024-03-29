package filrouge.app;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/*
* classe qui permet de créer une liste de voitures
*/

public interface CarsList extends Parcelable {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getBrand();
    void setBrand(String brand);
    String getDescription();
    void setDescription(String description);
    String getEnergy();
    void setEnergy(String energy);
    int getPrice();
    void setPrice(int price);
    int getMaxSpeed();
    void setMaxSpeed(int maxSpeed);
    int getPower();
    void setPower(int power);
    String getPicture();
    void setPicture(String picture);
    float getValue();
    void setValues(float value);
    @Override
    String toString();

}
