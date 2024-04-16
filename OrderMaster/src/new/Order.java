import java.util.ArrayList;
import java.util.LinkedList;

public class Order {
    private Node head;
    private String status;
    private int id;

    public Order(int id) {
        this.head = null;
        this.status = "Pending";
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    private static class Node {
        private OrderItem orderItem;
        private int quantity;
        private Node next;

        public Node(OrderItem orderItem, int quantity) {
            this.orderItem = orderItem;
            this.quantity = quantity;
            this.next = null;
        }

        @Override
        public String toString() {
            return "{" +
                    "orderItem=" + orderItem +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    public void addItem(OrderItem orderItem, int quantity) {
        Node newNode = new Node(orderItem, quantity);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void removeItem(OrderItem orderItem) {
        if (head == null) {
            return;
        }
        if (head.orderItem.equals(orderItem)) {
            head = head.next;
        } else {
            Node current = head;
            while (current.next != null) {
                if (current.next.orderItem.equals(orderItem)) {
                    current.next = current.next.next;
                    break;
                }
                current = current.next;
            }
        }
    }

    public void modifyQuantity(OrderItem orderItem, int newQuantity) {
        if (head == null) {
            return;
        }
        else {
            Node current = head;
            while (current != null) {
                if (current.orderItem.equals(orderItem)) {
                    current.quantity = newQuantity;
                    break;
                }
                current = current.next;
            }
        }
    }


//    public void getItems() {
//        ArrayList<ArrayList<OrderItem>, Integer> itemsList = new ArrayList<>();
//        Node current = head;
//        while (current != null) {
//            System.out.println(current.orderItem + " " +  current.quantity);
//            itemsList.add(current);
//            current = current.next;
//        }
////        return itemsList;
//    }



    public double getTotalPrice() {
        double totalPrice = 0;
        Node current = head;
        while (current != null) {
            totalPrice += current.orderItem.getPrice() * current.quantity;
            current = current.next;
        }
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node current = head;

        while (current != null) {
            sb.append(current.toString());
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }

        sb.append("]");
        return sb.toString();
    }

}
