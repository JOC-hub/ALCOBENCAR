package main;

import java.sql.SQLException;

import db.AccesoDB;


public class TestConexion {

	public static void main(String[] args) {
		AccesoDB adb = new AccesoDB();
		
		try {
			adb.getConexion();
		} catch (ClassNotFoundException e) {
			System.out.println("El driver no es correcto");
		} catch (SQLException e) {
			System.out.println("La url no es correcta");
		}

	}

}
