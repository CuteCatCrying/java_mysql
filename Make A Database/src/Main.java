import java.util.Scanner;
import java.sql.SQLException;

public class Main{
	private static Koneksi conn = new Koneksi();
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args){
		boolean ulang = true;
		String pilihanUser;
		String user,pass;
		String namaDatabase;
		String namaTable;
		int banyakField;

		while(ulang){
			System.out.println();
			System.out.println("==========================");
			System.out.println("||\tDatabase\t||");
			System.out.println("|| 1. Login to MySQL\t||");
			System.out.println("|| 2. Make A Database\t||");
			System.out.println("|| 3. Change Database\t||");
			System.out.println("|| 4. Create Table\t||");
			System.out.println("|| 5. Keluar\t||");
			System.out.println("==========================");
			System.out.print("Pilih ? ");
				pilihanUser = in.next();

			System.out.println();
			switch(pilihanUser){
				case "1" : {
					try{
						System.out.println("\tLogin to MySQL");
						System.out.print("Username\t: ");
							user = in.next();
						System.out.print("Password\t: ");
							pass = in.next();

						conn.getConn(user,pass);

						if(conn.getStatus()){
							System.out.println("Berhasil terhubung ke MySQL");
						}
					}catch(Exception e){
						// System.out.println(e.getMessage());
						System.out.println("Invalid Password or Username");
					}
					break;
				}
				case "2" : {
					try{
						System.out.println("\tMake A Database");
						System.out.print("Nama Database\t: ");
							namaDatabase = in.next();
						conn.createDatabase(namaDatabase);
					}catch(NullPointerException e){
						System.out.println("Anda Belum Login");
						break;
					}
					break;
				}
				case "3" : {
					System.out.println("\tChange Database");
					System.out.print("Nama Database\t: ");
						namaDatabase = in.next();
					conn.changeDatabase(namaDatabase);
					break;
				}
				case "4" : {
					System.out.println("\tCreate Tabel");
					System.out.println("Note : Posisi primary key ada pada field pertama");
					System.out.print("Nama Table\t: ");
						namaTable = in.next();
					System.out.print("Banyak field\t: ");
						banyakField = in.nextInt();
					conn.createTable(namaTable, banyakField);
				}
				case "5" : {
					ulang = false;
				}
			}
		}
	}
}