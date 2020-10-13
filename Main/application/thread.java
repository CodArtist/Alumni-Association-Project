package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class thread extends Thread {
private String sender;
private String message;
private String reciever;
	
	public thread(String a,String b,String c)
	{
		sender = a;
		message=b;
		reciever = c;
	}
	
	public void run()
{
		Connection con1 =  Sqldatabase.dbconnect();
		PreparedStatement pst = null;
		try {
			pst = con1.prepareStatement("insert into message(fromuser,message,touser) values(?,?,?)");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.setString(1,Login.name);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.setString(2,message);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.setString(3,Chat.reciever);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pst.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Connection con2 = Sqldatabase.dbconnect();
		PreparedStatement pst3=null;
	try {
	pst3 = con2.prepareStatement("update log set sender = ?, message= ?,reciever=? where id = 1");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst3.setString(1,sender);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst3.setString(2,message);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst3.setString(3,reciever);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try {
	pst3.executeUpdate();
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


}
}
