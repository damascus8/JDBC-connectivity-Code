package testconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface Info {
	
	String mysql_pkg="com.mysql.jdbc.Driver";
	String url="jdbc:mysql://localhost:3306/student";
	String user="root";
	String password="root";
	
	
}
