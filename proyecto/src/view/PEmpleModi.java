package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.AlcoListener;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class PEmpleModi extends JPanel {
	public PEmpleModi(){
		init();
	}

	private static final long serialVersionUID = 1L;

	public final String BTN_COMPROBAR = "COMPROBAR EXISTENCIAS";
	public final String BTN_MODIFICAR = "MODIFICAR";
	
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnBuscar;
	private JButton btnModificar;
	private JTextField txtTraccion;
	
	
	private void init() {
		setLayout(null);		
		
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
				
		JLabel lblBuscarID = new JLabel("BUSCAR COCHE POR ID:");
		lblBuscarID.setBounds(148, 106, 307, 38);
		add(lblBuscarID);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(102, 239, 61, 14);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(196, 236, 109, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
			
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(101, 279, 46, 14);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(196, 276, 109, 20);
		add(txtModelo);
		txtModelo.setColumns(10);
		
		btnBuscar = new JButton("BUSCAR");
		btnBuscar.setBounds(415, 153, 103, 26);
		add(btnBuscar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(230, 155, 46, 14);
		add(lblId);
		
		btnModificar = new JButton(BTN_MODIFICAR);
		btnModificar.setBounds(275, 412, 221, 38);
		add(btnModificar);
		
		JComboBox cmbxIDModi = new JComboBox();
		cmbxIDModi.setBounds(275, 155, 61, 22);
		add(cmbxIDModi);
		
		JLabel lblTraccion = new JLabel("Traccion:");
		lblTraccion.setBounds(102, 317, 46, 14);
		add(lblTraccion);
		
		txtTraccion = new JTextField();
		txtTraccion.setBounds(196, 314, 109, 20);
		add(txtTraccion);
		txtTraccion.setColumns(10);
		
		JLabel lblFechaSalida = new JLabel("Fecha salida:");
		lblFechaSalida.setBounds(365, 279, 81, 14);
		add(lblFechaSalida);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(456, 276, 40, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(506, 276, 40, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(556, 276, 61, 20);
		add(spinner_2);
	}
	
	public void setListener(AlcoListener listener) {
		btnBuscar.addActionListener(listener);
		btnModificar.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
}
