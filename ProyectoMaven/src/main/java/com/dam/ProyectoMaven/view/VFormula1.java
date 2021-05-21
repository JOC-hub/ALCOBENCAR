package com.dam.ProyectoMaven.view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.dam.ProyectoMaven.control.F1Listener;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class VFormula1 extends JFrame {
	static final int ANCHO = 800;
	static final int ALTO = 600;
	public static final String MNTM_CONSULTA = "Consulta Pilotos";
	public static final String MNTM_INSERCION = "Añadir Piloto";
	public static final String MNTM_MODIF = "Modificar Piloto";
	
	private JScrollPane scrpContenedor;
	private JMenuItem mntmConsulta;
	private JMenuItem mntmInsercion;
	private JMenuItem mntmModificacion;
	
	public VFormula1() {
		
		crearMenu();
		
		initComponents();
	}

	private void initComponents() {
		scrpContenedor = new JScrollPane();
		getContentPane().add(scrpContenedor, BorderLayout.CENTER);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(ANCHO, ALTO);
		
		// Se obtienen las dimensiones en pixels de la pantalla.       
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();               
		// Se obtienen las dimensiones en pixels de la ventana.       
		Dimension ventana = new Dimension(ANCHO, ALTO);               
		// Una cuenta para situar la ventana en el centro de la pantalla.       
		setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
	}

	private void crearMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mntmConsulta = new JMenuItem(MNTM_CONSULTA);
		menuBar.add(mntmConsulta);
		
		mntmInsercion = new JMenuItem(MNTM_INSERCION);
		menuBar.add(mntmInsercion);
		
		mntmModificacion = new JMenuItem(MNTM_MODIF);
		menuBar.add(mntmModificacion);
	}
	
	public void hacerVisible() {
		setVisible(true);
	}
	
	public void cargarPanel(JPanel panel) {
		scrpContenedor.setViewportView(panel);
	}

	public void setListener(F1Listener listener) {
		mntmConsulta.addActionListener(listener);
		mntmInsercion.addActionListener(listener);
		mntmModificacion.addActionListener(listener);
	}
}
