package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.AlcoListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;

public class VPMenu extends JFrame {	
	private static final long serialVersionUID = 1L;
	static final int ANCHO = 800;
	static final int ALTO = 600;
	
	public URL errorURL = getClass().getResource("error.wav");
	public final static String BTN_ACC_CLIENTE = "CLIENTE";
	public final static String BTN_ACC_EMPLE = "EMPLEADO";
	private JScrollPane scrpContenedor;
	private JButton btnAccesoCliente;
	private JButton btnAccesoEmpleado;
	
	public VPMenu() {			
		init();
	}

	private void init() {
		getContentPane().setLayout(null);
		
		btnAccesoCliente = new JButton(BTN_ACC_CLIENTE);
		btnAccesoCliente.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnAccesoCliente.setBounds(86, 227, 201, 75);
		getContentPane().add(btnAccesoCliente);
		
		btnAccesoEmpleado = new JButton(BTN_ACC_EMPLE);
		btnAccesoEmpleado.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnAccesoEmpleado.setBounds(491, 227, 201, 75);
		getContentPane().add(btnAccesoEmpleado);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(ANCHO, ALTO);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = new Dimension(ANCHO, ALTO);
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}

	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void hacerInvisible() {
		setVisible(false);
	}

	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	public void setListener(AlcoListener listener) {
		btnAccesoCliente.addActionListener(listener);
		btnAccesoEmpleado.addActionListener(listener);
	}
}
