package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.AlcoListener;
import model.Coche;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class PEmpleModi extends JPanel {
	public PEmpleModi() {
		init();
	}
	
	private static final long serialVersionUID = 1L;


	public static final String BTN_CONSULTAR = "VER COCHES";
	public static final String BTN_GUARDAR = "GUARDAR CAMBIOS";
	public static final String BTN_CANCELAR = "CANCELAR MODIFICACION";
	public static final String BTN_MODIF = "MODIFICAR";
	
	private static final String CLM_ID = "ID";
	private static final String CLM_MARCA = "MARCA";
	private static final String CLM_MODELO = "MODELO";
	private static final String CLM_TRACCION = "TRACCION";
	private static final String CLM_ANIADIDOS = "AÑADIDOS";
	private static final String CLM_FECHA_SAL = "FECHA SALIDA";
	private static final String CLM_RESERVADO = "RESERVADO";
	public static final String OPT_CUALQUIER = "Cualquiera";
	
	private JButton btnConsultar;
	private JTable tblConsultasEmpleModi;
	private JComboBox<String> cmbxMarca;
	private JComboBox<String> cmbxModelo;
	private DefaultComboBoxModel<String> cmbModelMarca;
	private DefaultComboBoxModel<String> cmbModelModelo;
	private DefaultTableModel tblModel;
	private JButton btnModif;
	private JButton btnGuardarModif;
	private JTextField txtMarcaModif;
	private JTextField txtModeloModif;
	private JTextArea txtAniadidosModif;
	private JSpinner spnAnioModif;
	private JSpinner spnMesModif;
	private JSpinner spnDiaModif;
	private JButton btnCancelarModif;
	private JComboBox<String> cmbxTraccion;
	
	private void init() {
		setLayout(null);		
		
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
				
		JLabel lblRealizarConsulta = new JLabel("CONSULTA DE RESERVAS:");
		lblRealizarConsulta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRealizarConsulta.setBounds(59, 54, 221, 52);
		add(lblRealizarConsulta);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setBounds(250, 110, 151, 38);
		add(btnConsultar);
		
		JLabel lblMarca = new JLabel("MARCA:");
		lblMarca.setBounds(36, 109, 46, 14);
		add(lblMarca);
		
		cmbxMarca = new JComboBox<String>();
		cmbModelMarca = new DefaultComboBoxModel<String>();
		cmbxMarca.setModel(cmbModelMarca);
		cmbxMarca.setBounds(110, 106, 112, 20);
		add(cmbxMarca);
		
		JLabel lblModelo = new JLabel("MODELO:");
		lblModelo.setBounds(36, 134, 64, 14);
		add(lblModelo);
		
		cmbxModelo = new JComboBox<String>();
		cmbModelModelo = new DefaultComboBoxModel<String>();
		cmbxModelo.setModel(cmbModelModelo);
		cmbxModelo.setBounds(110, 131, 112, 20);
		add(cmbxModelo);
		
		JScrollPane scrpConsultaEmple = new JScrollPane();
		scrpConsultaEmple.setBounds(36, 159, 724, 104);
		add(scrpConsultaEmple);
		
		tblConsultasEmpleModi = new JTable();
		tblConsultasEmpleModi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpConsultaEmple.setViewportView(tblConsultasEmpleModi);
		
		btnModif = new JButton(BTN_MODIF);
		btnModif.setBounds(586, 274, 112, 23);
		add(btnModif);
		
		
		
		
		
		btnGuardarModif = new JButton(BTN_GUARDAR);
		btnGuardarModif.setBounds(72, 480, 173, 23);
		add(btnGuardarModif);
		
		btnCancelarModif = new JButton(BTN_CANCELAR);
		btnCancelarModif.setBounds(492, 480, 206, 23);
		add(btnCancelarModif);
		
		txtMarcaModif = new JTextField();
		txtMarcaModif.setBounds(110, 335, 159, 20);
		add(txtMarcaModif);
		txtMarcaModif.setColumns(10);
		
		JLabel lblMarcaModif = new JLabel("Marca:");
		lblMarcaModif.setBounds(36, 338, 64, 14);
		add(lblMarcaModif);
		
		txtModeloModif = new JTextField();
		txtModeloModif.setBounds(110, 366, 159, 20);
		add(txtModeloModif);
		txtModeloModif.setColumns(10);
		
		JLabel lblModeloModif = new JLabel("Modelo:");
		lblModeloModif.setBounds(36, 369, 64, 14);
		add(lblModeloModif);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 397, 159, 60);
		add(scrollPane);
		
		txtAniadidosModif = new JTextArea();
		txtAniadidosModif.setLineWrap(true);
		txtAniadidosModif.setWrapStyleWord(true);
		scrollPane.setViewportView(txtAniadidosModif);
		
		JLabel lblAniadidosModif = new JLabel("A\u00F1adidos:");
		lblAniadidosModif.setBounds(36, 403, 72, 14);
		add(lblAniadidosModif);
		
		JLabel lblFechaSalModif = new JLabel("Fecha de salida:");
		lblFechaSalModif.setBounds(333, 338, 126, 14);
		add(lblFechaSalModif);
		
		spnDiaModif = new JSpinner();
		spnDiaModif.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spnDiaModif.setBounds(378, 366, 39, 20);
		add(spnDiaModif);
		
		JLabel lblDiaModif = new JLabel("D\u00EDa:");
		lblDiaModif.setBounds(333, 369, 39, 14);
		add(lblDiaModif);
		
		spnMesModif = new JSpinner();
		spnMesModif.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spnMesModif.setBounds(492, 366, 39, 20);
		add(spnMesModif);
		
		JLabel lblMesModif = new JLabel("Mes:");
		lblMesModif.setBounds(443, 369, 39, 14);
		add(lblMesModif);
		
		JLabel lblAnioModif = new JLabel("A\u00F1o:");
		lblAnioModif.setBounds(552, 369, 39, 14);
		add(lblAnioModif);
		
		spnAnioModif = new JSpinner();
		spnAnioModif.setModel(new SpinnerNumberModel(2000, 1950, 2050, 1));
		spnAnioModif.setBounds(601, 366, 72, 20);
		add(spnAnioModif);
		
		cmbxTraccion = new JComboBox<String>();
		cmbxTraccion.setModel(new DefaultComboBoxModel<String>(new String[] {"Delantera", "Trasera", "Total"}));
		cmbxTraccion.setBounds(409, 422, 135, 22);
		add(cmbxTraccion);
		
		JLabel lblTraccion = new JLabel("Tracci\u00F3n:");
		lblTraccion.setBounds(333, 426, 66, 14);
		add(lblTraccion);
		
		configurarTablaModi();
	}
	
	public void disableDetalles() {
		btnGuardarModif.setEnabled(false);
		btnCancelarModif.setEnabled(false);
		txtMarcaModif.setEnabled(false);
		txtModeloModif.setEnabled(false);
		txtAniadidosModif.setEnabled(false);
		spnDiaModif.setEnabled(false);
		spnMesModif.setEnabled(false);
		spnAnioModif.setEnabled(false);
		cmbxTraccion.setEnabled(false);
		
	}
	
	public void enableDetalles() {
		btnGuardarModif.setEnabled(true);
		btnCancelarModif.setEnabled(true);
		txtMarcaModif.setEnabled(true);
		txtModeloModif.setEnabled(true);
		txtAniadidosModif.setEnabled(true);
		spnDiaModif.setEnabled(true);
		spnMesModif.setEnabled(true);
		spnAnioModif.setEnabled(true);
		cmbxTraccion.setEnabled(true);
	}
	
	public void disableTabla() {
		tblConsultasEmpleModi.setEnabled(false);
	}
	
	public void enableTabla() {
		tblConsultasEmpleModi.setEnabled(true);
	}
	
	public void cargarDetalles(ArrayList<Coche> listaCoches) {
		int id_coche = getCocheSeleccionadoModi();
		
		if (id_coche != 0) {
			for (Coche coche : listaCoches) {
				String fecha = coche.getFechaSalida();
				StringTokenizer tokens = new StringTokenizer(fecha, "/");				
				
				int cont = 1;
				int dia = 0;
				int mes = 0;
				int anio = 0;
				
				while (tokens.hasMoreTokens()) {
					if (cont == 1) {
						dia = Integer.parseInt(tokens.nextToken());
						cont++;
					} else if (cont == 2) {
						mes = Integer.parseInt(tokens.nextToken());
						cont++;
					} else if (cont == 3) {
						anio = Integer.parseInt(tokens.nextToken());
					}
				}
				
				txtMarcaModif.setText(coche.getMarca());
				txtModeloModif.setText(coche.getModelo());
				txtAniadidosModif.setText(coche.getAniadidos());
				spnDiaModif.setValue(dia);
				spnMesModif.setValue(mes);
				spnAnioModif.setValue(anio);
				cmbxTraccion.setSelectedItem(coche.getTraccion());
			}
		}
	}
	
	public void limpiarDetalles() {
		txtMarcaModif.setText("");
		txtModeloModif.setText("");
		txtAniadidosModif.setText("");
		spnDiaModif.setValue(1);
		spnMesModif.setValue(1);
		spnAnioModif.setValue(2000);
		cmbxTraccion.setSelectedIndex(0);
	}
	
	private void configurarTablaModi() {
		tblModel = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;

			}
		};

		tblModel.addColumn(CLM_ID);
		tblModel.addColumn(CLM_MARCA);
		tblModel.addColumn(CLM_MODELO);
		tblModel.addColumn(CLM_TRACCION);
		tblModel.addColumn(CLM_ANIADIDOS);
		tblModel.addColumn(CLM_FECHA_SAL);
		tblModel.addColumn(CLM_RESERVADO);

		tblConsultasEmpleModi.setModel(tblModel);

		tblConsultasEmpleModi.getColumn(CLM_ID).setPreferredWidth(15);
		tblConsultasEmpleModi.getColumn(CLM_MARCA).setPreferredWidth(60);
		tblConsultasEmpleModi.getColumn(CLM_MODELO).setPreferredWidth(90);
		tblConsultasEmpleModi.getColumn(CLM_TRACCION).setPreferredWidth(80);
		tblConsultasEmpleModi.getColumn(CLM_ANIADIDOS).setPreferredWidth(140);
		tblConsultasEmpleModi.getColumn(CLM_FECHA_SAL).setPreferredWidth(90);
		tblConsultasEmpleModi.getColumn(CLM_RESERVADO).setPreferredWidth(50);

	}
	
	public void cargarTablaModi(ArrayList<Coche> listaCoches) {

		tblModel.setRowCount(0);

		Object[] fila = new Object[7];

		for (Coche coche : listaCoches) {
			fila[0] = coche.getId_coche();
			fila[1] = coche.getMarca();
			fila[2] = coche.getModelo();
			fila[3] = coche.getTraccion();
			fila[4] = coche.getAniadidos();
			fila[5] = coche.getFechaSalida();
			fila[6] = coche.getReservado();

			tblModel.addRow(fila);
		}

	}
	
	public void deselectTabla() {
		tblConsultasEmpleModi.clearSelection();
	}
	
	public boolean isCocheSelected() {
		boolean isSelected = false;
		
		if (tblConsultasEmpleModi.getSelectedRow() != -1) {
			isSelected = true;
		}
		
		return isSelected;
	}
	
	public String getMarcaFiltroModi() {
		return (String) cmbxMarca.getSelectedItem();
	}

	public String getModeloFiltroModi() {
		return (String) cmbxModelo.getSelectedItem();
	}

	public void cargarCmbMarcasModi(ArrayList<String> listaMarcas) {
		cmbModelMarca.removeAllElements();

		cmbModelMarca.addElement(OPT_CUALQUIER);
		
		for (String marca : listaMarcas) {
			cmbModelMarca.addElement(marca);

		}
	
	}

	public void cargarCmbModelosModi(ArrayList<String> listaModelos) {
		cmbModelModelo.removeAllElements();

		cmbModelModelo.addElement(OPT_CUALQUIER);
		
		for (String modelo : listaModelos) {
			cmbModelModelo.addElement(modelo);
		}

	}
	
	public int getCocheSeleccionadoModi() {
		int idCoche  = (int) tblModel.getValueAt(tblConsultasEmpleModi.getSelectedRow(), 0);
		
		return idCoche;
	}
	
	public String getMarcaModif() {
		String marca = txtMarcaModif.getText().trim();
		return marca;
	}
	
	public String getModeloModif() {
		String modelo = txtModeloModif.getText().trim();
		return modelo;
	}
	
	public String getAniadidosModif() {
		String aniadidos = txtAniadidosModif.getText().trim();
		return aniadidos;
	}
	
	public int getDiaModif() {
		int dia = (int) spnDiaModif.getValue();
		return dia;
	}
	
	public int getMesModif() {
		int mes = (int) spnMesModif.getValue();
		return mes;
	}
	
	public int getAnioModif() {
		int anio = (int) spnAnioModif.getValue();
		return anio;
	}
	
	public String getTraccionModif() {
		String traccion = (String) cmbxTraccion.getSelectedItem();
		return traccion;
	}
	
	public void setListener(AlcoListener listener) {
		btnConsultar.addActionListener(listener);
		btnModif.addActionListener(listener);
		btnGuardarModif.addActionListener(listener);
		btnCancelarModif.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void hacerInvisible() {
		setVisible(false);

	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void limpiartablaModi() {

		int a = tblModel.getRowCount() - 1;
		for (int i = a; i >= 0; i--) {
			tblModel.removeRow(i);
		}
	}
}
