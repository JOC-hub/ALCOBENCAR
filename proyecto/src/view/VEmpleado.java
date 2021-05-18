package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import control.AlcoListener;

public class VEmpleado extends JFrame {
	private static final long serialVersionUID = 1L;
	static final int ANCHO = 800;
	static final int ALTO = 600;
	
	public final String MNTM_CONSULTAR = "CONSULTAR";
	public final String MNTM_BORRAR = "BORRAR";
	public final String MNTM_MODIFICAR = "MODIFICAR";
	public final String MNTM_VOLVER = "VOLVER";
	
	private JScrollPane scrpContenedor;
	private JMenuItem mntmConsultar;
	private JMenuItem mntmBorrar;
	private JMenuItem mntmModificar;
	private JMenuItem mntmVolver;
	
	public VEmpleado() {
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
		
		mntmConsultar = new JMenuItem(MNTM_CONSULTAR);
		menuBar.add(mntmConsultar);
		
		mntmBorrar = new JMenuItem(MNTM_BORRAR);
		menuBar.add(mntmBorrar);
		
		mntmModificar = new JMenuItem(MNTM_MODIFICAR);
		menuBar.add(mntmModificar);
		
		mntmVolver = new JMenuItem(MNTM_VOLVER);
		menuBar.add(mntmVolver);
	}

	public void hacerVisible() {
		setVisible(true);
	}

	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}
	
	public void setListener(AlcoListener listener) {
		mntmConsultar.addActionListener(listener);
		mntmBorrar.addActionListener(listener);
		mntmModificar.addActionListener(listener);
		mntmVolver.addActionListener(listener);
	}
}
