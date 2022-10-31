package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class DebitoFrame {

	private JFrame frmBancoEdd;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DebitoFrame window = new DebitoFrame();
					window.frmBancoEdd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DebitoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBancoEdd = new JFrame();
		frmBancoEdd.setTitle("Banco EDD - Nuevo débito");
		frmBancoEdd.setBounds(100, 100, 300, 169);
		frmBancoEdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBancoEdd.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos correspondientes");
		lblNewLabel.setBounds(10, 11, 169, 14);
		frmBancoEdd.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(10, 28, 264, 59);
		frmBancoEdd.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(10, 11, 44, 14);
		panel.add(lblMonto);
		
		textField = new JTextField();
		textField.setBounds(20, 28, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDniDestinatario = new JLabel("DNI destinatario:");
		lblDniDestinatario.setBounds(158, 11, 82, 14);
		panel.add(lblDniDestinatario);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(168, 28, 86, 20);
		panel.add(textField_1);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(185, 98, 89, 23);
		frmBancoEdd.getContentPane().add(btnConfirmar);
	}
}
