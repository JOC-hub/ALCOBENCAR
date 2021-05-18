package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.AlcoListener;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PCliente extends JPanel {
	public PCliente() {
		init();
	}

	private static final long serialVersionUID = 1L;

	public final String BTN_COMPROBAR = "COMPROBAR EXISTENCIAS";
	
	private JTextField txtMarca;
	private JTextField txtModelo;
	private JButton btnComprobar;
	
	
	private void init() {
		setLayout(null);		
		
		setSize(VPMenu.ANCHO-30, VPMenu.ALTO-70);
				
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
	}
	
	public void setListener(AlcoListener listener) {
		btnComprobar.addActionListener(listener);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	public void hacerInvisible() {
		setVisible(false);
		
	}
}
