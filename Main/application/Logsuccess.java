package application;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.mysql.cj.jdbc.Blob;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Logsuccess implements Initializable {
	@FXML
	private Button profile;

	@FXML
	private Button gallery;

	@FXML
	private Button password;

	@FXML
	private Button logout;
	@FXML
	private AnchorPane anchor,mainanchor;
	@FXML
	private Label label,label1;
@FXML
private Circle profimg;
	
	public void profile(ActionEvent event ) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/profile.fxml"));
		anchor.getChildren().setAll(pane);
	}

public void gallery(ActionEvent event ) throws IOException {
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/gallery.fxml"));
		mainanchor.getChildren().setAll(pane);
	}

public void changepass(ActionEvent event ) throws IOException {
	
	AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/Changepassword.fxml"));
	anchor.getChildren().setAll(pane);
}

public void logout(ActionEvent event ) throws IOException {
	
	AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/Login.fxml"));
	mainanchor.getChildren().setAll(pane);
}
public void chat(ActionEvent event ) throws IOException {
	
	AnchorPane pane = FXMLLoader.load(getClass().getResource("./fxml files/chat.fxml"));
    mainanchor.getChildren().setAll(pane);
}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		thread3 th= new thread3(profimg,Login.name);
		th.start();
		


		
		//System.out.println(input);
		/*Image img  = new Image(input);
		profimg.setFill(new ImagePattern(img));*/
		
		
		label.setText(Login.name);
		label1.setText(Login.name);
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
	public void editimg(ActionEvent event) throws IOException, SQLException {
		FileChooser filebrowse  = new FileChooser();
		filebrowse.setTitle("profile image");
		Stage stage  = (Stage)anchor.getScene().getWindow();
		
    File file  = filebrowse.showOpenDialog(stage);
	String fpath;	
    FileInputStream fis  = new FileInputStream(file);
    fpath = file.getAbsolutePath();
  // fpath = fpath.re("\\","\\\\");
						//byte[] data c new byte[(int) file.length()]; 
		
	/*	BufferedImage bImage = ImageIO.read(file);
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "jpg", bos );
	      byte [] b = bos.toByteArray();
	      for(int i = 0;i<b.length;i++)
		System.out.print(b[i]);*/
		Connection con = Sqldatabase.dbconnect();
		PreparedStatement pst = con.prepareStatement("UPDATE data SET profimg=? WHERE name=?"); 
	//FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\shubham\\scanner_20170305_181634.jpg");
		InputStream is = new FileInputStream(new File(file.getAbsolutePath()));
		
		pst.setBlob(1,is);
		pst.setString(2,Login.name);
		pst.executeUpdate();
		Image img = new Image(fis);
		profimg.setFill(new ImagePattern(img));
//String filename	= file.getPath();	
/*BufferedWriter write = new BufferedWriter(new FileWriter("C:\\Users\\user\\eclipse-workspace\\Main\\application\\Data Base\\userdata.txt"));
write.newLine();
write.write(Login.name);
write.newLine();
write.write(filename);
		write.close();
	*/	
	}
	

}
