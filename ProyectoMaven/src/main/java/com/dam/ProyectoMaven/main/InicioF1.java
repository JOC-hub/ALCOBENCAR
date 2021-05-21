package com.dam.ProyectoMaven.main;

import java.awt.EventQueue;

import com.dam.ProyectoMaven.control.F1Listener;
import com.dam.ProyectoMaven.view.PConsultaPilotos;
import com.dam.ProyectoMaven.view.PInsercionPiloto;
import com.dam.ProyectoMaven.view.PModifPiloto;
import com.dam.ProyectoMaven.view.VFormula1;

public class InicioF1 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				VFormula1 vf1 = new VFormula1();
				PConsultaPilotos pcp = new PConsultaPilotos();
				PInsercionPiloto pip = new PInsercionPiloto();
				PModifPiloto pmp = new PModifPiloto();
				
				F1Listener listener = new F1Listener(vf1, pcp, pip, pmp);
				
				vf1.setListener(listener);
				pcp.setListener(listener);
				pip.setListener(listener);
				pmp.setListener(listener);
				
				vf1.hacerVisible();
				
			}
		});

	}

}
