package cn.tomleung.dbcon;

import java.sql.*;

public class DBConnection {
	private final String DBNAME = "myshop";
	private final String DBUSER = "myshop";
	private final String DBPWD = "myshop";
	private final String DBURL = "jdbc:mysql://Localhost:3306/" + DBNAME + "?useSSL=false";
	private final String DBDRIVER = "com.mysql.jdbc.Driver";
	private Connection con = null;
	
	public DBConnection(){
		try{
			Class.forName(DBDRIVER);
			con=DriverManager.getConnection(DBURL,DBUSER,DBPWD);
		}catch(ClassNotFoundException e) {
			System.out.println("创建数据库驱动不成功");
		}catch(SQLException e){
			System.out.println("数据库连接失败");
		}
	}
	
	public Connection getConnection(){
		return con;
	}
	
	public void close(){
		try{
			con.close();
		}catch(Exception e){
			System.out.println("关闭数据库连接失败");
		}
	}
}
