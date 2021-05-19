package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import db.EmpleadosPersistencia;
import model.Empleados;
import view.PCliente;
import view.PEmpleBorrar;
import view.PEmpleCons;
import view.PEmpleModi;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;

public class AlcoListener implements ActionListener {

	static final int INTENTOS = 3;
	private int contInt = 0;
	
	private VPMenu vMenu;
	private PCliente pc;
	private VVerificacion vv;
	private VEmpleado ve;
	private PEmpleBorrar peb;
	private PEmpleCons pec;
	private PEmpleModi pem;
	private EmpleadosPersistencia modelo;

	public AlcoListener(VPMenu vMenu, PCliente pc, VVerificacion pv, VEmpleado ve, PEmpleBorrar peb,PEmpleCons pec, PEmpleModi pem) {
		this.vMenu = vMenu;
		this.pc = pc;
		this.vv = pv;
		this.ve = ve;
		this.peb = peb;
		this.pec = pec;
		this.pem = pem;
		modelo = new EmpleadosPersistencia();
				
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
//dejo hecha la estructura para navegar entre los elementos de la interfaz grafica sin dar ninguna funcionalidad --David
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(vMenu.MNTM_ACC_CLIENTE)) {
				pc.hacerVisible();
				vMenu.cargarPanel(pc);
			} else if (ev.getActionCommand().equals(vMenu.MNTM_ACC_EMPLE)) {
				vMenu.dispose();
				vv.hacerVisible();
			} else if (ev.getActionCommand().equals(ve.MNTM_VOLVER)) {
				ve.dispose();
				vMenu.hacerVisible();
				pc.hacerInvisible();
			} else if (ev.getActionCommand().equals(ve.MNTM_BORRAR)) {
				ve.cargarPanel(peb);
			} else if (ev.getActionCommand().equals(ve.MNTM_CONSULTAR)) {
				ve.cargarPanel(pec);
			} else if (ev.getActionCommand().equals(ve.MNTM_MODIFICAR)) {
				ve.cargarPanel(pem);
			}
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(VVerificacion.BTN_BYPASS)) {
				vv.dispose();	
				ve.hacerVisible();
			} else if (ev.getActionCommand().equals(VVerificacion.BTN_LOGIN)) {
				Empleados empleado = vv.getDatos();
				
				if (empleado != null) {
					String pwdDB = modelo.getPasswordUser(empleado.getUser());
					
					if (pwdDB == null){
						vv.mostrarMsjError("El usuario indicado no existe");
					} else {
						if (pwdDB.equals(empleado.getPwd())) {
							// PERMITIR ACCESO
							vv.dispose();
							ve.hacerVisible();
							vv.limpiarDatos();
							
						} else {
							// CONTRASEÑA NO COINCIDE
							contInt++;
							vv.mostrarMsjError("La contraseña no es válida. Te quedan " 
									+ (INTENTOS - contInt) + " intentos.");
							if (contInt == INTENTOS) {
								System.exit(0);
							}
						}
					}
				}
			}

		}

	}
}
