import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;

public class Koneksi{
	private Scanner in = new Scanner(System.in);
	private String user;
	private String pass;
	private String host;
	private Connection conn;

	public void getConn(String user, String pass){
		this.user = user;
		this.pass = pass;
		this.host = "jdbc:mysql://localhost:3306/";

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn = DriverManager.getConnection(this.host,this.user,this.pass);
		}catch(SQLException e){
			// System.out.println(e);
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}

	public void createDatabase(String db){
		try{
			String sql = "CREATE DATABASE "+db+"";
			Statement st = this.conn.createStatement();
			st.executeUpdate(sql);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public boolean getStatus(){
		try{
			if(!this.conn.isClosed()){
				return true;
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return false;
	}

	public void changeDatabase(String db){
		try{
			host = String.format("jdbc:mysql://localhost:3306/%s",db);
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(host,user,pass);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}
	}

	public void createTable(String namaTable, int banyakField){
		String sql;
		String[] namaField = new String[banyakField];

		if(host.equals("jdbc:mysql://localhost:3306/")){
			System.out.println("Database belum di-use");
			return;
		}

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Statement st = conn.createStatement();

			for(int i=0; i<banyakField; i++){
				System.out.print("Nama Field\t("+(i+1)+"): ");
					namaField[i] = in.next();

				if(i == 0){
					sql = String.format("CREATE TABLE %s (%s varchar(25) primary key)",namaTable,namaField[0]);
				}else{
					sql = String.format("ALTER TABLE %s ADD %s varchar(25)",namaTable,namaField[i]);
				}
				st.executeUpdate(sql);
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
	}
}