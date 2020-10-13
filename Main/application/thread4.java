package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class thread4 extends Thread {
	@FXML
	private Circle profimg;
	private String name;
	public thread4(Circle c,String n)
	{
		this.profimg = c;
		this.name=n;
	}

public void run()
{
	Connection con1 = Sqldatabase.dbconnect();
	PreparedStatement pst5 = null;
	try {
		pst5 = con1.prepareStatement("select * from data where name = ?");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} 
	try {
		pst5.setString(1,name);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//FileInputStream fis = new FileInputStream("C:\\Users\\user\\Desktop\\shubham\\scanner_20170305_181634.jpg");
	//InputStream is = new FileInputStream(new File(file.getAbsolutePath()));
	
//	pst.setBlob(2,is);
	byte b[] = null;
	ResultSet r5 = null;
	try {
		r5 = pst5.executeQuery();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		if(r5.next())
		{
			java.sql.Blob blob = r5.getBlob("profimg");
		 b = blob.getBytes(1,(int) blob.length());
			
		}
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//FileInputStream fis =  binaryStream;

if(b!=null)
		{FileOutputStream fout = null;
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
	
	
	profimg.setFill(new ImagePattern(img));
		
	try {
		fis.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
	
	try {
		con1.close();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}
