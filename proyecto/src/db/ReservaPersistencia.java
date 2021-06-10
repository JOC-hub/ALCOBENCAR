package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Reserva;

public class ReservaPersistencia {

	private AccesoDB adb;
	
	public ReservaPersistencia() {
		adb = new AccesoDB();
	}
	
	public int insertarReserva(int idCoche, String apeNom, String dni) {
		
		String query = "INSERT INTO INFO_RESERVA (APE_NOM, DNI, FECHA_RESERVA, ID_COCHE)"
				+ "VALUES (?, ?, date('now'), ?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		int res = 0;
		
		try {
			con = adb.getConexion();
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, apeNom);
			pstmt.setString(2, dni);
			pstmt.setInt(3, idCoche);
			
			res = pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			res = -1;
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
		
		
	}
	
	public int deleteReserva(int idCoche) {
		String query = "DELETE FROM INFO_RESERVA WHERE ID_COCHE = ?";
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
	
	public ArrayList<Reserva> selectReservaEmple() {
		ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
		
		String query = "SELECT APE_NOM, DNI, FECHA_RESERVA, ID_COCHE FROM INFO_RESERVA";
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rslt = null;
		try {
			con = adb.getConexion();
			
			stmt = con.createStatement();
			
			rslt = stmt.executeQuery(query);
			
			String ape_nom;
			String dni;
			String fecha_res;
			int id;
			
			while (rslt.next()) {
				ape_nom = rslt.getString(1);
				dni = rslt.getString(2);
				fecha_res = rslt.getString(3);
				id = rslt.getInt(4);
				
				listaReservas.add(new Reserva(ape_nom, dni, fecha_res, id));
				
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
		
		return listaReservas;
		
	}
}
