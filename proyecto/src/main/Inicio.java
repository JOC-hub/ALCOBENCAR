package main;

import java.awt.EventQueue;

import control.AlcoListener;
import view.PCliente;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;

public class Inicio {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VPMenu vMenu = new VPMenu();
				PCliente pc = new PCliente();
				VVerificacion vv = new VVerificacion();
				VEmpleado ve = new VEmpleado();
				
				AlcoListener listener = new AlcoListener(vMenu, pc, vv, ve);
				vMenu.setListener(listener);
				pc.setListener(listener);
				vv.setListener(listener);
				ve.setListener(listener);
				
				vMenu.hacerVisible();
				
			}
		});

	}

}
