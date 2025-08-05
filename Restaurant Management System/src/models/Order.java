package models;

public class Order {
    private String orderId;
    private Dish dish;
    private int quantity;
    private Customer customer;
    private OrderStatus status;
    private static int orderCounter = 1;

    public Order(Dish dish, int quantity, Customer customer) {
        this.orderId = "ORD" + String.format("%03d", orderCounter++);
        this.dish = dish;
        this.quantity = quantity;
        this.customer = customer;
        this.status = OrderStatus.PENDING;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public Dish getDish() {
        return dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // Setters
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return dish.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("Order{id='%s', dish='%s', quantity=%d, customer='%s', status=%s, total=$%.2f}",
                orderId, dish.getName(), quantity, customer.getUsername(), status, getTotalPrice());
    }
}