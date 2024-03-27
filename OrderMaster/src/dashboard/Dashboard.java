package dashboard;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Dashboard {
	public Scene getScene() {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root,400,400);
		return scene;
	}
}
