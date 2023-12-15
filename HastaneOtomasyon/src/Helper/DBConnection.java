package Helper;

import java.sql.*;

public class DBConnection {

	Connection c = null;

	public DBConnection() {

	}
public Connection connDB() {
		try {
			c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/hastane?user=root");
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return c;
	}

}
