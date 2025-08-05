package models;

import java.util.List;

public class Chef extends User {

    // constructor
    public Chef(String username, String password) {
        super(username, password);
    }

    // methods
    public void addDish(Dish dish) {
        System.out.println("Dish " + dish.getName() + "' added to menu successfully!");
    }

    public void editDish(String name) {
        System.out.println("Dish '" + name + "' edited successfully!");
    }

    public void viewOrders(List<Order> orders) {
        System.out.println("\n=== ORDERS ===");
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            System.out.printf("Order ID: %s | Customer: %s | Dish: %s | Quantity: %d | Status: %s%n",
                    order.getOrderId(), order.getCustomer().getUsername(),
                    order.getDish().getName(), order.getQuantity(), order.getStatus());
        }
    }

    public void updateOrderStatus(Order order, OrderStatus status) {
        order.setStatus(status);
        System.out.println("Order " + order.getOrderId() + " status updated to: " + status);
    }
}