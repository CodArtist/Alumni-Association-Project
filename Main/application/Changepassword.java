package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Changepassword implements Initializable {
@FXML
private JFXPasswordField newpass,confirmpass;
@FXML
private Label changepass,message;
@FXML
private Button change;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		TranslateTransition trans = new TranslateTransition(Duration.seconds(0.4),changepass);
		trans.setFromX(747);
		trans.setToX(0);
		trans.play();
		
		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(0.5),newpass);
		trans1.setFromX(757);
		trans1.setToX(0);
		trans1.play();
		
		TranslateTransition trans2 = new TranslateTransition(Duration.seconds(0.6),confirmpass);
		trans2.setFromX(767);
		trans2.setToX(0);
		trans2.play();

		TranslateTransition trans3 = new TranslateTransition(Duration.seconds(0.7),change);
		trans3.setFromX(777);
		trans3.setToX(0);
		trans3.play();

	}
	public void submit(ActionEvent event) throws IOException
	{  File temp = File.createTempFile("temp", ".txt");
	File data =new File("C:\\Users\\user\\eclipse-workspace\\Main\\application\\Data Base\\Data.txt"); 
	   BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\user\\eclipse-workspace\\Main\\application\\Data Base\\Data.txt"));
	   BufferedWriter write = new BufferedWriter(new FileWriter(temp));
       String nam = Login.name;
	   String s = "";
	 if(newpass.getText().equals(confirmpass.getText())) 
	 {message.setText("");
	 
	  do {
		   
		   s = read.readLine();
		 		  if(nam.equals(s))
		  {	   
			 
			   write.write(Login.name);
			   write.newLine();
			   write.write(newpass.getText());
			   write.newLine();
			   s = read.readLine();

		  }
		   
		   else
		   
		   { if(s!= null)
		   { write.write(s);
		   write.newLine();
		  
		   }
		  /* else
		   write.newLine();*/
		   		   
		   }
	  
	   }while(s!=null);
	
	
	newpass.setText("");
	confirmpass.setText("");
		   read.close();
	   write.close();
	  		
		
		
		temp.renameTo(data);

	 }
	
	 else
	 { 
		 message.setText("Password not matched");
	}
	}
	
	

}
