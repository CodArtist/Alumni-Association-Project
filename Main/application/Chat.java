package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Chat implements Initializable{
@FXML
private JFXTextField search;
@FXML
private Label name;
@FXML
private Button cross,cross1;
public static String reciever;
@FXML
private AnchorPane anchor;
@FXML
private JFXListView<Button> lv,friends;
public String accept;
@FXML
private Button back;
private ObservableList<Button> friend = FXCollections.observableArrayList();
private ObservableList<Button> buttons = FXCollections.observableArrayList(); 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
		cross.translateXProperty().set(1000);
		cross1.setTranslateX(1000);
		
		lv.translateXProperty().set(700);
		friends.translateXProperty().set(-100);
		//cross.setPrefWidth(500);
	/*for(int i= 0;i<10;i++)
		
		{ Button b = new Button("harsh");
		b.setStyle("-fx-background-color:black; -fx-text-fill:white;");
		b.setOnMouseEntered(e-> b.setStyle("-fx-background-color:white; -fx-text-fill:black;"));
		b.setOnMouseExited(e -> b.setStyle("-fx-background-color:black; -fx-text-fill:white;"));
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("hello");
			}
			
		});
		
		//b.setStyle("-fx-text-fill:white;");
		buttons.add(b);
		
				}
	*/
		//lv.getItems().addAll(buttons);
		//String mess=null;
	/*	Connection con = Sqldatabase.dbconnect();
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select * from data where message = ?");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pst.setString(1, mess);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(mess);*/
	}
	
public void search(ActionEvent event ) throws SQLException
{
	Connection con = Sqldatabase.dbconnect();
	PreparedStatement pst;
	pst = con.prepareStatement("select * from data where name = ?");
	pst.setString(1, search.getText());
	ResultSet r = pst.executeQuery();
	if(r.next())
		{name.setText(search.getText());
		reciever  =search.getText();
		cross.translateXProperty().set(0);
		cross1.translateXProperty().set(0);

		search.setText("");
		}
		else
		{name.setText("Sorry no user found");
		cross.translateXProperty().set(1000);
		cross1.translateXProperty().set(1000);

		search.setText("");
		
		}
		}
public void sendmessage(ActionEvent event) throws IOException
{
   AnchorPane pane  = FXMLLoader.load(getClass().getResource("./fxml files/sendmessage.fxml"));
	anchor.getChildren().setAll(pane);
}
	
public void sendrequest(ActionEvent event) throws SQLException {
	Connection con = Sqldatabase.dbconnect();
	PreparedStatement pst = con.prepareStatement("insert into request(sender,status,reciever) values(?,?,?)");
pst.setString(1,Login.name);
pst.setString(2,"false");
pst.setString(3,Chat.reciever);
pst.executeUpdate();
}
public void friendreq(ActionEvent event) throws SQLException {
	Connection con = Sqldatabase.dbconnect();
	PreparedStatement pst = con.prepareStatement("select * from request where reciever = ? and status = 'false'");
	pst.setString(1, Login.name);
	ResultSet r = pst.executeQuery();
buttons.clear();
	while(r.next())
	{
		Button b = new Button(r.getString(1));
		b.setStyle("-fx-background-color:black; -fx-text-fill:white;");
		b.setOnMouseEntered(e-> b.setStyle("-fx-background-color:white; -fx-text-fill:black;"));
		b.setOnMouseExited(e -> b.setStyle("-fx-background-color:black; -fx-text-fill:white;"));
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
			lv.translateXProperty().set(700);
			Connection con1 = Sqldatabase.dbconnect();
			PreparedStatement pst2 = null;
			try {
				pst2 = con1.prepareStatement("update request set status='true' where sender =? and reciever = ?");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pst2.setString(1,b.getText());
				pst2.setString(2,Login.name);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				pst2.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		});
		buttons.add(b);
		
	}
	lv.getItems().clear();
		lv.getItems().addAll(buttons);
		lv.setTranslateX(0);

	
	}
	public void friends (ActionEvent event) throws SQLException, IOException
	{friend.clear();
	
			//friends.translateXProperty().set(10);
		Connection con = Sqldatabase.dbconnect();
	
		PreparedStatement pst = con.prepareStatement("select * from request where sender = ?  and status = 'true'");
		pst.setString(1,Login.name);
		ResultSet r = pst.executeQuery();
		while(r.next())
		{
			Button b = new Button(r.getString(3));
			b.setPrefWidth(150);
			b.setPrefHeight(56);
			Circle cir = new Circle();
			cir.setRadius(23);
			cir.setTranslateX(-20);
		    File file  = new File("./application/img/user.jpg");
		    
		    FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    Image img = new Image(fis);
		    cir.setFill(new ImagePattern(img));

		    thread4 tt = new thread4(cir,r.getString(3));
			tt.start();
			b.setGraphic(cir);
			b.setStyle("-fx-background-color:white; -fx-text-fill:black;");
			b.setOnMouseEntered(e-> b.setStyle("-fx-background-color:black; -fx-text-fill:white;"));
			b.setOnMouseExited(e -> b.setStyle("-fx-background-color:white; -fx-text-fill:black;"));
		
			
			b.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					Chat.reciever = b.getText();
					 AnchorPane pane = null;
					try {
						pane = FXMLLoader.load(getClass().getResource("./fxml files/sendmessage.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						anchor.getChildren().setAll(pane);
						
				}
				
			});
			friend.add(b);
		}
		friends.getItems().clear();
		friends.getItems().addAll(friend);
		//stage.close();
		TranslateTransition t=new TranslateTransition(Duration.seconds(1),friends);
		t.setFromX(-30);
		t.setToX(50);
		t.play();
		
	}
/*public void anim(ActionEvent event)
{
	TranslateTransition t=new TranslateTransition(Duration.seconds(1),search);
	t.setFromX(500);
	t.setToX(100);
	t.play();
}*/
	
	public void cross(ActionEvent event)
	{
		Platform.exit();
	}
	public void min(ActionEvent event)
	{
		Stage stage = (Stage)anchor.getScene().getWindow(); 
		stage.setIconified(true);

	}
	public void back(ActionEvent event ) throws IOException
	{
		Parent root = FXMLLoader.load(getClass().getResource("./fxml files/logsuccess.fxml"));
		Scene scene = back.getScene();
		anchor.getChildren().add(root);
		root.translateXProperty().set(-scene.getWidth());
		Timeline timeline = new Timeline();
		KeyValue kv = new KeyValue(root.translateXProperty(),0,Interpolator.EASE_IN);
		KeyFrame kf = new KeyFrame(Duration.seconds(0.5),kv);
		timeline.setOnFinished(event1->{
			anchor.getChildren().remove(scene);
		});
		timeline.getKeyFrames().add(kf);
		timeline.play();
	}
}
