

import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionJDBC {
	
	//Class for getting DBConnection.Making "conn" object static, to avoid multiple connections being opened
	
	private static Connection conn;
	
	
	public static Connection getDBConnect() {

		

		String url = "jdbc:mysql://sql1.njit.edu:3306/sm956";
	    String ucid ="sm956";
	   String dbpassword ="sursum24";
	
	if(conn==null){
		try {
		
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url,ucid,dbpassword);
		
		return conn;
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		}else{
		return conn;
		}
		return null;
	
		}
	
}
