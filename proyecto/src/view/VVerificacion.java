package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import control.AlcoListener;

public class VVerificacion extends JFrame {

	private static final long serialVersionUID = 1L;
	static final int ANCHO = 400;
	static final int ALTO = 300;
	public final String BTN_LOGIN = "Login";
	public static final String BTN_BYPASS = "BYPASS";

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
		btnBypass.addActionListener(listener);
		//un boton provisional para saltarme el login que no esta completamente implementado
	}

}
