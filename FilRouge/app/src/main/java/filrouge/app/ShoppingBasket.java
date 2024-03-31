package filrouge.app;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasket {
    private static List<CarsList> carsInBasket = new ArrayList<>();

    public static void addCar(CarsList car) {
        carsInBasket.add(car);
    }

    public static void removeCar(int position) {
        carsInBasket.remove(position);
    }

    public static List<CarsList> getCarsInBasket() {
        return carsInBasket;
    }

    public static void clearBasket() {
        carsInBasket.clear();
    }

    public static int getTotalPrice() {
        int totalPrice = 0;
        for (CarsList car : carsInBasket) {
            totalPrice += car.getPrice();
        }
        return totalPrice;
    }
}
