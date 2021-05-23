package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Coche;

public class CochePersistencia {

	private AccesoDB adb;
	
	public CochePersistencia() {
		adb = new AccesoDB();	
	}
	
	public ArrayList<Coche> selectCocheEmple() {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
		
	}
	
	public ArrayList<Coche> selectCocheMarcaEmple(String marcaFiltro) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE " 
		+ "WHERE MARCA = ? ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, marcaFiltro);
			
			rslt = pstmt.executeQuery();

			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
	}
	
	public ArrayList<Coche> selectCocheMarcaModeloEmple(String marcaFiltro, String modeloFiltro) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE " 
		+ "WHERE MARCA = ? "
		+ "AND MODELO = ? ";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, marcaFiltro);
			pstmt.setString(2, modeloFiltro);
			
			rslt = pstmt.executeQuery();

			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
	}
	
	public ArrayList<Coche> selectCocheCliente() {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE "
			+ "WHERE RESERVADO = 'NO'";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
		
	}
	
	public ArrayList<Coche> selectCocheMarca(String marcaFiltro) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE " 
		+ "WHERE MARCA = ? "
		+ "AND RESERVADO = 'NO'";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, marcaFiltro);
			
			rslt = pstmt.executeQuery();

			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
	}
	
	public ArrayList<Coche> selectCocheMarcaModelo(String marcaFiltro, String modeloFiltro) {
		ArrayList<Coche> listaCoches = new ArrayList<Coche>();
		
		String query = "SELECT ID_COCHE, MARCA, MODELO, TRACCION, ANIADIDOS, FECHA_SALIDA, RESERVADO FROM COCHE " 
		+ "WHERE MARCA = ?"
		+ "AND MODELO = ?"
		+ "AND RESERVADO = 'NO'";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rslt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, marcaFiltro);
			pstmt.setString(2, modeloFiltro);
			
			rslt = pstmt.executeQuery();

			int id;
			String marca;
			String modelo;
			String traccion;
			String aniadidos;
			String fecha;
			String reserv;
			while (rslt.next()) {
				id = rslt.getInt(1);
				marca = rslt.getString(2);
				modelo = rslt.getString(3);
				traccion = rslt.getString(4);
				aniadidos = rslt.getString(5);
				fecha = rslt.getString(6);
				reserv = rslt.getString(7);
				
				listaCoches.add(new Coche(id, marca, modelo, traccion, aniadidos, fecha, reserv));
				
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
		
		return listaCoches;
	}
	
	public ArrayList<String> selectDisctinctMarca() {
		ArrayList<String> listaMarcas = new ArrayList<String>();
		String query = "SELECT DISTINCT MARCA FROM COCHE";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			String marca;
			while (rslt.next()) {
				marca = rslt.getString(1);
				
				listaMarcas.add(marca);
				
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
		
		return listaMarcas;
	}
	
	public ArrayList<String> selectDisctinctModelo() {
		ArrayList<String> listaModelos = new ArrayList<String>();
		String query = "SELECT DISTINCT MODELO FROM COCHE";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			String modelo;
			while (rslt.next()) {
				modelo = rslt.getString(1);
				
				listaModelos.add(modelo);
				
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
		
		return listaModelos;
	}
	
	public int deleteCoche(int idCoche) {
		String query = "DELETE FROM COCHE WHERE ID_COCHE = ?";
		int res = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, idCoche);
			
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