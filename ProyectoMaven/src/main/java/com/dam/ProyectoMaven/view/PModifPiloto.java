package com.dam.ProyectoMaven.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.dam.ProyectoMaven.control.F1Listener;
import com.dam.ProyectoMaven.model.Piloto;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PModifPiloto extends JPanel {
	
	public static final String BTN_GUARDAR = "Guardar Cambios Piloto";
	public static final String BTN_BUSCAR = "Buscar Piloto";
	public static final String BTN_CANCELAR = "Cancelar cambios";
	
	private JTextField txtNombre;
	private JTextField txtNacionalidad;
	private JSpinner spnNumero;
	private JSpinner spnEdad;
	private JComboBox<String> cmbEscuderia;
	private JButton btnGuardar;
	private JButton btnBuscar;
	private JButton btnCancelar;
	public PModifPiloto() {
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		
		setSize(VFormula1.ANCHO - 30, VFormula1.ALTO - 70);
		
		JLabel lblTitulo = new JLabel("Datos del Piloto a modificar");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(49, 42, 223, 14);
		add(lblTitulo);
		
		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(89, 90, 46, 14);
		add(lblNumero);
		
		spnNumero = new JSpinner();
		spnNumero.setModel(new SpinnerNumberModel(0, 0, 99, 1));
		spnNumero.setBounds(151, 87, 46, 20);
		add(spnNumero);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(89, 138, 46, 14);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(151, 135, 385, 20);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(89, 185, 76, 14);
		add(lblNacionalidad);
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setEditable(false);
		txtNacionalidad.setBounds(175, 182, 97, 20);
		add(txtNacionalidad);
		txtNacionalidad.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(89, 231, 46, 14);
		add(lblEdad);
		
		spnEdad = new JSpinner();
		spnEdad.setEnabled(false);
		spnEdad.setModel(new SpinnerNumberModel(16, 16, 67, 1));
		spnEdad.setBounds(151, 228, 46, 20);
		add(spnEdad);
		
		JLabel lblEscuderia = new JLabel("Escudería");
		lblEscuderia.setBounds(89, 283, 76, 14);
		add(lblEscuderia);
		
		cmbEscuderia = new JComboBox<String>();
		cmbEscuderia.setEnabled(false);
		cmbEscuderia.setModel(new DefaultComboBoxModel<String>(
				new String[] {"McLaren Racing", "Alfa Romeo Racing", "Scuderia AlphaTauri", 
						"Red Bull Racing", "Alpine F1 Team", "Scuderia Ferrari", "Aston Martin BWT", 
						"Mercedes-AMG Petronas", "Williams", "Haas"}));
		cmbEscuderia.setBounds(175, 279, 193, 22);
		add(cmbEscuderia);
		
		btnGuardar = new JButton(BTN_GUARDAR);
		btnGuardar.setEnabled(false);
		btnGuardar.setBounds(175, 368, 193, 23);
		add(btnGuardar);
		
		btnBuscar = new JButton(BTN_BUSCAR);
		btnBuscar.setBounds(244, 86, 124, 23);
		add(btnBuscar);
		
		btnCancelar = new JButton(BTN_CANCELAR);
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(377, 368, 193, 23);
		add(btnCancelar);
	}

	public void setListener(F1Listener listener) {
		btnGuardar.addActionListener(listener);
		btnBuscar.addActionListener(listener);
		btnCancelar.addActionListener(listener);
		
	}

	public Piloto getDatos() {
		int numero = (int) spnNumero.getValue();
		String nombre = txtNombre.getText();
		String nacion = txtNacionalidad.getText();
			
		int edad = (int) spnEdad.getValue();
		String escuderia = (String) cmbEscuderia.getSelectedItem();
				
		return new Piloto(numero, nombre, edad, escuderia, nacion);
	}

	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);
		
	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Información de operación", 
				JOptionPane.INFORMATION_MESSAGE);
		
	}

	public int getNumPiloto() {
		return (int) spnNumero.getValue();
	}

	public void cargarDatos(Piloto piloto) {
		txtNombre.setText(piloto.getNombre());
		txtNacionalidad.setText(piloto.getNacionalidad());
		spnEdad.setValue(piloto.getEdad());
		cmbEscuderia.setSelectedItem(piloto.getEscuderia());
		
		habilitarComponentes(true);
		
	}

	private void habilitarComponentes(boolean b) {
		spnEdad.setEnabled(b);
		cmbEscuderia.setEnabled(b);
		
		btnGuardar.setEnabled(b);
		btnCancelar.setEnabled(b);
		
		btnBuscar.setEnabled(!b);
		
	}

	public void limpiarComponentes() {
		spnNumero.setValue(0);
		txtNombre.setText("");
		txtNacionalidad.setText("");
		spnEdad.setValue(16);
		cmbEscuderia.setSelectedIndex(0);
		
		habilitarComponentes(false);
		
	}
}
