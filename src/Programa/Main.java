package Programa;

import java.awt.EventQueue;

import GUI.LogInFrame;
import TDALista.ListaDoblementeEnlazada;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//Iniciar variables
		Logica logica = new Logica();
		
		//Iniciar ventana de logueo
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInFrame window = new LogInFrame(logica);
					window.getFrmBancoeddInicio().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

}
