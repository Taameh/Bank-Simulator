package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

import Programa.CuentaBancaria;
import Programa.Logica;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class DebitoFrame {

	private Logica logica;
	private CuentaBancaria sesion;
	
	private JFrame frmBancoEdd;
	private JTextField textFieldMonto;
	private JTextField textFieldDNI;



	/**
	 * Create the application.
	 */
	public DebitoFrame(Logica l) {
		logica = l;
		sesion = logica.getSesionActual();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmBancoEdd(new JFrame());
		getFrmBancoEdd().setTitle("Banco EDD - Nuevo débito");
		getFrmBancoEdd().setBounds(100, 100, 300, 169);
		getFrmBancoEdd().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getFrmBancoEdd().getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese los datos correspondientes");
		lblNewLabel.setBounds(10, 11, 203, 14);
		getFrmBancoEdd().getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 245, 245));
		panel.setBounds(10, 28, 264, 59);
		getFrmBancoEdd().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMonto = new JLabel("Monto:");
		lblMonto.setBounds(10, 11, 44, 14);
		panel.add(lblMonto);
		
		textFieldMonto = new JTextField();
		textFieldMonto.setBounds(20, 28, 86, 20);
		panel.add(textFieldMonto);
		textFieldMonto.setColumns(10);
		
		JLabel lblDniDestinatario = new JLabel("DNI destinatario:");
		lblDniDestinatario.setBounds(158, 11, 82, 14);
		panel.add(lblDniDestinatario);
		
		textFieldDNI = new JTextField();
		textFieldDNI.setColumns(10);
		textFieldDNI.setBounds(168, 28, 86, 20);
		panel.add(textFieldDNI);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Montserrat Light", Font.PLAIN, 10));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float monto = Float.parseFloat(textFieldMonto.getText());
					int dni = Integer.parseInt(textFieldDNI.getText());
					int confirmar = JOptionPane.showConfirmDialog(btnConfirmar, "¿Desea confirmar la transacción?", "Confirmar transaccion", 2);
					if (confirmar == 0) {
					if(!logica.debito(monto, dni)) {
						JOptionPane.showMessageDialog(btnConfirmar, "Transaccion Invalida", "Error en la transaccion", 0);
					}
					frmBancoEdd.dispose();
					}
					
				} catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(
							null, "Por favor, solo ingrese numeros", "Datos incorrectos", 0);
				}
			}
		});
		btnConfirmar.setBounds(85, 98, 89, 23);
		getFrmBancoEdd().getContentPane().add(btnConfirmar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(btnConfirmar, "¿Desea cancelar la transacción?", "Cancelar transaccion", 2);
				if (confirmar == 0) {
				frmBancoEdd.dispose();
				}
			}
		});
		btnCancelar.setFont(new Font("Montserrat Light", Font.PLAIN, 10));
		btnCancelar.setBounds(185, 98, 89, 23);
		frmBancoEdd.getContentPane().add(btnCancelar);
	}

	public JFrame getFrmBancoEdd() {
		return frmBancoEdd;
	}

	public void setFrmBancoEdd(JFrame frmBancoEdd) {
		this.frmBancoEdd = frmBancoEdd;
		frmBancoEdd.setResizable(false);
	}
}
