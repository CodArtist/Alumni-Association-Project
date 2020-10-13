package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Gallery implements Initializable{
@FXML
private JFXListView list;
@FXML
private AnchorPane anchor;
@FXML
private Button back;
public String imgaddress = "./application/img/icons8-love-48.png";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HBox h  = new HBox();
		Label l  = new Label("Add a Picture");
		Button add  = new Button();
		add.setGraphic(new ImageView("./application/img/icons8-plus-64.png"));
		add.setStyle("-fx-background-color:white;");
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				FileChooser filebrowse  = new FileChooser();
				filebrowse.setTitle("upload picture");
				Stage stage  = (Stage)anchor.getScene().getWindow();
				
		    File file  = filebrowse.showOpenDialog(stage);
		    
		    Connection con3 = Sqldatabase.dbconnect();
			PreparedStatement pst = null;
			try {
				pst = con3.prepareStatement("INSERT INTO images(user,image) VALUES(?,?)");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		//FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\shubham\\scanner_20170305_181634.jpg");
			InputStream is = null;
			try {
				is = new FileInputStream(new File(file.getAbsolutePath()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pst.setBlob(2,is);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

try {
	con3.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst.close();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
			}
			
		}
				);
		
			
		
		h.getChildren().addAll(l,add);
		list.getItems().add(h);
		
		
		Connection con  = Sqldatabase.dbconnect();
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement("select * from request where sender = ?  and status = 'true'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pst.setString(1,Login.name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet r = null;
		try {
			r = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(r.next())
			{
				
				Connection con1 = Sqldatabase.dbconnect();
			PreparedStatement pst1 = con1.prepareStatement("SELECT * FROM images WHERE user = ? AND image IS NOT NULL");
			pst1.setString(1,r.getString(3));
			ResultSet r1 = pst1.executeQuery();
			while(r1.next())
			{			Label name = new Label(r1.getString(1));
			Label nolikes = new Label("1");
			Label nocomments = new Label("2");
			Circle cir = new Circle();
			cir.setRadius(25);
			Button like = new Button();
		
			ImageView image1 = new ImageView(imgaddress);
			like.setGraphic(image1);
			like.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			if(imgaddress.equals("./application/img/icons8-love-48.png"))
			{imgaddress= "./application/img/icons8-love-48 (1).png";
					like.setGraphic(new ImageView(imgaddress));
					nolikes.setText("2");
			}
			else
				 {imgaddress = "./application/img/icons8-love-48.png";
					like.setGraphic(new ImageView(imgaddress));
				 nolikes.setText("1");
				 
				 }
				 }
				
				
			});
			Button comment = new Button();
			comment.setGraphic(new ImageView("./application/img/icons8-chat-bubble-50.png"));
			byte b[] = null;
			java.sql.Blob blob = r1.getBlob("image");
			//System.out.println(r.getString("name")+r5.getString("password"));
		 b = blob.getBytes(1,(int) blob.length());
							FileOutputStream fout = null;
					try {
						fout = new FileOutputStream("hg.jpg");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    try {
						fout.write(b);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    try {
						fout.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				    FileInputStream fis = null;
					try {
						fis = new FileInputStream("hg.jpg");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
						Image img = new Image(fis);

			
			
					
			
			ImageView image = new ImageView(img);
					
							HBox hbox = new HBox();
				
				hbox.getChildren().addAll(cir,name,image,like,comment,nolikes,nocomments);
			cir.setTranslateX(0);
			cir.setTranslateY(0);

			name.setTranslateX(20);
			name.setTranslateY(0);
			name.setStyle("-fx-text-fll:black; -fx-background-color:white;");

			nolikes.setTranslateX(-360);
			nolikes.setTranslateY(350);
			nolikes.setPrefWidth(32);
			nolikes.setPrefHeight(36);
			nolikes.setStyle("-fx-text-fill:black;-fx-background-color:white;");

			nocomments.setTranslateX(-300);
			nocomments.setTranslateY(350);
			nocomments.setPrefWidth(32);
			nocomments.setPrefHeight(36);
			nocomments.setStyle("-fx-text-fill:black;,-fx-background-color:white;");
			
			image.setTranslateX(30);
			image.setTranslateY(100);
			image.setFitHeight(190);
			image.setFitWidth(338);

			
			like.setTranslateX(-250);
			like.setTranslateY(300);
			like.setPrefWidth(33);
			like.setPrefHeight(36);
            like.setStyle("-fx-background-color:white;"); 

			comment.setTranslateX(-230);
			comment.setTranslateY(305);
			comment.setPrefWidth(33);
			comment.setPrefHeight(36);
            comment.setStyle("-fx-background-color:white;");
			
			
			list.getItems().add(hbox);
			hbox.setPrefHeight(424);
			}
			con1.close();
			pst1.close();
	
			}
			con.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		// TODO Auto-generated method stub
		/* TranslateTransition trans= new TranslateTransition(Duration.seconds(0.4),gallerylb);
			trans.setFromX(747);
			trans.setToX(0);
			trans.play();
			
			TranslateTransition trans1= new TranslateTransition(Duration.seconds(0.4),images);
			trans1.setFromY(475);
			trans1.setToY(50);
			trans1.play();*/
			
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

	public void cross(ActionEvent event)
	{
		Platform.exit();
	}
	public void min(ActionEvent event)
	{
		Stage stage = (Stage)anchor.getScene().getWindow(); 
		stage.setIconified(true);

	}

}
