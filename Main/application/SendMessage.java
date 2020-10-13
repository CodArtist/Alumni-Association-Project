package application;
import application.thread;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.thread2;
public class SendMessage implements Initializable  {
@FXML
public JFXListView<HBox>list;
@FXML
private JFXTextArea message;
public static ResultSet r1;
@FXML
private AnchorPane anchor;

@FXML
private Button back;
public static Boolean exit = false;
@FXML
private Label recievername;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		recievername.setText(Chat.reciever);
			SendMessage.exit=false;
		//Connection con1  = Sqldatabase.dbconnect();
				 
				PreparedStatement pst= null;
			/*	PreparedStatement stmt = null; 
				try {
					stmt = con1.prepareStatement("update log set sender=?,message=?,reciever=? where id = 1");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					stmt.setString(1,"d");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					stmt.setString(2,"d");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					stmt.setString(3,"d");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					stmt.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*try {
					pst2 =con1.prepareStatement("delete * from log where id = 1");
				} catch (SQLException e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					pst2.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			/*	try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					con1.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		*/	
				
				
				
			thread2 t= new thread2(list);
				t.start();
				
				
				
				
				
				
				Connection con = Sqldatabase.dbconnect();
				try {
					pst= con.prepareStatement("select * from message where (touser=? and fromuser=?) or (touser=? and fromuser=?)");
					//pst2 =  con.prepareStatement("select * from message where touser=? and fromuser=?");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						try {
							pst.setString(1,Login.name);
							pst.setString(2, Chat.reciever);
							pst.setString(3,Chat.reciever);
							pst.setString(4,Login.name);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ResultSet r = null;//r2= null;
						Boolean re1=true,re2=true;
						
						try {
							r = pst.executeQuery();
							//r2 =  pst2.executeQuery();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							/*do{
							if(r.next())	
							{	
								list.getItems().add(new Label(Chat.reciever +"\n"+ r.getString(2)));
							}
							else
								re1= false;
							if(r2.next())
								{list.getItems().add(new Label("You" + "\n" + r2.getString(2)));
								
								}
							else
								re2= false;
								
								}while(re1 || re2);*/
						String s;
							
							while(r.next())
							{
								
								if(r.getString(1).equals(Login.name))
								{s= "You";
								
								}
							else
								s = r.getString(1);
								
							HBox hbox  = new HBox();
							hbox.setPrefHeight(100);
							
						Label  label = new Label(s + "\n" + r.getString(2));
							label.setPrefWidth(160);
							
							//label.setMinHeight(100);
							if(s.equals("You"))
							{								
							label.setStyle("-fx-background-color:blue; -fx-text-fill:white;-fx-border-radius:30 30 30 30; -fx-background-radius:30 30 30 30;-fx-padding:0 0 0 30;-fx-font-weight:bold");

							hbox.getChildren().add(label);
							label.setTranslateX(700);
							}
							
							else
							{
							    label.setStyle("-fx-background-color:#dadada; -fx-text-fill:black;-fx-border-radius:30 30 30 30; -fx-background-radius:30 30 30 30;-fx-padding:0 0 0 30;");
							    Circle cir = new Circle();
							   // Color color = new Color();
							  //  cir.setStroke(Color.BLUE);
							    //cir.setStrokeWidth(5);
							    thread4 tt = new thread4(cir,Chat.reciever);
							    tt.start();
							    
		                      File file1  = new File("./application/img/user.jpg");
							    
							    
							    FileInputStream fis = null;
								try {
									fis = new FileInputStream(file1);
								} catch (FileNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							    Image img = new Image(fis);
							    cir.setFill(new ImagePattern(img));

							    hbox.getChildren().addAll(cir,label);
							    cir.setTranslateY(20);
							    cir.setRadius(32);
								label.setTranslateX(20);
								label.setTranslateY(40);
							    
							}

							list.getItems().add(hbox);
								
								
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							
							e.printStackTrace();
						}
						try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	}
	
	public void send (ActionEvent event ) throws SQLException {
		HBox hbox  = new HBox();
		hbox.setPrefHeight(100);
		Label  label = new Label("You" + "\n" + message.getText());
		label.setStyle("-fx-background-color:blue; -fx-text-fill:white;-fx-border-radius:30 30 30 30; -fx-background-radius:30 30 30 30;-fx-padding:0 0 0 30;-fx-font-weight:bold");
			label.setPrefWidth(160);
			//label.setMinHeight(100);
			
			hbox.getChildren().add(label);
			label.setTranslateX(700);

		
list.getItems().add(hbox);
		
		thread t = new thread(Login.name,message.getText(),Chat.reciever);
		t.start();
		
	/*	Connection con =  Sqldatabase.dbconnect();
		PreparedStatement pst =  con.prepareStatement("insert into message(fromuser,message,touser) values(?,?,?)");
		//pst.setString(1,/*Login.name*);
		//pst.setString(2,message.getText());
		//pst.setString(3,/*Chat.reciever*);
		//pst.executeUpdate();*/
		message.setText("");	

	}
	
	
	/*public void reload(ActionEvent event) {
	/*	Connection con  = Sqldatabase.dbconnect();
		 
		PreparedStatement pst= null,pst2 = null;
		try {
			pst = con.prepareStatement("select * from message where (touser=? and fromuser=?) or (touser=? and fromuser=?)");
			//pst2 =  con.prepareStatement("select * from message where touser=? and fromuser=?");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				try {
					pst.setString(1,Login.name);
					pst.setString(2, Chat.reciever);
					pst.setString(3,Chat.reciever);
					pst.setString(4,Login.name);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ResultSet r = null;//r2= null;
				Boolean re1=true,re2=true;
				
				try {
					r = pst.executeQuery();
					//r2 =  pst2.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			//	try {
					/*do{
					if(r.next())	
					{	
						list.getItems().add(new Label(Chat.reciever +"\n"+ r.getString(2)));
					}
					else
						re1= false;
					if(r2.next())
						{list.getItems().add(new Label("You" + "\n" + r2.getString(2)));
						
						}
					else
						re2= false;
						
						}while(re1 || re2);*/
			/*	String s;
				String space = null;
					
					while(r1.next())
					{if(r1.getString(1).equals("harsh"))
						{s= "You";
						 
						}
						
					else
					{	s = r1.getString(1);
					space="";
					}
						SendMessage.list.getItems().add(new Label(space + s + "\n" + space + r1.getString(2)));
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}*/
	
	public void cross(ActionEvent event)
	{
		Platform.exit();
		SendMessage.exit = true;
	}
	public void min(ActionEvent event)
	{
		Stage stage = (Stage)anchor.getScene().getWindow(); 
		stage.setIconified(true);

	}
	public void back(ActionEvent event ) throws IOException
	{SendMessage.exit=true;
		Parent root = FXMLLoader.load(getClass().getResource("./fxml files/chat.fxml"));
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
