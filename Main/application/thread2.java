package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXListView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class thread2 extends Thread {
	@FXML
	private JFXListView<HBox> list1;
	
public thread2(JFXListView<HBox> l)
{
	this.list1 = l;
}
public void run()
{
	while(!SendMessage.exit)
	{Boolean del = false;
			Connection con  = Sqldatabase.dbconnect();
		 
		PreparedStatement pst= null;
				
			try {
				pst = con.prepareStatement("select * from log where id = 1 and sender = ? and reciever=?");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				pst.setString(1,Chat.reciever);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			try {
				pst.setString(2,Login.name);
			} catch (SQLException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			ResultSet r = null;
			try {
				r = pst.executeQuery();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				if(r.next())
				{					HBox hbox  = new HBox();
					hbox.setPrefHeight(100);
					
				Label label = null;
				try {
					label = new Label(Chat.reciever + "\n" + r.getString(3));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					label.setPrefWidth(160);

					 label.setStyle("-fx-background-color:#dadada; -fx-text-fill:black;-fx-border-radius:30 30 30 30; -fx-background-radius:30 30 30 30;-fx-padding:0 0 0 30;");
					    Circle cir = new Circle();
					   // Color color = new Color();
					   // cir.setStroke(Color.BLUE);
					    //cir.setStrokeWidth(5);
					    File file1 = new File("./application/img/hh.jpg");
					    if(file1.length()==0)
					    {file1  = new File("./application/img/user.jpg");
					    
					    					    
					    }
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
						list1.getItems().add(hbox);
					    del =true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(del)
		{
						Connection con1  = Sqldatabase.dbconnect();
			 
			PreparedStatement stmt = null; 
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
			
			try {
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

			del= false;
				}
		
		
try {
	con.close();
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst.close();
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
try {
	Thread.sleep(5000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	
	
	}
	
	
	
	
}
}
