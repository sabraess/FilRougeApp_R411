package filrouge.app.basket;

import java.util.ArrayList;
import java.util.List;

import filrouge.app.cars.CarsList;

/*
* auteur : clara et sabre
* modifié par clara
* classe qui permet de gérer le panier
*/
public class ShoppingBasket {
    /*initialisation de la liste des voiture dans le panier*/
    private static List<CarsList> carsInBasket = new ArrayList<>();

    /*ajoute une voiture à la liste*/
    public static void addCar(CarsList car) {
        carsInBasket.add(car);
    }

    /*supprime une voiture de la liste*/
    public static void removeCar(int position) {
        carsInBasket.remove(position);
    }

    /*retourne la liste des voitures dans le panier*/
    public static List<CarsList> getCarsInBasket() {
        return carsInBasket;
    }

    /*vide le panier*/
    public static void clearBasket() {
        carsInBasket.clear();
    }

    /*retourne le prix total des voitures dans le panier*/
    public static int getTotalPrice() {
        int totalPrice = 0;
        for (CarsList car : carsInBasket) {
            totalPrice += car.getPrice();
        }
        return totalPrice;
    }
}
