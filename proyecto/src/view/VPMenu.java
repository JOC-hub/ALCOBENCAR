package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.AlcoListener;

import javax.swing.JFrame;

public class VPMenu extends JFrame {	
	private static final long serialVersionUID = 1L;
	static final int ANCHO = 800;
	static final int ALTO = 600;
	
	public final String MNTM_ACC_CLIENTE = "CLIENTE";
	public final String MNTM_ACC_EMPLE = "EMPLEADO";
	
	private JScrollPane scrpContenedor;
	private JMenuItem mntmAccesoCliente;
	private JMenuItem mntmAccesoEmpleado;
	
	public VPMenu() {
		
		crearMenu();
		
		init();
	}

	private void init() {
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setSize(ANCHO, ALTO);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = new Dimension(ANCHO, ALTO);
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmAccesoCliente = new JMenuItem(MNTM_ACC_CLIENTE);
		menuBar.add(mntmAccesoCliente);
		
		mntmAccesoEmpleado = new JMenuItem(MNTM_ACC_EMPLE);
		menuBar.add(mntmAccesoEmpleado);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}

	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	public void setListener(AlcoListener listener) {
		mntmAccesoCliente.addActionListener(listener);
		mntmAccesoEmpleado.addActionListener(listener);
	}
}
