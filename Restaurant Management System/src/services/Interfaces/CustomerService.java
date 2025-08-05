package services.Interfaces;

import models.Dish;
import models.Order;

import java.util.List;

public interface CustomerService extends Registerable, UserService {
    List<Dish> viewMenu();
    Order makeOrder(String dishName, int quantity, String customerUsername);
    List<Order> viewMyOrders(String customerUsername);
}