package application;

import java.io.IOException;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Mainpage {
	public static 
	String buttonclicked = null;  
	
	@FXML 
	AnchorPane anchor;
	@FXML
	private Button button;
	
	public void Student(ActionEvent event) throws IOException {
	buttonclicked = "stu";
		Parent root = FXMLLoader.load(getClass().getResource("./fxml files/Login.fxml"));
Scene scene = button.getScene();
anchor.getChildren().add(root);
root.translateYProperty().set(scene.getHeight());
Timeline timeline = new Timeline();
KeyValue kv = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
KeyFrame kf = new KeyFrame(Duration.seconds(0.5),kv);
timeline.setOnFinished(event1->{
	anchor.getChildren().remove(scene);
});
timeline.getKeyFrames().add(kf);
timeline.play();
}
	
	
	
	public void Alumni(ActionEvent event) throws IOException {
		buttonclicked= "alu";
		Parent root = FXMLLoader.load(getClass().getResource("./fxml files/Login.fxml"));
Scene scene = button.getScene();
anchor.getChildren().add(root);
root.translateYProperty().set(scene.getHeight());
Timeline timeline = new Timeline();
KeyValue kv = new KeyValue(root.translateYProperty(),0,Interpolator.EASE_IN);
KeyFrame kf = new KeyFrame(Duration.seconds(0.5),kv);
timeline.setOnFinished(event1->{
	anchor.getChildren().remove(scene);
});
timeline.getKeyFrames().add(kf);
timeline.play();	

}
	
	
	
	
	public void close(ActionEvent event)
	{
		Platform.exit();
	}
	public void Min(ActionEvent event)
	{
		Stage stage = (Stage)anchor.getScene().getWindow(); 
		stage.setIconified(true);
	}
	
}
