package com.dam.ProyectoMaven.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.dam.ProyectoMaven.db.PilotosPersistencia;
import com.dam.ProyectoMaven.model.Piloto;
import com.dam.ProyectoMaven.view.PConsultaPilotos;
import com.dam.ProyectoMaven.view.PInsercionPiloto;
import com.dam.ProyectoMaven.view.PModifPiloto;
import com.dam.ProyectoMaven.view.VFormula1;

public class F1Listener implements ActionListener {
	
	private VFormula1 vf1;
	private PConsultaPilotos pcp;
	private PInsercionPiloto pip;
	private PModifPiloto pmp;
	private PilotosPersistencia model;
	
	public F1Listener(VFormula1 vf1, PConsultaPilotos pcp, PInsercionPiloto pip, 
			PModifPiloto pmp) {
		this.vf1 = vf1;
		this.pcp = pcp;
		this.pip = pip;
		this.pmp = pmp;
		model = new PilotosPersistencia();
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(VFormula1.MNTM_CONSULTA)) {
				pcp.cargarCmbNacionalidades(model.selectDistinctNacion());
				vf1.cargarPanel(pcp);
				
			} else if (ev.getActionCommand().equals(VFormula1.MNTM_INSERCION)) {
				// CARGAR PANEL DE INSERCIÓN
				vf1.cargarPanel(pip);
			} else if (ev.getActionCommand().equals(VFormula1.MNTM_MODIF)) {
				// CARGAR PANEL DE MODIFICACIÓN
				vf1.cargarPanel(pmp);
			}
			
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(PConsultaPilotos.BTN_CONSULTAR)) {
				consultarPiloto();
				
			} else if (ev.getActionCommand().equals(PInsercionPiloto.BTN_GUARDAR)) {
				Piloto piloto = pip.getDatos();
				
				if (piloto != null) {
					// ALMACENAR EL PILOTO EN LA BASE DE DATOS
					int res = model.insertPiloto(piloto);
					if (res == 1) {
						pip.mostrarMsjInfo("El piloto se ha insertado correctamente");
						pip.limpiarComponentes();
						
					} else if (res == -1) {
						pip.mostrarMsjError("No se puede insertar el piloto porque ya existe " 
								+ "el número o el nombre en la base de datos");
						
					} else {
						pip.mostrarMsjError("No se ha podido insertar el piloto");
					}
				}
				
			} else if (ev.getActionCommand().equals(PModifPiloto.BTN_BUSCAR)) {
				int num = pmp.getNumPiloto();
				
				Piloto piloto = model.selectPilotoNum(num);
				
				if (piloto != null) {
					// cargar los datos en el panel de modificación
					pmp.cargarDatos(piloto);
					
				} else {
					pmp.mostrarMsjInfo("No existe ningún piloto con el número indicado");
				}
				
			} else if (ev.getActionCommand().equals(PModifPiloto.BTN_GUARDAR)) {
				
				Piloto piloto = pmp.getDatos();
				int res = model.updatePiloto(piloto);
				
				if (res == -1) {
					pmp.mostrarMsjError("El número seleccionado corresponde a otro piloto");
				} else if (res == 1) {
					pmp.mostrarMsjInfo("La modificación se ha realizado con éxito");
				} else {
					pmp.mostrarMsjError("No se ha podido realizar modificación");
				}
				
			} else if (ev.getActionCommand().equals(PModifPiloto.BTN_CANCELAR)) {
				pmp.limpiarComponentes();
			} else if (ev.getActionCommand().equals(PConsultaPilotos.BTN_ELIMINAR)) {
				int numPiloto = pcp.getPilotoSeleccionado();
				
				if (numPiloto != -1) {
					int opcion = JOptionPane.showConfirmDialog(pcp, "Se va a eliminar el piloto seleccionado, ¿Desea continuar?",
							"Confirmacion de borrado",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);
					if (opcion == JOptionPane.YES_OPTION) {
						int res = model.deletePiloto(numPiloto);
						
						if (res == 1) {
							pcp.mostrarMsjInfo("El borrado se ha realizado con exito");
							consultarPiloto();
						} else {
							pcp.mostrarMsjError("No se ha podido eliminar el piloto");
						}	
					} else {
						pcp.mostrarMsjInfo("Se ha cancelado la operación");
					}
					
				}
			}
		}
		

	}

	private void consultarPiloto() {
		String nacionFiltro = pcp.getNacionFiltro();
		ArrayList<Piloto> listaPilotos = null;
		if (nacionFiltro.equals(PConsultaPilotos.OPT_TODAS)) {
			listaPilotos = model.selectPilotos();
		} else {
			listaPilotos = model.selectPilotosNacion(nacionFiltro);
		}
		pcp.cargarTabla(listaPilotos);
	}
	
}
