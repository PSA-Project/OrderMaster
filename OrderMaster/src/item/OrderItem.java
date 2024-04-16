package item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class OrderItem<T> {
    private T item;
    private double price;
    private int quantity;
    private String status;

    public OrderItem(T item, double price, int quantity, String status) {
        this.item = item;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    // Method to add all items from a collection to the existing list
    public void addAll(Collection<OrderItem<T>> items) {
        for (OrderItem<T> item : items) {
            this.item = item.getItem();
            this.price += item.getPrice();
            this.quantity += item.getQuantity();
            this.status = item.getStatus();
        }
    }
    
    @Override
    public String toString() {
        return Objects.toString(item); // Use Objects.toString() to handle null items
    }
}
