package order;

import java.util.LinkedList;

import item.MenuItem;
import item.OrderItem;

public class Order {
    private LinkedList<OrderItem<MenuItem>> items;
    private String status;

    public Order() {
        this.items = new LinkedList<>();
        this.status = "Pending"; // Initial status
    }

    public LinkedList<OrderItem<MenuItem>> getItems() {
        return items;
    }

    public String getStatus() {
        return status;
    }

    public void addItem(OrderItem<MenuItem> item) {
        items.add(item);
    }

    public void removeItem(OrderItem<MenuItem> item) {
        items.remove(item);
    }
}
