package order;

import javafx.beans.value.ObservableValueBase;
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
import menu.MenuUI;

import java.io.IOException;
import application.Main;
import item.MenuItem;
import item.OrderItem;

public class OrderUI {
    private TableView<Order> ordersTable;
    private Button backBtn;
    private Button newOrderBtn;
    private Button modifyOrderBtn;
    private OrderHashMap<Integer, Order> orderHashMap = new OrderHashMap<>();
    
    @SuppressWarnings("unchecked")
    public Scene getScene() {
        Scene scene = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Order.fxml"));

            this.ordersTable = (TableView<Order>) root.lookup("#ordersTable");
            this.backBtn = (Button) root.lookup("#backBtn");
            this.newOrderBtn = (Button) root.lookup("#newOrderBtn");
            this.modifyOrderBtn = (Button) root.lookup("#modifyOrderBtn");

            // Initialize the table columns
            TableColumn<Order, Integer> idColumn = new TableColumn<>("Id");
//            idColumn.setCellValueFactory(data -> data.getValue().getId());

            TableColumn<Order, Double> priceColumn = new TableColumn<>("Price");
            priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            
            TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Quantity");
            quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            
            TableColumn<Order, String> statusColumn = new TableColumn<>("Status");
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
            
            this.ordersTable.getColumns().addAll(idColumn, priceColumn, quantityColumn, statusColumn);

            // Set event handlers
            this.backBtn.setOnAction(e -> {
                Main.toDashboard();
            });

            this.newOrderBtn.setOnAction(e -> {
                createNewOrder();
            });

            this.modifyOrderBtn.setOnAction(e -> {
                modifySelectedOrder(MenuUI.menu);
            });

            populateOrderTable();

            scene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    private Order createNewOrder() {
        Order order = new Order();
        orderHashMap.put(order.getId(), order);
        return order;
    }

    private void modifySelectedOrder(Menu<MenuItem> menu) {
        // Retrieve the selected order item from the table
        Order selectedOrderItem = ordersTable.getSelectionModel().getSelectedItem();

        if (selectedOrderItem != null) {
//            OrderModifyUI orderModifyUI = new OrderModifyUI(selectedOrderItem);
//            Main.toOrderModify(orderModifyUI.getScene());
        } else {
            // Show an error message or handle the case where no order item is selected
        }
    }


    private void populateOrderTable() {
        // Add items to the order and update the table
        try {
            // Hardcoded items
        	
        	Order order = createNewOrder();
        	            
            ordersTable.setItems(FXCollections.observableList(orderHashMap.getValues()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
