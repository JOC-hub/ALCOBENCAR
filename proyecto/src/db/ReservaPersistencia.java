package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
