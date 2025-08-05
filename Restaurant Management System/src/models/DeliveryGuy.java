package models;

import java.util.List;

public class DeliveryGuy extends User {

    public DeliveryGuy(String username, String password) {
        super(username, password);
    }

    public void viewOrders(List<Order> orders) {
        System.out.println("\n=== DELIVERY ORDERS ===");
        List<Order> readyOrders = orders.stream().filter(order -> order.getStatus()== OrderStatus.READY_FOR_DELIVERY).toList();


        if (readyOrders.isEmpty()) {
            System.out.println("No orders ready for delivery.");
            return;
        }

        for (Order order : readyOrders) {
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