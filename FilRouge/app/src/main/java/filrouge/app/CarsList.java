package filrouge.app;

import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/*
* auteur : clara et sabra
* modifié par clara
* interface pour les voitures qui hérite de parcelable
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
    List<Opinion> getOpinionList();
    void setOpinionList(List<Opinion> value);
    @NonNull
    @Override
    String toString();

}
