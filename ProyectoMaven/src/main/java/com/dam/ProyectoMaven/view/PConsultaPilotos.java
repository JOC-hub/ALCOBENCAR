package com.dam.ProyectoMaven.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.dam.ProyectoMaven.control.F1Listener;
import com.dam.ProyectoMaven.model.Piloto;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;

public class PConsultaPilotos extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final String CLM_NUMERO = "NÚMERO";
	private static final String CLM_NOMBRE = "NOMBRE";
	private static final String CLM_NACIONALIDAD = "NACIONALIDAD";
	private static final String CLM_EDAD = "EDAD";
	private static final String CLM_ESCUDERIA = "ESCUDERÍA";
	public static final String BTN_CONSULTAR = "Consultar";
	public static final String OPT_TODAS = "TODAS";
	public static final String BTN_ELIMINAR = "Eliminar Piloto";
	
	private JTable tblPilotos;
	private DefaultTableModel tblModel;
	private JButton btnConsultar;
	private JComboBox<String> cmbNacionalidad;
	private DefaultComboBoxModel<String> cmbModel;
	private JButton btnEliminar;
	
	public PConsultaPilotos() {
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		
		setSize(VFormula1.ANCHO - 30, VFormula1.ALTO - 70);
		
		JLabel lblTitulo = new JLabel("Listado de Pilotos");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(24, 23, 180, 14);
		add(lblTitulo);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setBounds(640, 70, 89, 23);
		add(btnConsultar);
		
		JScrollPane scrpTabla = new JScrollPane();
		scrpTabla.setBounds(72, 104, 623, 309);
		add(scrpTabla);
		
		tblPilotos = new JTable();
		tblPilotos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpTabla.setViewportView(tblPilotos);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(57, 74, 89, 14);
		add(lblNacionalidad);
		
		cmbNacionalidad = new JComboBox<String>();
		cmbModel = new DefaultComboBoxModel<String>();
		cmbNacionalidad.setModel(cmbModel);
		//cmbNacionalidad.setModel(new DefaultComboBoxModel<String>(new String[] {"Todas", "Can", "Esp", "Grb", "Jpn", "Fin", "Ita", "Fra", "Aus", "Mex", "Hol"}));
		cmbNacionalidad.setBounds(145, 70, 75, 22);
		add(cmbNacionalidad);
		
		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setBounds(608, 440, 121, 23);
		add(btnEliminar);
		
		configurarTabla();
	}

	private void configurarTabla() {
		tblModel = new DefaultTableModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				/*if (column == 2) {
					return true;
				} else {*/
					return false;
				//}
			}
		};
		
		tblModel.addColumn(CLM_NUMERO);
		tblModel.addColumn(CLM_NOMBRE);
		tblModel.addColumn(CLM_EDAD);
		tblModel.addColumn(CLM_ESCUDERIA);
		tblModel.addColumn(CLM_NACIONALIDAD);
		
		tblPilotos.setModel(tblModel);
		
		tblPilotos.getColumn(CLM_NUMERO).setPreferredWidth(25);
		tblPilotos.getColumn(CLM_NOMBRE).setPreferredWidth(100);
		tblPilotos.getColumn(CLM_EDAD).setPreferredWidth(25);
		tblPilotos.getColumn(CLM_ESCUDERIA).setPreferredWidth(125);
		tblPilotos.getColumn(CLM_NACIONALIDAD).setPreferredWidth(50);
		
		
	}
	
	public void cargarTabla(ArrayList<Piloto> listaPilotos) {
		
		tblModel.setRowCount(0);
		
		Object[] fila = new Object[5];
		
		for (Piloto piloto : listaPilotos) {
			fila[0] = piloto.getNumero();
			fila[1] = piloto.getNombre();
			fila[2] = piloto.getEdad();
			fila[3] = piloto.getEscuderia();
			fila[4] = piloto.getNacionalidad();
			
			tblModel.addRow(fila);
		}
		
	}
	
	public void cargarCmbNacionalidades(ArrayList<String> listaNacion) {
		cmbModel.removeAllElements();
		
		cmbModel.addElement(OPT_TODAS);
		for (String nacion : listaNacion) {
			cmbModel.addElement(nacion);
		}
		
	}

	public void setListener(F1Listener listener) {
		btnConsultar.addActionListener(listener);
		btnEliminar.addActionListener(listener);
		
	}

	public String getNacionFiltro() {
		return (String) cmbNacionalidad.getSelectedItem();
	}
	
	public int getPilotoSeleccionado() {
		int numPiloto = -1;
		
		if (tblPilotos.getSelectedRow() == -1) {
			mostrarMsjError("Debe seleccionar el piloto que desea eliminar"); 
		} else {
			numPiloto = (int) tblPilotos.getValueAt(tblPilotos.getSelectedRow(), 0);
		}
		
		return numPiloto;
	}

	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.ERROR_MESSAGE);
		
	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.INFORMATION_MESSAGE);		
	}
}
