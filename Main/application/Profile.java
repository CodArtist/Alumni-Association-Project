package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Profile implements Initializable {
@FXML
private Label prof;
@FXML
private Label name;
@FXML
private Label branch;
	
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	//
	// TODO Auto-generated method stub	

	name.setText(Login.name);
	
	name.translateXProperty().set(747);
	branch.translateXProperty().set(747);
  TranslateTransition trans= new TranslateTransition(Duration.seconds(0.4),prof);
	trans.setFromX(747);
	trans.setToX(0);
	trans.play();
	
	TranslateTransition trans1= new TranslateTransition(Duration.seconds(0.5),name);
	trans1.setFromX(757);
trans1.setToX(0);
trans1.play();
	
	
	TranslateTransition trans2= new TranslateTransition(Duration.seconds(0.6),branch);
	trans2.setFromX(767);
	trans2.setToX(0);
	trans2.play();

	
	

}

}
