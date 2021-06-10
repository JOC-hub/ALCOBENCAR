package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import control.AlcoListener;
import model.Coche;
import sound.PlaySound;

import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class PEmpleCons extends JPanel {
	

	private static final long serialVersionUID = 1L;


	public static final String BTN_CONSULTAR = "CONSULTAR";
	public static final String BTN_ELIMINAR = "ELIMINAR";


	private static final String CLM_ID = "ID";
	private static final String CLM_MARCA = "MARCA";
	private static final String CLM_MODELO = "MODELO";
	private static final String CLM_TRACCION = "TRACCION";
	private static final String CLM_ANIADIDOS = "AÑADIDOS";
	private static final String CLM_FECHA_SAL = "FECHA SALIDA";
	private static final String CLM_RESERVADO = "RESERVADO";
	public static final String OPT_CUALQUIER = "Cualquiera";
	
	private VPMenu vMenu;
	private PlaySound ps;
	private JButton btnConsultar;
	private JButton btnEliminar;
	private JTable tblConsultasEmple;
	private JComboBox<String> cmbxMarca;
	private JComboBox<String> cmbxModelo;
	private DefaultComboBoxModel<String> cmbModelMarca;
	private DefaultComboBoxModel<String> cmbModelModelo;
	private DefaultTableModel tblModel;
	
	public PEmpleCons(VPMenu vMenu, PlaySound ps){
		this.vMenu = vMenu;
		this.ps = ps;
		init();
	}
	private void init() {
		setLayout(null);		
		
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
				
		JLabel lblRealizarConsulta = new JLabel("CONSULTA DE RESERVAS:");
		lblRealizarConsulta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRealizarConsulta.setBounds(59, 54, 221, 52);
		add(lblRealizarConsulta);
		
		btnConsultar = new JButton(BTN_CONSULTAR);
		btnConsultar.setBounds(129, 437, 151, 38);
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
		scrpConsultaEmple.setBounds(36, 159, 724, 250);
		add(scrpConsultaEmple);
		
		tblConsultasEmple = new JTable();
		tblConsultasEmple.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrpConsultaEmple.setViewportView(tblConsultasEmple);
		
		btnEliminar = new JButton(BTN_ELIMINAR);
		btnEliminar.setBounds(483, 437, 151, 38);
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

		tblModel.addColumn(CLM_ID);
		tblModel.addColumn(CLM_MARCA);
		tblModel.addColumn(CLM_MODELO);
		tblModel.addColumn(CLM_TRACCION);
		tblModel.addColumn(CLM_ANIADIDOS);
		tblModel.addColumn(CLM_FECHA_SAL);
		tblModel.addColumn(CLM_RESERVADO);

		tblConsultasEmple.setModel(tblModel);

		tblConsultasEmple.getColumn(CLM_ID).setPreferredWidth(15);
		tblConsultasEmple.getColumn(CLM_MARCA).setPreferredWidth(60);
		tblConsultasEmple.getColumn(CLM_MODELO).setPreferredWidth(90);
		tblConsultasEmple.getColumn(CLM_TRACCION).setPreferredWidth(80);
		tblConsultasEmple.getColumn(CLM_ANIADIDOS).setPreferredWidth(140);
		tblConsultasEmple.getColumn(CLM_FECHA_SAL).setPreferredWidth(90);
		tblConsultasEmple.getColumn(CLM_RESERVADO).setPreferredWidth(50);

	}
	
	public void cargarTabla(ArrayList<Coche> listaCoches) {

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
	
	public int getCocheSeleccionado() {
		int idCoche = -1;
		
		if (tblConsultasEmple.getSelectedRow() == -1) {
			ps.playSound(vMenu.errorURL);
			mostrarMsjError("Debe seleccionar el piloto que desea eliminar");
			
		} else {
			idCoche = (int) tblModel.getValueAt(tblConsultasEmple.getSelectedRow(), 0);
		}
		
		return idCoche;
	}
	
	public void setListener(AlcoListener listener) {
		btnConsultar.addActionListener(listener);
		btnEliminar.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void hacerInvisible() {
		setVisible(false);

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
}