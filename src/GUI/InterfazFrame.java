package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JDesktopPane;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class InterfazFrame {

	private JFrame frmBancoEdd;
	private JTextField txtNombreApellido;
	private JTextField textFieldSaldo;
	private JTextField textFieldSymbol;
	private JTextField textFieldDia;
	private JTextField textFieldMes;
	private JTextField textFieldAño;
	private JTable tableTransacciones;
	private JTextField textFieldDia2;
	private JTextField textFieldMes2;
	private JTextField textFieldAño2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazFrame window = new InterfazFrame();
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
	public InterfazFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBancoEdd = new JFrame();
		frmBancoEdd.setTitle("Banco EDD");
		frmBancoEdd.setBounds(100, 100, 500, 534);
		frmBancoEdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBancoEdd.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(130, 130, 130)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 484, 40);
		frmBancoEdd.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtNombreApellido = new JTextField();
		txtNombreApellido.setEditable(false);
		txtNombreApellido.setText("Nombre Apellido");
		txtNombreApellido.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 15));
		txtNombreApellido.setBounds(10, 11, 210, 20);
		txtNombreApellido.setBorder(null);
		txtNombreApellido.setBackground(new Color(255,255,255));
		panel.add(txtNombreApellido);
		txtNombreApellido.setColumns(10);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setToolTipText("Cerrar sesión");
		btnSalir.setFont(new Font("Montserrat", Font.PLAIN, 11));
		btnSalir.setBounds(406, 10, 68, 23);
		panel.add(btnSalir);
		
		JPanel ButtonArea = new JPanel();
		ButtonArea.setToolTipText("");
		ButtonArea.setBackground(new Color(245, 245, 245));
		ButtonArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		ButtonArea.setBounds(10, 51, 280, 43);
		frmBancoEdd.getContentPane().add(ButtonArea);
		ButtonArea.setLayout(null);
		
		JButton btnDebito = new JButton("Realizar débito");
		btnDebito.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnDebito.setToolTipText("Realizar débito");
		btnDebito.setBounds(10, 11, 125, 23);
		ButtonArea.add(btnDebito);
		
		JButton btnCredito = new JButton("Realizar crédito");
		btnCredito.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnCredito.setBounds(145, 11, 125, 23);
		ButtonArea.add(btnCredito);
		
		JPanel SaldoArea = new JPanel();
		SaldoArea.setLayout(null);
		SaldoArea.setToolTipText("");
		SaldoArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		SaldoArea.setBackground(new Color(245, 245, 245));
		SaldoArea.setBounds(300, 51, 174, 43);
		frmBancoEdd.getContentPane().add(SaldoArea);
		
		textFieldSaldo = new JTextField();
		textFieldSaldo.setBackground(new Color(245, 245, 245));
		textFieldSaldo.setFont(new Font("Montserrat SemiBold", Font.PLAIN, 18));
		textFieldSaldo.setHorizontalAlignment(SwingConstants.TRAILING);
		textFieldSaldo.setText("123456.00");
		textFieldSaldo.setEditable(false);
		textFieldSaldo.setBounds(44, 0, 130, 43);
		SaldoArea.add(textFieldSaldo);
		textFieldSaldo.setColumns(10);
		
		textFieldSymbol = new JTextField();
		textFieldSymbol.setBackground(new Color(245, 245, 245));
		textFieldSymbol.setFont(new Font("Montserrat Black", Font.PLAIN, 28));
		textFieldSymbol.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSymbol.setEditable(false);
		textFieldSymbol.setText("$");
		textFieldSymbol.setBounds(6, 1, 35, 41);
		textFieldSymbol.setBorder(null);
		SaldoArea.add(textFieldSymbol);
		textFieldSymbol.setColumns(10);
		
		JPanel HistoryArea = new JPanel();
		HistoryArea.setBackground(new Color(245, 245, 245));
		HistoryArea.setBounds(10, 105, 464, 345);
		HistoryArea.setBorder(new LineBorder(new Color(192, 192, 192)));
		frmBancoEdd.getContentPane().add(HistoryArea);
		HistoryArea.setLayout(null);
		
		JComboBox comboBoxMostrar = new JComboBox();
		comboBoxMostrar.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		comboBoxMostrar.setModel(new DefaultComboBoxModel(new String[] {"Mostrar todas", "Mostrar últimas N", "Mostrar N mayor Valor", "Mostrar fecha específica", "Mostrar valor superior a N"}));
		comboBoxMostrar.setBounds(10, 11, 165, 22);
		HistoryArea.add(comboBoxMostrar);
		
		textFieldDia = new JTextField();
		textFieldDia.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldDia.setToolTipText("Día");
		textFieldDia.setBounds(185, 12, 20, 20);
		HistoryArea.add(textFieldDia);
		textFieldDia.setColumns(10);
		
		textFieldMes = new JTextField();
		textFieldMes.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldMes.setToolTipText("Mes");
		textFieldMes.setColumns(10);
		textFieldMes.setBounds(210, 12, 20, 20);
		HistoryArea.add(textFieldMes);
		
		textFieldAño = new JTextField();
		textFieldAño.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldAño.setToolTipText("Año");
		textFieldAño.setColumns(10);
		textFieldAño.setBounds(235, 12, 35, 20);
		HistoryArea.add(textFieldAño);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnConfirmar.setBounds(334, 11, 120, 23);
		HistoryArea.add(btnConfirmar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 444, 289);
		HistoryArea.add(scrollPane);
		
		tableTransacciones = new JTable();
		tableTransacciones.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Monto", "Tipo", "Emisor", "Receptor", "Fecha", "Hora"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(tableTransacciones);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Año");
		textField_3.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textField_3.setColumns(10);
		textField_3.setBounds(185, 12, 85, 20);
		HistoryArea.add(textField_3);
		
		JButton btnConfirmar_1 = new JButton("Calcular saldo");
		btnConfirmar_1.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		btnConfirmar_1.setBounds(10, 461, 120, 23);
		frmBancoEdd.getContentPane().add(btnConfirmar_1);
		
		textFieldDia2 = new JTextField();
		textFieldDia2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldDia2.setToolTipText("Día");
		textFieldDia2.setColumns(10);
		textFieldDia2.setBounds(140, 462, 20, 20);
		frmBancoEdd.getContentPane().add(textFieldDia2);
		
		textFieldMes2 = new JTextField();
		textFieldMes2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldMes2.setToolTipText("Mes");
		textFieldMes2.setColumns(10);
		textFieldMes2.setBounds(165, 462, 20, 20);
		frmBancoEdd.getContentPane().add(textFieldMes2);
		
		textFieldAño2 = new JTextField();
		textFieldAño2.setFont(new Font("Montserrat Medium", Font.PLAIN, 11));
		textFieldAño2.setToolTipText("Año");
		textFieldAño2.setColumns(10);
		textFieldAño2.setBounds(190, 462, 35, 20);
		frmBancoEdd.getContentPane().add(textFieldAño2);
	}
}
