package vn.iotstar.configs;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {

	private static String USERNAME = "root";
	private static String PASSWORD = "root";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/1twebst4";

	public static Connection getDatabaseConnection() throws SQLException { 
		try {
			Class.forName (DRIVER);
		} catch (ClassNotFoundException e) {
	    	    // TODO Auto-generated catch block
	    	    e.printStackTrace();
		}
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}

	//Test chương trình. Kích phải chuột chọn run as->java application 
	public static void main(String[] args) {
		try {
			new DBConnectMySQL();
			System.out.println(DBConnectMySQL.getDatabaseConnection());
		}catch(Exception e) {
	    	    e.printStackTrace();
		}
	}
}