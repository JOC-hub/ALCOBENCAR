package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class AccesoDB {

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:dbSQLite/DB_ALCOBENCAR.db";
	
	private String driver;
	private String url;
	
	public AccesoDB() {
		driver = DRIVER;
		url = URL;
	}
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		
		Connection con = DriverManager.getConnection(url,  config.toProperties());
		System.out.println("Conexi�n establecida con la BBDD");
		
		return con;

	}
}
