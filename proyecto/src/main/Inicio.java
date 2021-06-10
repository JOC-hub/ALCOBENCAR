package main;

import java.awt.EventQueue;

import control.AlcoListener;
import view.VCliente;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;
import view.PEmpleInsert;
import view.PEmpleCons;
import view.PEmpleModi;
import view.PEmpleReserva;


public class Inicio {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPMenu vMenu = new VPMenu();
				VCliente pc = new VCliente();
				VVerificacion vv = new VVerificacion();
				VEmpleado ve = new VEmpleado();
				PEmpleCons pec = new PEmpleCons();
				PEmpleModi pem = new PEmpleModi();
				PEmpleInsert pei = new PEmpleInsert();
				PEmpleReserva per = new PEmpleReserva();
				AlcoListener listener = new AlcoListener(vMenu, pc, vv, ve, pec, pei, pem, per);
				vMenu.setListener(listener);
				pc.setListener(listener);
				vv.setListener(listener);
				ve.setListener(listener);
				pec.setListener(listener);
				pei.setListener(listener);
				pem.setListener(listener);
				per.setListener(listener);

				
				vMenu.hacerVisible();
				
			}
		});

	}

}
