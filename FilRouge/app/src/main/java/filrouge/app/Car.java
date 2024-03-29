package filrouge.app;
/*
 * classe qui permet de créer un objet Cars
 */


import android.os.Parcel;
import android.os.Parcelable;

public class Car  implements CarsList{
    private int id;
    private String name;
    private String brand;
    private String description;
    private  String energy;
    private int price;
    private int maxSpeed;
    private int power;
    private String picture;
    private float value; //pour le ratingBar

    public Car(){
    }

    public Car(Parcel in){
        this.id = in.readInt();
        this.name = in.readString();
        this.brand = in.readString();
        this.description = in.readString();
        this.energy = in.readString();
        this.price = in.readInt();
        this.maxSpeed = in.readInt();
        this.power = in.readInt();
        this.picture = in.readString();
        this.value = in.readFloat();
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getBrand(){return this.brand;}
    public void setBrand(String brand){this.brand = brand;}

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description){ this.description = description;}

    public String getEnergy() {
        return this.energy;
    }
    public void setEnergy(String energy){
        this.energy = energy;
    }

    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price){
        this.price = price;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }
    public void setMaxSpeed(int maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public int getPower() {
        return this.power;
    }
    public void setPower(int power){
        this.power = power;
    }

    public float getValue() {
        return this.value;
    }
    public void setValues(float value) {
        this.value = value;
    }

    public String getPicture() {
       return this.picture;
    }
    public void setPicture(String picture){
        this.picture = "https://raw.githubusercontent.com/sabraess/filrouge/jsonimages/imagesfilrouge/" + picture;
    }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int describeContents(){
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.brand);
        dest.writeString(this.description);
        dest.writeString(this.energy);
        dest.writeInt(this.price);
        dest.writeInt(this.maxSpeed);
        dest.writeInt(this.power);
        dest.writeString(this.picture);
        dest.writeFloat(this.value);
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){

        @Override
        public Car createFromParcel(Parcel in){
            return new Car(in);
        }
        @Override
        public Car[] newArray(int size){
            return new Car[size];
        }
    };

    public static Parcelable.Creator<Car> getCREATOR(){
        return CREATOR;
    }




}
