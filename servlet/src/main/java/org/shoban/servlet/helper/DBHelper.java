package org.shoban.servlet.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;
import java.util.Properties;

public class DBHelper {
	private Connection con;
	public String dbms;
	public String jarFile;
	public String dbName;
	public String userName;
	public String password;
	public String urlString;

	private String driver;
	private String serverName;
	private int portNumber;
	private Properties prop;

	public DBHelper(String fileName) throws FileNotFoundException, InvalidPropertiesFormatException, IOException {
		setProperties(fileName);

	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		Class.forName("com.mysql.jdbc.Driver");
		String currentUrlString = null;

		if (this.dbms.equals("mysql")) {
			currentUrlString = "jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/";
			conn = DriverManager.getConnection(currentUrlString, connectionProps);

			this.urlString = currentUrlString + this.dbName;
			conn.setCatalog(this.dbName);
		} else if (this.dbms.equals("derby")) {
			this.urlString = "jdbc:" + this.dbms + ":" + this.dbName;

			conn = DriverManager.getConnection(this.urlString + ";create=true", connectionProps);

		}
		System.out.println("Connected to database");
		return conn;
	}

	private void setProperties(String fileName)
			throws FileNotFoundException, IOException, InvalidPropertiesFormatException {
		this.prop = new Properties();
		FileInputStream fis = new FileInputStream(fileName);
		prop.loadFromXML(fis);

		this.dbms = this.prop.getProperty("dbms");
		this.jarFile = this.prop.getProperty("jar_file");
		this.driver = this.prop.getProperty("driver");
		this.dbName = this.prop.getProperty("database_name");
		this.userName = this.prop.getProperty("user_name");
		this.password = this.prop.getProperty("password");
		this.serverName = this.prop.getProperty("server_name");
		this.portNumber = Integer.parseInt(this.prop.getProperty("port_number"));

		System.out.println("Set the following properties:");
		System.out.println("dbms: " + dbms);
		System.out.println("driver: " + driver);
		System.out.println("dbName: " + dbName);
		System.out.println("userName: " + userName);
		System.out.println("serverName: " + serverName);
		System.out.println("portNumber: " + portNumber);

	}

	public boolean isUserValid(String user, String pwd) throws SQLException, ClassNotFoundException {
		boolean isUserExist = false;
		Map<String,String> userMap= new HashMap<>();
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from user where loginName = ?");
		preparedStatement.setString(1, user);
		//preparedStatement.setString(2, pwd);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String passowrd = resultSet.getString(3);
			if(pwd.equals(passowrd)){
				isUserExist = true;
			}
	      }
		
		return isUserExist;
	}

	public static void main(String[] args)
			throws SQLException, FileNotFoundException, InvalidPropertiesFormatException, IOException, ClassNotFoundException {
		String filePath = "C:\\shoban\\myproject\\training\\servlet\\src\\main\\resources\\mysql-sample-properties.xml";
		DBHelper helper = new DBHelper(filePath);
		boolean isUserExist = helper.isUserValid("shoban", "test123");
	}
}
