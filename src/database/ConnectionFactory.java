package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		String server = "localhost";
		String database = "nicenes";
		String user = "root";
		String pass = "tm35hp7a";
		String url = "jdbc:mysql://"+ server + "/" + database;
		
		Connection con=null;
		String dirverString = "com.mysql.jdbc.Driver";
		
		try {
			Class.forName(dirverString);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			try {
				pass="";
				con = DriverManager.getConnection(url, user, pass);
			} catch (SQLException e2) {
				e.printStackTrace();
			}
		}
		return con;
		//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
				
	}
}