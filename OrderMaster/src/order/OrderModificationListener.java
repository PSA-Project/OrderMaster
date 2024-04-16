package order;

import item.MenuItem;
import item.OrderItem;

public interface OrderModificationListener {
    void onOrderModified(OrderItem<MenuItem> modifiedOrderItem);
}
