package filrouge.app;

import java.util.ArrayList;
import java.util.List;

/*
* classe qui permet de créer une liste de voitures
*/

public class CarsList {
    private static List<Car> carsList = new ArrayList<>();
    private static List<Car> displayCars = new ArrayList<>();
    private static CarsList instance = null;


    public static List<Car> getCarsList() {
        if (instance == null) {
            instance = new CarsList();
        }
        return carsList;
    }

    public static List<Car> getDisplayCars() {
        if (instance == null) {
            instance = new CarsList();
        }
        return displayCars;
    }

    public static void clearDisplayCars() {
        if(instance == null){
            instance = new CarsList();
        }
        displayCars.clear();
    }

    public static void addDisplayCars(Car cars){
        if(instance == null){
            instance = new CarsList();
        }
        displayCars.add(cars);
    }

    public static Car getDisplayCars(int index){
        if(instance == null){
            instance = new CarsList();
        }
        return displayCars.get(index);
    }

    public static Car getCars(int index){
        if(instance == null){
            instance = new CarsList();
        }
        return carsList.get(index);
    }

}
