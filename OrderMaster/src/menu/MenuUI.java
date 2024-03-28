package menu;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import item.MenuItem;
import item.MenuItem.Type;

public class MenuUI {
	private TreeView<String> menuTree;
	
	@SuppressWarnings("unchecked")
	public Scene getScene() {
		Scene scene = null;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			
			this.menuTree = (TreeView<String>) root.lookup("#menuTree");
						
			Menu<MenuItem> menu = populateTree();
			TreeItem<String> rootItem = this.createMenuTreeRecursive(menu, menu.getRootItem(), null);
			this.menuTree.setRoot(rootItem);

	        	        
			scene = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return scene;
	}
	
	private TreeItem<String> createMenuTreeRecursive(Menu<MenuItem> menu, MenuItem parent, TreeItem<String> item) {
		
		TreeItem<String> treeItem = new TreeItem<String>(parent.getName());
		treeItem.setExpanded(true);
		
		if(item != null) {
			item.getChildren().add(treeItem);
		} else {
			item = treeItem;
		}
		
		try {
			ArrayList<MenuItem> children = menu.getChildren(parent);
			for(MenuItem child : children) {
				this.createMenuTreeRecursive(menu, child, treeItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return item;
	}
	
	private static Menu<MenuItem> populateTree() {
		
		MenuItem menuItem = new MenuItem("Menu", Type.CATEGORY);
		MenuItem drinks = new MenuItem("Drinks", Type.CATEGORY);
		MenuItem coldDrinks = new MenuItem("Cold", Type.CATEGORY);
		MenuItem hotDrinks = new MenuItem("Hot", Type.CATEGORY);
		MenuItem coffee = new MenuItem("Coffee", Type.ITEM);
		MenuItem coke = new MenuItem("Coke", Type.ITEM);
		
		Menu<MenuItem> menu = new Menu<MenuItem>(menuItem);
		try {
			menu.addChild(menuItem, drinks);
			menu.addChild(drinks, coldDrinks);
			menu.addChild(drinks, hotDrinks);
			menu.addChild(hotDrinks, coffee);
			menu.addChild(coldDrinks, coke);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return menu;
	}
}
