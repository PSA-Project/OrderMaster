package menu;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuUI {
	public Scene getScene() {
		Scene scene = null;
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
			scene = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scene;
	}
}
