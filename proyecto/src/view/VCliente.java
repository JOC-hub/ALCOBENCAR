package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.AlcoListener;
import model.Coche;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class VCliente extends JFrame {
	private static final long serialVersionUID = 1L;

	static final int ANCHO = 800;
	static final int ALTO = 600;

	public static final String CLM_MARCA = "MARCA";
	public static final String CLM_MODELO = "MODELO";
	public static final String CLM_TRACCION = "TRACCION";
	public static final String CLM_ANIADIDOS = "AÑADIDOS";
	public static final String CLM_FECHA_SAL = "FECHA SALIDA";
	public static final String OPT_CUALQUIER = "Cualquiera";
	public static final String BTN_COMPROBAR = "COMPROBAR EXISTENCIAS";
	public static final String BTN_VOLVER = "VOLVER";
	public static final String BTN_RESERVAR = "RESERVAR";
	public static final String BTN_GUARDAR = "GUARDAR DATOS";
	public static final String BTN_CANCELAR = "CANCELAR";
	
	private JButton btnComprobar;
	private JComboBox<String> cmbxMarca;
	private JComboBox<String> cmbxModelo;
	private DefaultComboBoxModel<String> cmbModelMarca;
	private DefaultComboBoxModel<String> cmbModelModelo;
	private JTable tblCoches;	
	private DefaultTableModel tblModel;
	private JTextField txtDNI;
	private JButton btnReservar;
	private JTextField txtApeNom;
	private JButton btnClienteVolver;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JLabel lblApeNom;
	private JLabel lblDNI;

	public VCliente() {
		init();
	}

	private void init() {
		getContentPane().setLayout(null);

		setSize(ANCHO, ALTO);

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = new Dimension(ANCHO, ALTO);
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);

		JLabel lblRealizarConsulta = new JLabel("REALIZAR CONSULTA:");
		lblRealizarConsulta.setBounds(10, 54, 221, 38);
		getContentPane().add(lblRealizarConsulta);

		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(15, 138, 61, 14);
		getContentPane().add(lblMarca);

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(15, 171, 46, 14);
		getContentPane().add(lblModelo);

		lblApeNom = new JLabel("Nombre y apellido:");
		lblApeNom.setBounds(362, 126, 125, 14);
		getContentPane().add(lblApeNom);

		lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(362, 157, 96, 14);
		getContentPane().add(lblDNI);

		btnComprobar = new JButton(BTN_COMPROBAR);
		btnComprobar.setBounds(104, 217, 184, 38);
		getContentPane().add(btnComprobar);

		cmbxMarca = new JComboBox<String>();
		cmbModelMarca = new DefaultComboBoxModel<String>();
		cmbxMarca.setModel(cmbModelMarca);
		cmbxMarca.setBounds(71, 134, 112, 22);
		getContentPane().add(cmbxMarca);

		cmbxModelo = new JComboBox<String>();
		cmbModelModelo = new DefaultComboBoxModel<String>();
		cmbxModelo.setModel(cmbModelModelo);
		cmbxModelo.setBounds(71, 167, 112, 22);
		getContentPane().add(cmbxModelo);

		JScrollPane scrpTabla = new JScrollPane();
		scrpTabla.setBounds(10, 266, 764, 284);
		getContentPane().add(scrpTabla);

		tblCoches = new JTable();
		tblCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpTabla.setViewportView(tblCoches);
		
		txtApeNom = new JTextField();
		txtApeNom.setBounds(503, 123, 207, 20);
		getContentPane().add(txtApeNom);
		txtApeNom.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(503, 154, 207, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);

		btnReservar = new JButton(BTN_RESERVAR);
		btnReservar.setBounds(499, 217, 184, 38);
		getContentPane().add(btnReservar);

		btnClienteVolver = new JButton(BTN_VOLVER);
		btnClienteVolver.setBounds(685, 11, 89, 23);
		getContentPane().add(btnClienteVolver);
		
		btnGuardar = new JButton(BTN_GUARDAR);
		btnGuardar.setBounds(466, 69, 151, 23);
		getContentPane().add(btnGuardar);
		
		btnCancelar = new JButton(BTN_CANCELAR);
		btnCancelar.setBounds(648, 69, 112, 23);
		getContentPane().add(btnCancelar);

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

		tblModel.addColumn(CLM_MARCA);
		tblModel.addColumn(CLM_MODELO);
		tblModel.addColumn(CLM_TRACCION);
		tblModel.addColumn(CLM_ANIADIDOS);
		tblModel.addColumn(CLM_FECHA_SAL);

		tblCoches.setModel(tblModel);

		tblCoches.getColumn(CLM_MARCA).setPreferredWidth(40);
		tblCoches.getColumn(CLM_MODELO).setPreferredWidth(100);
		tblCoches.getColumn(CLM_TRACCION).setPreferredWidth(35);
		tblCoches.getColumn(CLM_ANIADIDOS).setPreferredWidth(150);
		tblCoches.getColumn(CLM_FECHA_SAL).setPreferredWidth(35);

	}

	public void cargarTabla(ArrayList<Coche> listaCoches) {

		tblModel.setRowCount(0);

		Object[] fila = new Object[5];

		for (Coche coche : listaCoches) {
			fila[0] = coche.getMarca();
			fila[1] = coche.getModelo();
			fila[2] = coche.getTraccion();
			fila[3] = coche.getAniadidos();
			fila[4] = coche.getFechaSalida();

			tblModel.addRow(fila);
		}

	}

	public String getMarcaFiltro() {
		return (String) cmbxMarca.getSelectedItem();
	}

	public String getModeloFiltro() {
		return (String) cmbxModelo.getSelectedItem();
	}

	public void cargarCmbMarcas(ArrayList<String> listaMarcas) {
		cmbModelMarca.removeAllElements();

		cmbModelMarca.addElement(OPT_CUALQUIER);
		
		for (String marca : listaMarcas) {
			cmbModelMarca.addElement(marca);

		}
	
	}

	public void cargarCmbModelos(ArrayList<String> listaModelos) {
		cmbModelModelo.removeAllElements();

		cmbModelModelo.addElement(OPT_CUALQUIER);
		
		for (String modelo : listaModelos) {
			cmbModelModelo.addElement(modelo);
		}

	}
	
	public boolean isCocheSelected() {
		boolean isSelected = false;
		
		if (tblCoches.getSelectedRow() != -1) {
			isSelected = true;
		}
		
		return isSelected;
	}
	
	public String getMarcaSeleccionada() {
		String marcaSelec = (String) tblModel.getValueAt(tblCoches.getSelectedRow(), 0);
		System.out.println(marcaSelec);
		return marcaSelec.trim();	
	}
	
	public String getModeloSeleccionado() {
		String modeloSelec = (String) tblModel.getValueAt(tblCoches.getSelectedRow(), 1);	
		System.out.println(modeloSelec);
		return modeloSelec.trim();
	}
	
	public String getTraccionSeleccionada() {
		String traccionSelec = (String) tblModel.getValueAt(tblCoches.getSelectedRow(), 2);
		System.out.println(traccionSelec);
		return traccionSelec.trim();
	}
	
	public String getAniadidosSeleccionados() {
		String aniadidosSelec = (String) tblModel.getValueAt(tblCoches.getSelectedRow(), 3);
		System.out.println(aniadidosSelec);
		return aniadidosSelec.trim();
	}
	
	public String getFechaSeleccionada() {
		String fechaSelec = (String) tblModel.getValueAt(tblCoches.getSelectedRow(), 4);
		System.out.println(fechaSelec);
		return fechaSelec.trim();
	}

	public String getApeNom() {
		String apeNom = txtApeNom.getText().trim();
		return apeNom;
	}
	
	public String getDNI() {
		String dni = txtDNI.getText().trim();
		return dni;
	}
	public void setListener(AlcoListener listener) {
		btnComprobar.addActionListener(listener);
		btnReservar.addActionListener(listener);
		btnClienteVolver.addActionListener(listener);
		btnCancelar.addActionListener(listener);
		btnGuardar.addActionListener(listener);
	}

	public void disableTabla() {
		tblCoches.setEnabled(false);
	}
	
	public void enableTabla() {
		tblCoches.setEnabled(true);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	public void hacerInvisible() {
		setVisible(false);

	}

	public void hacerReservaInvisible() {
		lblApeNom.setVisible(false);
		lblDNI.setVisible(false);
		txtApeNom.setVisible(false);
		txtDNI.setVisible(false);
		btnGuardar.setVisible(false);
		btnCancelar.setVisible(false);
		
	}
	
	public void hacerReservaVisible() {
		lblApeNom.setVisible(true);
		lblDNI.setVisible(true);
		txtApeNom.setVisible(true);
		txtDNI.setVisible(true);
		btnGuardar.setVisible(true);
		btnCancelar.setVisible(true);
		
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.ERROR_MESSAGE);

	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de selección", JOptionPane.INFORMATION_MESSAGE);
	}

	public void limpiartabla() {

		int a = tblModel.getRowCount() - 1;
		for (int i = a; i >= 0; i--) {
			tblModel.removeRow(i);
		}
	}

	public void limpiarComponentes() {
		txtApeNom.setText("");
		txtDNI.setText("");
		
	}
}
