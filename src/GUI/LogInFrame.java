package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInFrame {

	public JFrame frmBancoeddInicio;
	private JTextField txtNombre;
	private JTextField textFieldNombre;
	private JTextField txtApellido;
	private JTextField textFieldApellido;
	private JTextField txtDni;
	private JTextField textFieldDNI;
	private JTextField txtClave;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private final JButton btnIniciarSesion = new JButton("Iniciar Sesión");
	private final JButton btnRegistrar = new JButton("Crear cuenta nueva");

	
	/**
	 * Create the application.
	 */
	public LogInFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBancoeddInicio = new JFrame();
		frmBancoeddInicio.setTitle("BancoEDD - Inicio de sesión");
		frmBancoeddInicio.setBounds(100, 100, 300, 450);
		frmBancoeddInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBancoeddInicio.getContentPane().setLayout(null);
		
		JTextPane txtpnBancoEdd = new JTextPane();
		txtpnBancoEdd.setEditable(false);
		txtpnBancoEdd.setBackground(new Color(240, 240, 240));
		txtpnBancoEdd.setFont(new Font("Montserrat Black", Font.PLAIN, 30));
		txtpnBancoEdd.setText("Banco EDD");
		txtpnBancoEdd.setBounds(48, 11, 187, 43);
		frmBancoeddInicio.getContentPane().add(txtpnBancoEdd);
		
		JTextArea txtrBienvenido = new JTextArea();
		txtrBienvenido.setEditable(false);
		txtrBienvenido.setBackground(new Color(240, 240, 240));
		txtrBienvenido.setFont(new Font("Montserrat", Font.PLAIN, 10));
		txtrBienvenido.setLineWrap(true);
		txtrBienvenido.setText("Bienvenido, ingrese sus datos para iniciar sesión.");
		txtrBienvenido.setBounds(15, 54, 259, 20);
		frmBancoeddInicio.getContentPane().add(txtrBienvenido);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtNombre.setEditable(false);
		txtNombre.setText("Nombre");
		txtNombre.setBounds(28, 92, 86, 20);
		frmBancoeddInicio.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.setBorder(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldNombre.setBounds(42, 114, 200, 21);
		frmBancoeddInicio.getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtApellido.setText("Apellido");
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBorder(null);
		txtApellido.setBounds(28, 146, 86, 20);
		frmBancoeddInicio.getContentPane().add(txtApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldApellido.setColumns(10);
		textFieldApellido.setBounds(42, 168, 200, 21);
		frmBancoeddInicio.getContentPane().add(textFieldApellido);
		
		txtDni = new JTextField();
		txtDni.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtDni.setText("DNI");
		txtDni.setEditable(false);
		txtDni.setColumns(10);
		txtDni.setBorder(null);
		txtDni.setBounds(28, 200, 86, 20);
		frmBancoeddInicio.getContentPane().add(txtDni);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setFont(new Font("Montserrat", Font.PLAIN, 11));
		textFieldDNI.setColumns(10);
		textFieldDNI.setBounds(42, 222, 200, 21);
		frmBancoeddInicio.getContentPane().add(textFieldDNI);
		
		txtClave = new JTextField();
		txtClave.setFont(new Font("Montserrat", Font.PLAIN, 11));
		txtClave.setText("Clave");
		txtClave.setEditable(false);
		txtClave.setColumns(10);
		txtClave.setBorder(null);
		txtClave.setBounds(28, 254, 86, 20);
		frmBancoeddInicio.getContentPane().add(txtClave);
		
				
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Montserrat", Font.PLAIN, 11));
		passwordField.setBounds(42, 276, 200, 21);
		frmBancoeddInicio.getContentPane().add(passwordField);
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		btnIniciarSesion.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnIniciarSesion.setBounds(86, 308, 111, 20);
		frmBancoeddInicio.getContentPane().add(btnIniciarSesion);
		
		
		btnRegistrar.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnRegistrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrar.setBounds(118, 369, 151, 31);
		frmBancoeddInicio.getContentPane().add(btnRegistrar);
		
		
	}
}
