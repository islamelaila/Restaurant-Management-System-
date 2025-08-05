package models;

public class Dish {
    private String id;
    private String name;
    private double price;
    private static int idCounter = 1;

    public Dish(String name, double price) {
        this.id = "D" + String.format("%03d", idCounter++);
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Dish{id='%s', name='%s', price=%.2f}", id, name, price);
    }
}