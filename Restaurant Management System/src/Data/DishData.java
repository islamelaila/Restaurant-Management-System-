package Data;


import models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishData {
    private static List<Dish> dishes = new ArrayList<>();

    public static void addDish(Dish dish) {
        dishes.add(dish);
    }

    public static List<Dish> getDishes() {
        return dishes;
    }

    public static Dish findDishByName(String name) {
        return dishes.stream()
                .filter(dish -> dish.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static Dish findDishById(String id) {
        return dishes.stream()
                .filter(dish -> dish.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static boolean removeDish(String name) {
        return dishes.removeIf(dish -> dish.getName().equalsIgnoreCase(name));
    }

    // Initialize with some sample dishes
    static {
        addDish(new Dish("Margherita Pizza", 12.99));
        addDish(new Dish("Chicken Burger", 8.99));
        addDish(new Dish("Caesar Salad", 7.99));
        addDish(new Dish("Pasta Carbonara", 11.49));
        addDish(new Dish("Pizza",10.50));
    }
}