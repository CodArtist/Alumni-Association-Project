package application;

import java.sql.Connection;
import java.sql.DriverManager;
//import com.mysql.jdbc.ResultSetMetaData;

public class Sqldatabase {

	Connection con= null;
	java.sql.PreparedStatement pst;
	public static Connection dbconnect()  
	{
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql:// sql12.freemysqlhosting.net:3306/sql12364066","sql12364066","y7WdPu3jd3");
		return conn;
		}
		
		catch(Exception e2)
		{
			System.out.println(e2);
			return null;
		}
		}
		
		
}
