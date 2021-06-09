package view;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import control.AlcoListener;
import model.Coche;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JScrollPane;

public class PEmpleInsert extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static final String BTN_INSERTAR = "Registrar Coche";
	public static final String BTN_LIMPIAR = "Limpiar Datos";
	
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JTextField txtTraccion;
	private JTextArea txtaAdds;
	private JSpinner spnDia;
	private JSpinner spnMes;
	private JSpinner spnAnio;
	private JComboBox<String> cmbReservado;
	private JButton btnInsertar;
	private JButton btnLimpiar;
	
	public PEmpleInsert() {
		initComponents();
	}

	private void initComponents() {
		setLayout(null);
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
		
		JLabel lblTitulo = new JLabel("Inserción de Coches");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(31, 37, 219, 27);
		add(lblTitulo);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMarca.setBounds(60, 115, 53, 15);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(142, 113, 137, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblModelo.setBounds(60, 174, 46, 14);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(142, 172, 137, 20);
		add(txtModelo);
		txtModelo.setColumns(10);
		
		JLabel lblTraccion = new JLabel("Traccion:");
		lblTraccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTraccion.setBounds(60, 237, 72, 14);
		add(lblTraccion);
		
		txtTraccion = new JTextField();
		txtTraccion.setBounds(142, 235, 137, 20);
		add(txtTraccion);
		txtTraccion.setColumns(10);
		
		JLabel lblAdds = new JLabel("A\u00F1adidos:");
		lblAdds.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdds.setBounds(60, 313, 72, 15);
		add(lblAdds);
		
		JLabel lblFechaS = new JLabel("Fecha salida:");
		lblFechaS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFechaS.setBounds(395, 116, 87, 14);
		add(lblFechaS);
		
		spnDia = new JSpinner();
		spnDia.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spnDia.setBounds(395, 149, 62, 20);
		add(spnDia);
		
		spnMes = new JSpinner();
		spnMes.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spnMes.setBounds(467, 149, 46, 20);
		add(spnMes);
		
		spnAnio = new JSpinner();
		spnAnio.setModel(new SpinnerNumberModel(1950, 1950, 2050, 1));
		spnAnio.setBounds(523, 149, 78, 20);
		add(spnAnio);
		
		JLabel lblReserva = new JLabel("Reservado:");
		lblReserva.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblReserva.setBounds(395, 208, 72, 14);
		add(lblReserva);
		
		cmbReservado = new JComboBox<String>();
		cmbReservado.setModel(new DefaultComboBoxModel<String>(new String[] {"NO", "SI"}));
		cmbReservado.setBounds(499, 205, 72, 22);
		add(cmbReservado);
		
		btnInsertar = new JButton(BTN_INSERTAR);
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInsertar.setBounds(408, 307, 163, 27);
		add(btnInsertar);
		
		btnLimpiar = new JButton(BTN_LIMPIAR);
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnLimpiar.setBounds(408, 374, 163, 27);
		add(btnLimpiar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(142, 307, 137, 94);
		add(scrollPane);
		
		txtaAdds = new JTextArea();
		scrollPane.setViewportView(txtaAdds);
		txtaAdds.setBackground(Color.WHITE);
		txtaAdds.setWrapStyleWord(true);
		txtaAdds.setLineWrap(true);
	}
	
	public void setListener(AlcoListener listener) {
		btnInsertar.addActionListener(listener);
		btnLimpiar.addActionListener(listener);
	}
	
	public void limpiarComponentes() {
		txtMarca.setText("");
		txtModelo.setText("");
		txtTraccion.setText("");
		txtaAdds.setText("");
		spnDia.setValue(1);
		spnMes.setValue(1);
		spnAnio.setValue(1950);
		cmbReservado.setSelectedIndex(0);
	}
	
	public void hacerInvisible() {
		setVisible(false);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error de datos", JOptionPane.ERROR_MESSAGE);
	}
	
	public void mostrarMsjInfo(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Resultado de Operación", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getMarcaInsert() {		
		String marca = txtMarca.getText().trim();
		return marca;
	}
	
	public String getModeloInsert() {
		String modelo = txtModelo.getText().trim();
		return modelo;
	}
			
	public String getTraccionInsert() {
		String traccion = txtTraccion.getText().trim();
		return traccion;
	}
	
	public String getAniadidosInsert() {
		String aniadidos = txtaAdds.getText().trim();
		return aniadidos;
	}
		
	public String getFechaSalidaInsert() {
		String fechaSalida = spnDia.getValue() + "/" + spnMes.getValue() + "/" + spnAnio.getValue(); 
		return fechaSalida;
	}
	
	public String getReservadoInsert() {
		String reservado = (String) cmbReservado.getSelectedItem();
		return reservado;
	}
}
