package services.Impl;


import Data.DishData;
import Data.OrderData;
import Data.UserData;
import models.*;
import services.Interfaces.ChefService;

import java.util.List;

public class ChefServiceImpl implements ChefService {

    @Override
    public User login(String username, String password) {
        Chef chef = UserData.findChef(username);
        if (chef != null && chef.checkPassword(password)) {
            return chef;
        }
        return null;
    }

    @Override
    public boolean addDish(String name, double price) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Dish name cannot be empty!");
            return false;
        }

        if (price <= 0) {
            System.out.println("Price must be positive!");
            return false;
        }

        // Check if dish already exists
        if (DishData.findDishByName(name) != null) {
            System.out.println("Dish with this name already exists!");
            return false;
        }

        Dish dish = new Dish(name, price);
        DishData.addDish(dish);
        System.out.printf("Dish '%s' added successfully with ID: %s%n", name, dish.getId());
        return true;
    }

    @Override
    public boolean editDish(String name, String newName, double newPrice) {
        Dish dish = DishData.findDishByName(name);
        if (dish == null) {
            System.out.println("Dish not found!");
            return false;
        }

        if (newName != null && !newName.trim().isEmpty()) {
            dish.setName(newName);
        }

        if (newPrice > 0) {
            dish.setPrice(newPrice);
        }

        System.out.printf("Dish updated successfully: %s%n", dish);
        return true;
    }

    @Override
    public List<Order> viewOrders() {
        return OrderData.getOrders();
    }



    @Override
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        Order order = OrderData.findOrderById(orderId);
        if (order == null) {
            System.out.println("Order not found!");
            return false;
        }

        order.setStatus(status);
        System.out.printf("Order %s status updated to: %s%n", orderId, status);
        return true;
    }
}