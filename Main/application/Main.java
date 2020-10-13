package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(getClass().getResource("./fxml files/MainPage.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());			
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			//primaryStage.setMaximized(true);
			Rectangle2D screen = Screen.getPrimary().getBounds();
			primaryStage.show();
			
			
						} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
				launch(args);
	}
}
