package com.dam.ProyectoMaven.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.sqlite.SQLiteConfig;

// Clase que facilita el método para conectar con la BBDD
public class AccesoDBProp {

	private String driver;
	private String url;

	public AccesoDBProp() {

		FileInputStream fis = null;
		Properties prop = new Properties();

		try {
			fis = new FileInputStream("configuracion.properties");

			prop.load(fis);

			driver = prop.getProperty("driver");
			url = prop.getProperty("url");

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Connection getConexion() throws ClassNotFoundException, SQLException {
		Class.forName(driver);

		// añadimos una configuracion de sqlite para añadir claves foraneas
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);

		Connection con = DriverManager.getConnection(url, config.toProperties());
		System.out.println("Conexión establecida con la BBDD");

		return con;

	}

}
