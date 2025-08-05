package models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Order> orders;

    public Customer(String username, String password) {
        super(username, password);
        this.orders = new ArrayList<>();
    }

    public void viewMenu(List<Dish> menu) {
        System.out.println("\n=== MENU ===");
        if (menu.isEmpty()) {
            System.out.println("No dishes available.");
            return;
        }

        for (Dish dish : menu) {
            System.out.printf("ID: %s | Name: %s | Price: $%.2f%n",
                    dish.getId(), dish.getName(), dish.getPrice());
        }
    }

    public Order makeOrder(Dish dish, int quantity) {
        Order order = new Order(dish, quantity, this);
        orders.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
}