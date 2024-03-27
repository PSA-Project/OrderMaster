package application;
	
import dashboard.Dashboard;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private static Stage stage;
	
	private static Dashboard dashboard;
	
	@Override
	public void start(Stage primaryStage) {		
		try {
			stage = primaryStage;
			stage.show();
			initialize();
			
			setScene(dashboard.getScene());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setScene(Scene scene) {
		stage.setScene(scene);
	}
	
	public static void initialize() {
		dashboard = new Dashboard();
	}
}
