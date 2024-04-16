import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    OrderItem orderItem1 = new OrderItem("pizza", 2.5);
    OrderItem orderItem2 = new OrderItem("coffee", 6.03);

    Order order1 = new Order(10);
    order1.addItem(orderItem1, 2);
    order1.addItem(orderItem2, 1);
    order1.modifyQuantity(orderItem2, 2);

    Order order2 = new Order(15);
    order2.addItem(orderItem1, 10);

    OrderHashMap<Integer, Order> orderHashMap = new OrderHashMap<>();
    orderHashMap.put(order1.getId(), order1);
    orderHashMap.put(order2.getId(), order2);
    System.out.println(orderHashMap.get(1));

  }

}
