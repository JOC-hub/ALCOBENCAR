package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.AlcoListener;
import model.Reserva;

import javax.swing.JButton;

public class PEmpleReserva extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String CLM_APE_NOM = "NOMBRE Y APELLIDO";
	private static final String CLM_DNI = "DNI";
	private static final String CLM_FECHA_RES = "FECHA RESERVA";
	private static final String CLM_ID = "ID";
	public static final String BTN_ELIMINAR = "ELIMINAR RESERVA";
	
	private JTable tblConsultas;
	private DefaultTableModel tblModel;
	private JButton btnEliminar;
	
	public PEmpleReserva() {
		init();
	}

	private void init() {
		setLayout(null);		
		
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
		
		JLabel lblReserva = new JLabel("CONSULTA DE RESERVAS:");
		lblReserva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReserva.setBounds(38, 58, 209, 45);
		add(lblReserva);
		
		JScrollPane scrpTabla = new JScrollPane();
		scrpTabla.setBounds(38, 114, 722, 268);
		add(scrpTabla);
		
		tblConsultas = new JTable();
		tblConsultas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpTabla.setViewportView(tblConsultas);
		
		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setBounds(549, 393, 189, 28);
		add(btnEliminar);
		
		configurarTabla();
	}
	
	private void configurarTabla() {
		tblModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};

		tblModel.addColumn(CLM_APE_NOM);
		tblModel.addColumn(CLM_DNI);
		tblModel.addColumn(CLM_FECHA_RES);
		tblModel.addColumn(CLM_ID);

		tblConsultas.setModel(tblModel);

		tblConsultas.getColumn(CLM_APE_NOM).setPreferredWidth(100);
		tblConsultas.getColumn(CLM_DNI).setPreferredWidth(90);
		tblConsultas.getColumn(CLM_FECHA_RES).setPreferredWidth(90);
		tblConsultas.getColumn(CLM_ID).setPreferredWidth(15);
	}
	
	public void setListener(AlcoListener listener) {
		btnEliminar.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	public void hacerInvisible() {
		setVisible(false);
	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.ERROR_MESSAGE);
		
	}

	public void cargarTabla(ArrayList<Reserva> listaReservas) {
		tblModel.setRowCount(0);

		Object[] fila = new Object[4];

		for (Reserva reserva : listaReservas) {
			fila[0] = reserva.getApe_nom();
			fila[1] = reserva.getDni();
			fila[2] = reserva.getFecha_res();
			fila[3] = reserva.getId();

			tblModel.addRow(fila);
		}

	}
	
	public int getCocheSeleccionado() {
		int id_coche = 0;
		
		if(tblConsultas.getSelectedRow() == -1) {
			mostrarMsjError("Debe seleccionar la reserva a eliminar");
		} else {
			id_coche = (int) tblModel.getValueAt(tblConsultas.getSelectedRow(), 3);
		}
		
		return id_coche;
	}
	
}
