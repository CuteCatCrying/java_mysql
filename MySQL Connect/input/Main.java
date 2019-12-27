package kelasMySQLConnectInput;

import java.sql.*;

public class Main{
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Database Tutorial");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbsampah","zera","mean123");

			Statement st = conn.createStatement();

			String sql = "insert into mahasiswa values ('1811081030','Zukron Alviandy R','Jln. Dr Mohd Hatta')";

			st.executeUpdate(sql);
			System.out.println("Insert Complete");
		}catch(Exception e){
			System.out.println(e);
		}
	}
}