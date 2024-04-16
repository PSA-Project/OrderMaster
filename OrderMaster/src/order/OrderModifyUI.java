package order;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import application.Main;
import item.MenuItem;
import item.OrderItem;
import menu.Menu;
import menu.MenuUI;

import java.io.IOException;
import java.util.ArrayList;

public class OrderModifyUI {
    private Button backBtn;
    private VBox modifyOrderVbox;
    private ComboBox<MenuItem> itemNameComboBox;  // Changed to ComboBox<MenuItem>
    private TextField quantityTextField;
    private ComboBox<String> statusComboBox;
    private Button saveBtn;
    private Button cancelBtn;
    private Text statusText;
    private Order selectedOrder;

    public OrderModifyUI(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public Scene getScene() {
        Scene scene = null;
        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderModify.fxml"));
//            Parent root = loader.load();
            Parent root = FXMLLoader.load(getClass().getResource("OrderModify.fxml"));
            scene = new Scene(root);

            backBtn = (Button) root.lookup("#backBtn");
            modifyOrderVbox = (VBox) root.lookup("#modifyOrderVbox");
            itemNameComboBox = (ComboBox<MenuItem>) root.lookup("#itemNameComboBox");
            quantityTextField = (TextField) root.lookup("#quantityTextField");
            statusComboBox = (ComboBox<String>) root.lookup("#statusComboBox");
            saveBtn = (Button) root.lookup("#saveBtn");
            cancelBtn = (Button) root.lookup("#cancelBtn");
            statusText = (Text) root.lookup("#statusText");

            // Set event handlers
            backBtn.setOnAction(e -> Main.toOrder());
            saveBtn.setOnAction(e -> modifyOrder());
            cancelBtn.setOnAction(e -> Main.toOrder());
            
            statusComboBox.getItems().addAll("Pending", "In Progress", "Completed");

            // Populate fields with selected order item data
            if (selectedOrder != null) {
                statusComboBox.setValue(selectedOrder.getStatus());
            }

            populateItemNameComboBox();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }

    private void populateItemNameComboBox() {
    	ArrayList<MenuItem> allItems = new ArrayList<>();
        collectAllItems(MenuUI.menu.getRootItem(), allItems);
        itemNameComboBox.getItems().setAll(allItems);
    }

    private void collectAllItems(MenuItem parent, ArrayList<MenuItem> allItems) {
        if (parent != null) {
            try {
				ArrayList<MenuItem> children = MenuUI.menu.getChildren(parent);
				
				for (MenuItem child : children) {
					if(child.getType() == MenuItem.Type.ITEM){
						allItems.add(child);
					}
				    collectAllItems(child, allItems);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

    private void modifyOrder() {
        if (selectedOrder != null) {
            try {
                int newQuantity = Integer.parseInt(quantityTextField.getText());
//                selectedOrderItem.setQuantity(newQuantity);
                selectedOrder.setStatus(statusComboBox.getValue());

                statusText.setText("Order modified successfully!");
                statusText.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                statusText.setText("Please enter a valid quantity.");
                statusText.setFill(Color.RED);
            }
        } else {
            statusText.setText("No order item selected.");
            statusText.setFill(Color.RED);
        }
    }
}
