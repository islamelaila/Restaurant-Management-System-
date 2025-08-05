package services.Interfaces;

import models.Order;
import models.OrderStatus;

import java.util.List;

public interface DeliveryService extends UserService {
    List<Order> viewOrders();
    boolean updateOrderStatus(String orderId, OrderStatus status);
}