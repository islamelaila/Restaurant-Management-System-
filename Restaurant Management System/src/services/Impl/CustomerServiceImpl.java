package services.Impl;


import Data.DishData;
import Data.OrderData;
import Data.UserData;
import models.Customer;
import models.Dish;
import models.Order;
import models.User;
import services.Interfaces.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public boolean register(String username, String password) {
        // Check if username already exists
        if (UserData.findCustomer(username) != null) {
            System.out.println("Username already exists!");
            return false;
        }

        Customer customer = new Customer(username, password);
        UserData.addCustomer(customer);
        System.out.println("Customer registered successfully!");
        return true;
    }

    @Override
    public User login(String username, String password) {
        Customer customer = UserData.findCustomer(username);
        if (customer != null && customer.checkPassword(password)) {
            return customer;
        }
        return null;
    }

    @Override
    public List<Dish> viewMenu() {
        return DishData.getDishes();
    }

    @Override
    public Order makeOrder(String dishName, int quantity, String customerUsername) {
        Customer customer = UserData.findCustomer(customerUsername);
        Dish dish = DishData.findDishByName(dishName);

        if (customer == null) {
            System.out.println("Customer not found!");
            return null;
        }

        if (dish == null) {
            System.out.println("Dish not found!");
            return null;
        }

        if (quantity <= 0) {
            System.out.println("Invalid quantity!");
            return null;
        }

        Order order = customer.makeOrder(dish, quantity);
        OrderData.addOrder(order);
        System.out.printf("Order placed successfully! Order ID: %s, Total: $%.2f%n",
                order.getOrderId(), order.getTotalPrice());
        return order;
    }

    @Override
    public List<Order> viewMyOrders(String customerUsername) {
        return OrderData.getOrdersByCustomer(customerUsername);
    }
}