package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.AlcoListener;
import model.Coche;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
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
	public static final String OPT_CUALQUIER = "CUALQUIERA";
	public static final String BTN_COMPROBAR = "COMPROBAR EXISTENCIAS";
	public static final String BTN_VOLVER = "VOLVER";
	public static final String BTN_RESERVAR = "RESERVAR";

	private JButton btnComprobar;
	private JComboBox<String> cmbxMarca;
	private JComboBox<String> cmbxModelo;
	private JTable tblCoches;
	private DefaultComboBoxModel<String> cmbModelMarca;
	private DefaultComboBoxModel<String> cmbModelModelo;
	private DefaultTableModel tblModel;
	private JTextField txtDNI;
	private JTextField txtFecha;
	private JButton btnReservar;
	private JTextField txtApeNom;
	private JButton btnClienteVolver;

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

		JLabel lblApeNom = new JLabel("Nombre y apellido:");
		lblApeNom.setBounds(391, 111, 96, 14);
		getContentPane().add(lblApeNom);

		JLabel lblDNI = new JLabel("DNI:");
		lblDNI.setBounds(391, 142, 96, 14);
		getContentPane().add(lblDNI);

		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(391, 171, 46, 14);
		getContentPane().add(lblFecha);

		btnComprobar = new JButton(BTN_COMPROBAR);
		btnComprobar.setBounds(104, 200, 184, 38);
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
		scrpTabla.setBounds(10, 265, 750, 254);
		getContentPane().add(scrpTabla);

		tblCoches = new JTable();
		tblCoches.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpTabla.setViewportView(tblCoches);

		txtApeNom = new JTextField();
		txtApeNom.setBounds(503, 108, 207, 20);
		getContentPane().add(txtApeNom);
		txtApeNom.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(503, 139, 207, 20);
		getContentPane().add(txtDNI);
		txtDNI.setColumns(10);

		txtFecha = new JTextField();
		txtFecha.setBounds(503, 168, 207, 20);
		getContentPane().add(txtFecha);
		txtFecha.setColumns(10);

		btnReservar = new JButton(BTN_RESERVAR);
		btnReservar.setBounds(464, 200, 184, 38);
		getContentPane().add(btnReservar);

		btnClienteVolver = new JButton(BTN_VOLVER);
		btnClienteVolver.setBounds(685, 11, 89, 23);
		getContentPane().add(btnClienteVolver);

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

	public void setListener(AlcoListener listener) {
		btnComprobar.addActionListener(listener);
		btnReservar.addActionListener(listener);
		btnClienteVolver.addActionListener(listener);
	}

	public void hacerVisible() {
		setVisible(true);
	}

	public void hacerInvisible() {
		setVisible(false);

	}

	// TODO: getSelectedCoche();

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
}
