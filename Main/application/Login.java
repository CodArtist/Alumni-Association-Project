package application;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
public class Login implements Initializable  {
	
	public static 
	String logeduser, name,Pass;
	@FXML
	private  TextField t1;
	@FXML
	private  JFXPasswordField t2;
	@FXML
	private Label lb;
	@FXML
	private AnchorPane anchor;
	@FXML
	private Button back;
	@FXML
	private Label label;
	public void Method(ActionEvent event) throws IOException, SQLException 
	{     
				
		Connection con = Sqldatabase.dbconnect();
		
		if (con==null)
	lb.setText("no internet");
		else {
		
	int t = 0;
	String dname,dpass;
	
	name = t1.getText();
	Pass= t2.getText();
	String s;
	
	PreparedStatement pst = con.prepareStatement("select name,password from data where name =? and password=?");
	pst.setString(1,name);
	pst.setString(2,Pass);
	
	ResultSet r = pst.executeQuery();
	if(r.next())
	{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/logsuccess.fxml"));
		anchor.getChildren().setAll(pane);
		
		
	}
	else
		lb.setText("invalid login");
	
	/*BufferedReader read =new BufferedReader(new FileReader("C:\\Users\\user\\eclipse-workspace\\Main\\application\\Data Base\\Data.txt"));
	do
	{
		
	s	=read.readLine();
		
		if(t==1)
	{if(Pass.equals(s))
	{	logeduser = name;
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/logsuccess.fxml"));
	anchor.getChildren().setAll(pane);

	break;
	}
	else
		{lb.setText("Wrong Password");
break;	
		}
	}
	
		
	
	
		
		
		if(t!=1)
	{if(name.equals(s))
		{t=1;		
		}
	
	}
		

	
	}while(s!=null);

	
	
	read.close();*/
		}
	}
	
	public void signup(ActionEvent event) throws IOException
	{/*BufferedReader read =new BufferedReader(new FileReader("C:\\Users\\user\\eclipse-workspace\\Main\\application\\Data Base\\Data.txt"));
	System.out.print(read.readLine());
	read.close();*/

		AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/Signup.fxml"));
		anchor.getChildren().setAll(pane);

		
	}
	public void Back(ActionEvent event) throws IOException {		
		/*AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/MainPage.fxml"));
		anchor.getChildren().setAll(pane);*/
		
		Parent root = FXMLLoader.load(getClass().getResource("./fxml files/MainPage.fxml"));
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
	public void close(ActionEvent event)
	{
		Platform.exit();
	}
	public void Min(ActionEvent event)
	{
		Stage stage = (Stage)anchor.getScene().getWindow(); 
		stage.setIconified(true);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		if(Mainpage.buttonclicked.equals("stu"))
			label.setText("Student");
		else
			label.setText("Alumni");
	}

	
}
