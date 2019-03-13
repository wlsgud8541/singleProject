package com.project.logindb;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
	static Connection con;
	public DBconnect() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="project";
		String pass="123456";
		
		con=DriverManager.getConnection(url,user,pass);
		System.out.println("접속성공");
	}
	public static Connection getUserConnection() throws Exception {
		if (con==null) {
			new DBconnect();
		}
		return con;
	}
	
	
}
