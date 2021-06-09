package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.AlcoListener;
import model.Coche;
import model.Reserva;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PEmpleReserva extends JPanel {
	private static final long serialVersionUID = 1L;
	
	//public static final String BTN_CONSULTAR = "Consultar Reserva";
	private static final String CLM_APE_NOM = "NOMBRE Y APELLIDO";
	private static final String CLM_DNI = "DNI";
	private static final String CLM_FECHA_RES = "FECHA RESERVA";
	private static final String CLM_ID = "ID";
	public static final String OPT_CUALQUIER = "Cualquiera";
	
	private JTable tblConsultas;
	private DefaultTableModel tblModel;
	private JButton btnConsultar;
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
		
		/*btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setBounds(119, 409, 152, 35);
		add(btnConsultar);*/
		
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
	
	/*public void setListener(AlcoListener listener) {
		btnConsultar.addActionListener(listener);
	}*/
	
	public void hacerVisible() {
		setVisible(true);
	}
	public void hacerInvisible() {
		setVisible(false);
	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.INFORMATION_MESSAGE);
		
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
}
