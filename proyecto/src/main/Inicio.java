package main;

import java.awt.EventQueue;

import control.AlcoListener;
import view.VCliente;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;
import view.PEmpleBorrar;
import view.PEmpleCons;
import view.PEmpleModi;


public class Inicio {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPMenu vMenu = new VPMenu();
				VCliente pc = new VCliente();
				VVerificacion vv = new VVerificacion();
				VEmpleado ve = new VEmpleado();
				PEmpleBorrar peb = new PEmpleBorrar();
				PEmpleCons pec = new PEmpleCons();
				PEmpleModi pem = new PEmpleModi();
				
				AlcoListener listener = new AlcoListener(vMenu, pc, vv, ve, peb, pec, pem);
				vMenu.setListener(listener);
				pc.setListener(listener);
				vv.setListener(listener);
				ve.setListener(listener);
				peb.setListener(listener);
				pec.setListener(listener);
				peb.setListener(listener);
				
				vMenu.hacerVisible();
				
			}
		});

	}

}
