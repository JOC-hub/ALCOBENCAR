package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AccesoDB {

	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String URL = "jdbc:sqlite:dbSQLite/DATOS_USUARIO.db";
	
	private String driver;
	private String url;
	
	public AccesoDB() {
		driver = DRIVER;
		url = URL;
	}
	
	public Connection getConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		
		Connection con = DriverManager.getConnection(url);
		System.out.println("Conexión establecida con la BBDD");
		
		return con;

	}
}
