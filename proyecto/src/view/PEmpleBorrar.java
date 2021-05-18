package view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.AlcoListener;

public class PEmpleBorrar extends JPanel {
	public PEmpleBorrar(){
		init();
	}

	private static final long serialVersionUID = 1L;

	public final String BTN_COMPROBAR = "COMPROBAR EXISTENCIAS";
	public final String BTN_BORRAR = "BORRAR";
	
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnComprobar;
	private JTextField textId;
	private JButton btnBorrar;
	
	
	private void init() {
		setLayout(null);		
		
		setSize(VEmpleado.ANCHO-30, VEmpleado.ALTO-70);
				
		JLabel lblRealizarConsulta = new JLabel("REALIZAR CONSULTA:");
		lblRealizarConsulta.setBounds(10, 54, 221, 38);
		add(lblRealizarConsulta);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setBounds(20, 142, 61, 14);
		add(lblMarca);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(66, 139, 109, 20);
		add(txtMarca);
		txtMarca.setColumns(10);
			
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(20, 167, 46, 14);
		add(lblModelo);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(66, 164, 109, 20);
		add(txtModelo);
		txtModelo.setColumns(10);
		
		btnComprobar = new JButton(BTN_COMPROBAR);
		btnComprobar.setBounds(10, 251, 221, 38);
		add(btnComprobar);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(20, 117, 46, 14);
		add(lblId);
		
		textId = new JTextField();
		textId.setBounds(66, 114, 109, 20);
		add(textId);
		textId.setColumns(10);
		
		btnBorrar = new JButton(BTN_BORRAR);
		btnBorrar.setBounds(10, 310, 221, 38);
		add(btnBorrar);
	}
	
	public void setListener(AlcoListener listener) {
		btnComprobar.addActionListener(listener);
		btnBorrar.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
}
