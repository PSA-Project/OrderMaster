package order;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import menu.Menu;
import java.io.IOException;
import application.Main;
import item.MenuItem;
import item.OrderItem;

public class OrderUI {
    private TableView<OrderItem<MenuItem>> ordersTable;
    private Button backBtn;
    private Button newOrderBtn;
    private Button modifyOrderBtn;
    private Order order;
    private ObservableList<OrderItem<MenuItem>> orderItems = FXCollections.observableArrayList();
	private Menu<MenuItem> menu;
    
    public OrderUI(Menu<MenuItem> menu) {
        this.menu = menu;
    }

    @SuppressWarnings("unchecked")
    public Scene getScene() {
        Scene scene = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));

            this.ordersTable = (TableView<OrderItem<MenuItem>>) root.lookup("#ordersTable");
            this.backBtn = (Button) root.lookup("#backBtn");
            this.newOrderBtn = (Button) root.lookup("#newOrderBtn");
            this.modifyOrderBtn = (Button) root.lookup("#modifyOrderBtn");

            // Initialize the table columns
            TableColumn<OrderItem<MenuItem>, String> nameColumn = new TableColumn<>("Name");
            nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getItem().getName()));

            TableColumn<OrderItem<MenuItem>, Double> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            TableColumn<OrderItem<MenuItem>, Integer> quantityColumn = new TableColumn<>("Quantity");
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            TableColumn<OrderItem<MenuItem>, String> statusColumn = new TableColumn<>("Status");
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            this.ordersTable.getColumns().addAll(nameColumn, priceColumn, quantityColumn, statusColumn);

            // Set event handlers
            this.backBtn.setOnAction(e -> {
                Main.toDashboard();
            });

            this.newOrderBtn.setOnAction(e -> {
                createNewOrder();
            });

            this.modifyOrderBtn.setOnAction(e -> {
                modifySelectedOrder(menu);
            });

            // Create a new order
            order = new Order();
            populateOrderTable();

            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    private void createNewOrder() {
        // Implement functionality to create a new order
    }

    private void modifySelectedOrder(Menu<MenuItem> menu) {
        // Retrieve the selected order item from the table
        OrderItem<MenuItem> selectedOrderItem = ordersTable.getSelectionModel().getSelectedItem();

        if (selectedOrderItem != null) {
            // Pass the selected order item to the OrderModifyUI class for modification
            OrderModifyUI orderModifyUI = new OrderModifyUI(selectedOrderItem);
            //orderModifyUI.setSelectedOrderItem(selectedOrderItem); // Set the selected order item
           // orderModifyUI.setMenu(menu); // Set the menu object if needed
            Main.toOrderModify((orderModifyUI.getScene()));
        } else {
            // Show an error message or handle the case where no order item is selected
        }
    }


    private void populateOrderTable() {
        // Add items to the order and update the table
        try {
            // Hardcoded items
            MenuItem item1 = new MenuItem("Coffee", MenuItem.Type.ITEM);
            MenuItem item2 = new MenuItem("Pizza", MenuItem.Type.ITEM);
            MenuItem item3 = new MenuItem("Burger", MenuItem.Type.ITEM);

            // Clear the existing items before adding new ones
            orderItems.clear();

            // Add the new items to the order
            order.addItem(new OrderItem<>(item1, 2.5, 2, order.getStatus()));
            order.addItem(new OrderItem<>(item2, 10.0, 1, order.getStatus()));
            order.addItem(new OrderItem<>(item3, 5.0, 1, order.getStatus()));

            // Update the orders table with the new order items
            orderItems.addAll(order.getItems());
            ordersTable.setItems(orderItems);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
