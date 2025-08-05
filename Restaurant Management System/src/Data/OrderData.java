package Data;

import models.Order;
import models.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class OrderData {
    private static List<Order> orders = new ArrayList<>();

    public static <Order> void addOrder(Order order) {
        orders.add((models.Order) order);
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static List<Order> getOrdersByStatus(OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .toList();
    }

    public static Order findOrderById(String orderId) {
        return orders.stream()
                .filter(order -> order.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public static List<Order> getOrdersByCustomer(String customerUsername) {
        return orders.stream()
                .filter(order -> order.getCustomer().getUsername().equals(customerUsername))
                .toList();
    }
}