package tomcatsample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ProxoolDemo {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("proxool.mysql");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}