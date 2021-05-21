package com.dam.ProyectoMaven.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.dam.ProyectoMaven.model.Piloto;

public class PilotosPersistencia {
	
	//private AccesoDB adb;
	private AccesoDBProp adb;

	public PilotosPersistencia() {
		adb = new AccesoDBProp();
	}
	
	public ArrayList<Piloto> selectPilotos() {
		ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			String query = "SELECT NUMERO, NOMBRE, EDAD, NACIONALIDAD, ESCUDERIA FROM PILOTOS";
			rslt = stmt.executeQuery(query);
			
			int num;
			String nom;
			String nac;
			String esc;
			int edad;
			while (rslt.next()) {
				num = rslt.getInt(1);
				nom = rslt.getString(2);
				nac = rslt.getString(4);
				esc = rslt.getString(5);
				edad = rslt.getInt(3);
				
				listaPilotos.add(new Piloto(num, nom, edad, esc, nac));
				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Problemas con el driver");
		} catch (SQLException e) {
			System.out.println("Problemas con BBDD");
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaPilotos;
		
	}
	
	public ArrayList<String> selectDistinctNacion() {
		ArrayList<String> listaNacionalidades = new ArrayList<String>();
		String query = "SELECT DISTINCT NACIONALIDAD FROM PILOTOS";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			String nac;
			while (rslt.next()) {
				nac = rslt.getString(1);
				
				listaNacionalidades.add(nac);
				
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Problemas con el driver");
		} catch (SQLException e) {
			System.out.println("Problemas con BBDD");
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaNacionalidades;
	}

	public ArrayList<Piloto> selectPilotosNacion(String nacionFiltro) {
		ArrayList<Piloto> listaPilotos = new ArrayList<Piloto>();
		String query = "SELECT NUMERO, NOMBRE, EDAD, NACIONALIDAD, ESCUDERIA FROM PILOTOS " 
		+ "WHERE NACIONALIDAD = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, nacionFiltro);
			
			rslt = pstmt.executeQuery();
			
			int num;
			String nom;
			String nac;
			String esc;
			int edad;
			while (rslt.next()) {
				num = rslt.getInt(1);
				nom = rslt.getString(2);
				nac = rslt.getString(4);
				esc = rslt.getString(5);
				edad = rslt.getInt(3);
				
				listaPilotos.add(new Piloto(num, nom, edad, esc, nac));
				
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listaPilotos;
	}

	public int insertPiloto(Piloto piloto) {
		String query = "INSERT INTO PILOTOS (NUMERO, NOMBRE, NACIONALIDAD, EDAD, ESCUDERIA) " 
						+ "VALUES (?, ?, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, piloto.getNumero());
			pstmt.setString(2, piloto.getNombre());
			pstmt.setString(3, piloto.getNacionalidad());
			pstmt.setInt(4, piloto.getEdad());
			pstmt.setString(5, piloto.getEscuderia());
			
			res = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			res = -1;
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
		
	}

	public Piloto selectPilotoNum(int num) {
		Piloto piloto = null;
		String query = "SELECT NOMBRE, EDAD, NACIONALIDAD, ESCUDERIA FROM PILOTOS " + 
						" WHERE NUMERO = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			
			rslt = pstmt.executeQuery();
			
			String nom;
			String nac;
			String esc;
			int edad;
			while (rslt.next()) {
				nom = rslt.getString(1);
				nac = rslt.getString(3);
				esc = rslt.getString(4);
				edad = rslt.getInt(2);
				
				piloto = new Piloto(num, nom, edad, esc, nac);
				
			}
				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rslt != null) rslt.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return piloto;
	}

	public int updatePiloto(Piloto piloto) {
		int res = 0;
		
		String query = "UPDATE PILOTOS SET NUMERO = ?, EDAD = ?, ESCUDERIA = ?"
						+ " WHERE NOMBRE = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, piloto.getNumero());
			pstmt.setInt(2, piloto.getEdad());
			pstmt.setString(3, piloto.getEscuderia());
			pstmt.setString(4, piloto.getNombre());
			
			res = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			res = -1;
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}			
		
		return res;
	}

	public int deletePiloto(int numPiloto) {
		String query = "DELETE FROM PILOTOS WHERE NUMERO = ?";
		int res = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, numPiloto);
			
			res = pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return res;
	}

}
