package application;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Signup {
	@FXML
	private AnchorPane anchor;
	@FXML
	private JFXTextField t1;
	@FXML
	private JFXPasswordField t2;
	@FXML
	private JFXPasswordField t3;
	
	@FXML
	private Label lb;
	@FXML
	private Label lb2;
	@FXML
	private Label lb3;
	
	public
	Connection con= null;

	
	public void login(ActionEvent event) throws IOException
	{
		AnchorPane pane =FXMLLoader.load(getClass().getResource("./fxml files/Login.fxml"));
		anchor.getChildren().setAll(pane);
	}
	public void register(ActionEvent event) throws IOException, ClassNotFoundException, SQLException
	{
		
		con = Sqldatabase.dbconnect();
		
		
		
		
		
		
		
		
		
		
		
		String name = t1.getText();
	String Pass = t2.getText();
if(!(Pass.isEmpty()||name.isEmpty()||t3.getText().isEmpty()))
	{if(!Pass.equals(t3.getText()))
		lb.setText("Password not Matched");
	else
	{	lb.setText("");

//System.out.print(Pass);
		//BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\users\\eclipse-workspace\\Main\\application\\Data Base\\Data.txt",true)) ;
		
		PreparedStatement pst=con.prepareStatement("insert into data(name,password) values(?,?)");
		pst.setString(1,name);
		pst.setString(2,Pass);
		pst.executeUpdate();
		
		/*write.write(name);
		write.newLine();
		write.write(Pass);
		write.newLine();
		
		
		write.close();*/
	}
	}
else
{if(Pass.isEmpty())
{
	lb2.setText("Enter Password");
}
else
	lb2.setText("");
if(name.isEmpty())
{
	lb3.setText("Enter name");
}
else
	lb3.setText("");

if(t3.getText().isEmpty())
{
	lb.setText("Confirm Password");
}
else
	lb.setText("");
}
	
	
	
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












