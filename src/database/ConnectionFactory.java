package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		String server = "localhost";
		String database = "nicenes";
		String user = "root";
		String pass = "fiap";
		String url = "jdbc:mysql://"+ server + "/" + database;
		
		Connection con;
		String test = "com.mysql.jdbc.Driver";
		try {
			
			try {
				Class.forName(test);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con = DriverManager.getConnection(url, user, pass);
			return con;
		} catch (SQLException e) {
			try {
				//pass = "";
				//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				//con = DriverManager.getConnection(url, user, pass);
				//return con;
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
	}
}