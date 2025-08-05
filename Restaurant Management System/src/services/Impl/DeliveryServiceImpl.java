package services.Impl;


import Data.OrderData;
import Data.UserData;
import models.DeliveryGuy;
import models.Order;
import models.OrderStatus;
import models.User;
import services.Interfaces.DeliveryService;

import java.util.List;

public class DeliveryServiceImpl implements DeliveryService {

    @Override
    public User login(String username, String password) {
        DeliveryGuy deliveryGuy = UserData.findDeliveryGuy(username);
        if (deliveryGuy != null && deliveryGuy.checkPassword(password)) {
            return deliveryGuy;
        }
        return null;
    }

    @Override
    public List<Order> viewOrders() {
        // Only show orders that are ready for delivery
        return OrderData.getOrdersByStatus(OrderStatus.READY_FOR_DELIVERY);
    }

    @Override
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        Order order = OrderData.findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return false;
        }

        // Delivery guys can only update to DELIVERED status
        if (status != OrderStatus.DELIVERED) {
            System.out.println("Delivery personnel can only mark orders as DELIVERED!");
            return false;
        }

        order.setStatus(status);
        System.out.printf("Order %s marked as delivered!%n", orderId);
        return true;
    }
}