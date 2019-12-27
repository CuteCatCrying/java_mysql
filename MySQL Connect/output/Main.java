package kelasMySQLConnectOutput;

import java.sql.*;

public class Main{
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Database Tutorial");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbjual","zera","mean123");

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from tbrg");
			System.out.println("Database konek");
			while(rs.next()){
				System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}