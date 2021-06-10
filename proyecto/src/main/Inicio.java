package main;

import java.awt.EventQueue;

import control.AlcoListener;
import sound.PlaySound;
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
				PlaySound ps = new PlaySound();
				VPMenu vMenu = new VPMenu();
				VCliente pc = new VCliente();
				VVerificacion vv = new VVerificacion();
				VEmpleado ve = new VEmpleado();
				PEmpleCons pec = new PEmpleCons(vMenu, ps);
				PEmpleModi pem = new PEmpleModi();
				PEmpleInsert pei = new PEmpleInsert();
				PEmpleReserva per = new PEmpleReserva(vMenu, ps);
				
				AlcoListener listener = new AlcoListener(vMenu, pc, vv, ve, pec, pei, pem, per, ps);
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
