package order;

import java.util.ArrayList;

import item.OrderItem;

public class Order {
    private Node head;
    private double totalPrice;
    private String status;

    public Order() {
        this.head = null;
        this.totalPrice = 0.0;
        this.status = "Pending";
    }

    private static class Node {
        private OrderItem data;
        private Node next;

        public Node(OrderItem data) {
            this.data = data;
            this.next = null;
        }
    }

    public void addItem(OrderItem orderItem) {
        Node newNode = new Node(orderItem);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        totalPrice += orderItem.getPrice() * orderItem.getQuantity(); // Update total price with quantity
    }

    public void removeItem(OrderItem item) {
        if (head == null) {
            return;
        }
        if (head.data.equals(item)) {
            head = head.next;
        } else {
            Node current = head;
            while (current.next != null) {
                if (current.next.data.equals(item)) {
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }
        totalPrice -= item.getPrice() * item.getQuantity(); // Update total price with quantity
    }

    public OrderItem[] getItems() {
        ArrayList<OrderItem> itemsList = new ArrayList<>();
        Node current = head;
        while (current != null) {
            itemsList.add(current.data);
            current = current.next;
        }
        return itemsList.toArray(new OrderItem[0]); // Convert ArrayList to array
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
