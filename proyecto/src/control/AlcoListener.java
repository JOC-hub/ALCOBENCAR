package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import db.CochePersistencia;
import db.EmpleadosPersistencia;
import model.Coche;
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
	private EmpleadosPersistencia modeloEmple;
	private CochePersistencia modelCoche;

	public AlcoListener(VPMenu vMenu, PCliente pc, VVerificacion pv, VEmpleado ve, PEmpleBorrar peb, PEmpleCons pec,
			PEmpleModi pem) {
		this.vMenu = vMenu;
		this.pc = pc;
		this.vv = pv;
		this.ve = ve;
		this.peb = peb;
		this.pec = pec;
		this.pem = pem;
		modeloEmple = new EmpleadosPersistencia();
		modelCoche = new CochePersistencia();

	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(vMenu.MNTM_ACC_CLIENTE)) {
				pc.cargarCmbMarcas(modelCoche.selectDisctinctMarca());
				pc.cargarCmbModelos(modelCoche.selectDisctinctModelo());
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
					String pwdDB = modeloEmple.getPasswordUser(empleado.getUser());

					if (pwdDB == null) {
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
							vv.mostrarMsjError(
									"La contraseña no es válida. Te quedan " + (INTENTOS - contInt) + " intentos.");
							if (contInt == INTENTOS) {
								System.exit(0);
							}
						}
					}
				}
			} else if (ev.getActionCommand().equals(PCliente.BTN_COMPROBAR)) {
				String marcaFiltro = pc.getMarcaFiltro();
				String modeloFiltro = pc.getModeloFiltro();

				ArrayList<Coche> listaCoches = null;

				if (marcaFiltro.equals(PCliente.OPT_CUALQUIER) && modeloFiltro.equals(PCliente.OPT_CUALQUIER)) {
					listaCoches = modelCoche.selectCocheCliente();
					pc.cargarTabla(listaCoches);
					if (listaCoches.isEmpty()) {
						pc.mostrarMsjInfo("NO HAY COCHES DISPONIBLES");
					}
				} else if ((marcaFiltro.equals(PCliente.OPT_CUALQUIER) && !modeloFiltro.equals(PCliente.OPT_CUALQUIER))) {
					pc.mostrarMsjError("Debe seleccionar marca");
				} else if ((!marcaFiltro.equals(PCliente.OPT_CUALQUIER) && modeloFiltro.equals(PCliente.OPT_CUALQUIER))) {
					listaCoches = modelCoche.selectCocheMarca(marcaFiltro);
					pc.cargarTabla(listaCoches);					
				} else {
					listaCoches = modelCoche.selectCocheMarcaModelo(marcaFiltro, modeloFiltro);					
					pc.cargarTabla(listaCoches);
					if (listaCoches.isEmpty()) {
						pc.mostrarMsjInfo("NO SE ENCONTRARON RESULTADOS DE LA BÚSQUEDA");
					}
				}
				
			}

		}

	}
}
