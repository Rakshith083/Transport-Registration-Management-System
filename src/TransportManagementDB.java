import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransportManagementDB {
	private static Connection con;
	public static Connection getConnection() throws Exception{
		// step1 load the driver class
		try {
			System.out.println("..............................");
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("..............................");

			// step2 create the connection object
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "momotc21");
			System.out.println("Database Connected");
			
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	 catch (SQLException e) {
						e.printStackTrace();
						}
		return null;
	}

	public static void main(String[] args)throws Exception {
		getConnection();
	}
}
