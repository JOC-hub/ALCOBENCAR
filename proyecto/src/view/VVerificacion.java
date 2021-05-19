package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.AlcoListener;
import model.Empleados;

public class VVerificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int ANCHO = 400;
	static final int ALTO = 300;
	public final static String BTN_LOGIN = "Login";
	public final static String BTN_BYPASS = "BYPASS";

	private JTextField txtUser;
	private JPasswordField txtPwd;
	private JButton btnLogin;
	private JButton btnBypass;

	public VVerificacion() {
		setTitle("Ventana Login");
		init();
	}

	private void init() {
		getContentPane().setLayout(null);

		JLabel lblUser = new JLabel("Usuario");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(25, 42, 55, 29);
		getContentPane().add(lblUser);

		JLabel lblPassword = new JLabel("Contraseña");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(25, 102, 82, 29);
		getContentPane().add(lblPassword);

		txtUser = new JTextField();
		txtUser.setBounds(140, 42, 131, 29);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);

		txtPwd = new JPasswordField();
		txtPwd.setBounds(140, 102, 131, 29);
		getContentPane().add(txtPwd);

		btnLogin = new JButton(BTN_LOGIN);
		btnLogin.setBounds(164, 178, 89, 23);
		getContentPane().add(btnLogin);
		
		//Este boton luego lo quitamos que de momento lo usamos para navegar sin tener que introducir datos cada vez que
		//estemos navegando la app
		btnBypass = new JButton(BTN_BYPASS);
		btnBypass.setBounds(164, 212, 89, 23);
		getContentPane().add(btnBypass);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(ANCHO, ALTO);

		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = new Dimension(ANCHO, ALTO);
		setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}

	public void hacerVisible() {
		setVisible(true);
	}

	public void setListener(AlcoListener listener) {
		btnLogin.addActionListener(listener);
		//un boton provisional para saltarme el login que no esta completamente implementado
		btnBypass.addActionListener(listener);
		
	}
	
	@SuppressWarnings("deprecation")
	public Empleados getDatos() {
		Empleados empleado = null;
		String user = txtUser.getText().trim();
		
		if (user.isEmpty()) {
			mostrarMsjError("Debe de introducir el nombre de usuario");
		} else {
			//por alguna razon pone que el metodo getText de pwd esta obsoleto
			//he puesto un SuppressWarnings
			String pwd = txtPwd.getText().trim();
			
			if (pwd.isEmpty()) {
				mostrarMsjError("Debe introducir la contraseña");
			} else {
				empleado = new Empleados(user, pwd);
			}
		}
		
		return empleado;
		
	}

	public void mostrarMsjError(String msj) {
		JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE);		
	}

	public void limpiarDatos() {
		txtPwd.setText(null);
		txtUser.setText(null);
	}
	
}
