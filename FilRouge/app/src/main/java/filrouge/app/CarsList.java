package filrouge.app;

import java.util.ArrayList;
import java.util.List;

/*
* classe qui permet de créer une liste de voitures
*/

public class CarsList {
    private static List<Cars> carsList = new ArrayList<>();
    private static List<Cars> displayCars = new ArrayList<>();
    private static CarsList instance = null;


    public static List<Cars> getCarsList() {
        if (instance == null) {
            instance = new CarsList();
        }
        return carsList;
    }

    public static List<Cars> getDisplayCars() {
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

    public static void addDisplayCars(Cars cars){
        if(instance == null){
            instance = new CarsList();
        }
        displayCars.add(cars);
    }

    public static Cars getDisplayCars(int index){
        if(instance == null){
            instance = new CarsList();
        }
        return displayCars.get(index);
    }

    public static Cars getCars(int index){
        if(instance == null){
            instance = new CarsList();
        }
        return carsList.get(index);
    }

}
