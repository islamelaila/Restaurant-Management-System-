package restaurant;

import models.*;
import services.Impl.ChefServiceImpl;
import services.Impl.CustomerServiceImpl;
import services.Impl.DeliveryServiceImpl;
import services.Interfaces.ChefService;
import services.Interfaces.CustomerService;
import services.Interfaces.DeliveryService;
import services.Interfaces.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService = new CustomerServiceImpl();
    private static ChefService chefService = new ChefServiceImpl();
    private static DeliveryService deliveryService = new DeliveryServiceImpl();

    public static void main(String[] args) {
        System.out.println("=== WELCOME TO RESTAURANT MANAGEMENT SYSTEM ===");

        while (true) {
            showMainMenu();
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    customerFlow();
                    break;
                case 2:
                    chefFlow();
                    break;
                case 3:
                    deliveryFlow();
                    break;
                case 4:
                    System.out.println("Thank you for using Restaurant !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== Choose your role ===");
        System.out.println("1. Customer ");
        System.out.println("2. Chef ");
        System.out.println("3. Delivery ");
        System.out.println("4. Exit");
        System.out.print("Choose your role: ");
    }

    private static void customerFlow() {
        System.out.println("\n=== CUSTOMER PORTAL ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.print("Choose option: ");

        int choice = getIntInput();
        Customer customer = null;

        if (choice == 1) {
            customer = (Customer) loginUser(customerService);
        } else if (choice == 2) {
            if (registerCustomer()) {
                System.out.println("Please login with your new account:");
                customer = (Customer) loginUser(customerService);
            }
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        if (customer != null) {
            customerMenu(customer);
        }
    }

    private static void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. View My Orders");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    List<Dish> menu = customerService.viewMenu();
                    customer.viewMenu(menu);
                    break;
                case 2:
                    placeOrder(customer);
                    break;
                case 3:
                    viewCustomerOrders(customer);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void placeOrder(Customer customer) {
        List<Dish> menu = customerService.viewMenu();
        customer.viewMenu(menu);

        System.out.print("\nEnter dish name: ");
        String dishName = scanner.nextLine();

        System.out.print("Enter quantity: ");
        int quantity = getIntInput();

        customerService.makeOrder(dishName, quantity, customer.getUsername());
    }

    private static void viewCustomerOrders(Customer customer) {
        List<Order> orders = customerService.viewMyOrders(customer.getUsername());
        System.out.println("\n=== MY ORDERS ===");

        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order order : orders) {
            System.out.printf("Order ID: %s | Dish: %s | Quantity: %d | Status: %s | Total: $%.2f%n",
                    order.getOrderId(), order.getDish().getName(), order.getQuantity(),
                    order.getStatus(), order.getTotalPrice());
        }
    }

    private static void chefFlow() {
        System.out.println("\n=== CHEF PORTAL ===");
        Chef chef = (Chef) loginUser(chefService);

        if (chef != null) {
            chefMenu(chef);
        }
    }

    private static void chefMenu(Chef chef) {
        while (true) {
            System.out.println("\n=== CHEF MENU ===");
            System.out.println("1. Add Dish");
            System.out.println("2. Edit Dish");
            System.out.println("3. View Orders");
            System.out.println("4. Update Order Status");
            System.out.println("5. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addDish();
                    break;
                case 2:
                    editDish();
                    break;
                case 3:
                    List<Order> orders = chefService.viewOrders();
                    chef.viewOrders(orders);
                    break;
                case 4:
                    updateOrderStatusAsChef();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void addDish() {
        System.out.print("Enter dish name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: $");
        double price = getDoubleInput();

        chefService.addDish(name, price);
    }

    private static void editDish() {
        System.out.print("Enter current dish name: ");
        String currentName = scanner.nextLine();

        System.out.print("Enter new dish name (or press Enter to skip): ");
        String newName = scanner.nextLine();

        System.out.print("Enter new price (or 0 to skip): $");
        double newPrice = getDoubleInput();

        chefService.editDish(currentName, newName.isEmpty() ? null : newName, newPrice);
    }

    private static void updateOrderStatusAsChef() {
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();

        System.out.println("Available statuses:");
        System.out.println("1. PENDING");
        System.out.println("2. READY_FOR_DELIVERY");
        System.out.print("Choose status: ");

        int statusChoice = getIntInput();
        OrderStatus status = null;

        switch (statusChoice) {
            case 1:
                status = OrderStatus.PENDING;
                break;
            case 2:
                status = OrderStatus.READY_FOR_DELIVERY;
                break;
            default:
                System.out.println("Invalid status!");
                return;
        }

        chefService.updateOrderStatus(orderId, status);
    }

    private static void deliveryFlow() {
        System.out.println("\n=== DELIVERY PORTAL ===");
        DeliveryGuy deliveryGuy = (DeliveryGuy) loginUser(deliveryService);

        if (deliveryGuy != null) {
            deliveryMenu(deliveryGuy);
        }
    }

    private static void deliveryMenu(DeliveryGuy deliveryGuy) {
        while (true) {
            System.out.println("\n=== DELIVERY MENU ===");
            System.out.println("1. View Ready Orders");
            System.out.println("2. Mark Order as Delivered");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose option: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    List<Order> readyOrders = deliveryService.viewOrders();
                    deliveryGuy.viewOrders(readyOrders);
                    break;
                case 2:
                    markOrderAsDelivered();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void markOrderAsDelivered() {
        System.out.print("Enter order ID to mark as delivered: ");
        String orderId = scanner.nextLine();

        deliveryService.updateOrderStatus(orderId, OrderStatus.DELIVERED);
    }

    private static User loginUser(UserService userService) {
        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userService.login(username, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + user.getUsername());
            return user;
        } else {
            System.out.println("Invalid credentials!");
            return null;
        }
    }

    private static boolean registerCustomer() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        return customerService.register(username, password);

    }

    private static int getIntInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static double getDoubleInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}