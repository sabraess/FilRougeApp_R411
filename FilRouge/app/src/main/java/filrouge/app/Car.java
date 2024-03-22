package filrouge.app;
/*
 * classe qui permet de créer un objet Cars
 */

public class Car {
    private int id;
    private String name;
    private String brand;
    private String description;
    private  String energy;
    private String price;
    private String maxSpeed;
    private String power;
    private String pictureDescription;
    private String picture;

    private float value; //pour le ratingBar

    public Car(){
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

    public String getPrice() {
        return this.price;
    }
    public void setPrice(String price){
        this.price = price;
    }

    public String getMaxSpeed() {
        return this.maxSpeed;
    }
    public void setMaxSpeed(String maxSpeed){
        this.maxSpeed = maxSpeed;
    }

    public String getPower() {
        return this.power;
    }
    public void setPower(String power){
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
        this.picture = "http://" + "/img/"+picture;
    }

    public String getPictureDescription() {
        return this.pictureDescription;
    }
    public void setPictureDescription(String pictureDescription){
        this.pictureDescription = pictureDescription;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
