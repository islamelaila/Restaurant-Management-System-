package services.Interfaces;

import models.Order;
import models.OrderStatus;

import java.util.List;

public interface ChefService extends UserService {
    boolean addDish(String name, double price);
    boolean editDish(String name, String newName, double newPrice);
    List<Order> viewOrders();
    boolean updateOrderStatus(String orderId, OrderStatus status);
}