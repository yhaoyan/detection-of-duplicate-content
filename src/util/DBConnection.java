package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/homework_detecting_system?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = ""; 
	
	private DBConnection(){
		
	}

	
	static{
		try{
			Class.forName(driverName);
		} catch(ClassNotFoundException e){
			e.printStackTrace();
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,user,password);
	}
	
	public static void close(ResultSet rs,Statement st,Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(st != null){
					st.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				try{
					if(conn != null){
						conn.close();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
	}
}











