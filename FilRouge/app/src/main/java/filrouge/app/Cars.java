package filrouge.app;

public class Cars{
    private final String name;
    private String description;
    private  String energy;
    private String price;
    private String maxSpeed;
    private float value; //pour le ratingBar
    private final int picture;

    public Cars(String name) {
        this.name = name;
        this.picture = CarsApp.getContext().getResources().getIdentifier(name, "drawable", CarsApp.getContext().getPackageName());
        this.description = getPosition()<0 ? null : CarsApp.getContext().getResources().getStringArray(R.array.description)[getPosition()];
        this.maxSpeed = getPosition()<0 ? null : CarsApp.getContext().getResources().getStringArray(R.array.maxSpeed)[getPosition()];
        this.price = getPosition()<0 ? null : CarsApp.getContext().getResources().getStringArray(R.array.price)[getPosition()];
        this.energy = getPosition()<0 ? null : CarsApp.getContext().getResources().getStringArray(R.array.energy)[getPosition()];
        this.value = (name.charAt(0)/name.length()/3)/2.0f;
    }

    private int getPosition(){
        String[] names = CarsApp.getContext().getResources().getStringArray(R.array.cars);
        for (int i=0 ; i<names.length ; i++){
            if (names[i].equals(name)) return i;
        }
        return -1;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEnergy() {
        return this.energy;
    }

    public String getPrice() {
        return this.price;
    }

    public String getMaxSpeed() {
        return this.maxSpeed;
    }

    public float getValue() {
        return this.value;
    }
    public void setValues(float value) {
        this.value = value;
    }

    public int getPicture() {
        return this.picture;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
