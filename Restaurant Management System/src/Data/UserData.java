package Data;

import models.Chef;
import models.Customer;
import models.DeliveryGuy;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Chef> chefs = new ArrayList<>();
    private static List<DeliveryGuy> deliveryGuys = new ArrayList<>();

    // Customer methods
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static Customer findCustomer(String username) {
        return customers.stream()
                .filter(customer -> customer.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    // Chef methods
    public static void addChef(Chef chef) {
        chefs.add(chef);
    }

    public static List<Chef> getChefs() {
        return chefs;
    }

    public static Chef findChef(String username) {
        return chefs.stream()
                .filter(chef -> chef.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    // DeliveryGuy methods
    public static void addDeliveryGuy(DeliveryGuy deliveryGuy) {
        deliveryGuys.add(deliveryGuy);
    }

    public static List<DeliveryGuy> getDeliveryGuys() {
        return deliveryGuys;
    }

    public static DeliveryGuy findDeliveryGuy(String username) {
        return deliveryGuys.stream()
                .filter(deliveryGuy -> deliveryGuy.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    // Initialize with some default users
    static {
        addChef(new Chef("chef1", "chef123"));
        addDeliveryGuy(new DeliveryGuy("delivery1", "delivery123"));
        addCustomer(new Customer("islam","islam123"));
    }
}