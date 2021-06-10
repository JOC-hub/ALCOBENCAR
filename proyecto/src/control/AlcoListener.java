package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import db.CochePersistencia;
import db.EmpleadosPersistencia;
import db.ReservaPersistencia;
import model.Coche;
import model.Empleados;
import model.Reserva;
import sound.PlaySound;
import view.VCliente;
import view.PEmpleCons;
import view.PEmpleInsert;
import view.PEmpleModi;
import view.PEmpleReserva;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;

public class AlcoListener implements ActionListener {

	static final int INTENTOS = 3;
	private int contInt = 0;

	private VPMenu vMenu;
	private VCliente vc;
	private VVerificacion vv;
	private VEmpleado ve;
	private PEmpleCons pec;
	private PEmpleModi pem;
	private PEmpleInsert pei;
	private PEmpleReserva per;
	private EmpleadosPersistencia modeloEmple;
	private CochePersistencia modelCoche;
	private ReservaPersistencia modelReserva;
	private PlaySound ps;
	private boolean inModif = false;

	public AlcoListener(VPMenu vMenu, VCliente vc, VVerificacion pv, VEmpleado ve, PEmpleCons pec, PEmpleInsert pei,
			PEmpleModi pem, PEmpleReserva per, PlaySound ps) {
		this.vMenu = vMenu;
		this.vc = vc;
		this.vv = pv;
		this.ve = ve;
		this.pec = pec;
		this.pem = pem;
		this.pei = pei;
		this.per = per;
		this.pem = pem;
		this.ps = ps;
		modeloEmple = new EmpleadosPersistencia();
		modelCoche = new CochePersistencia();
		modelReserva = new ReservaPersistencia();

	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() instanceof JMenuItem) {
			if (ev.getActionCommand().equals(ve.MNTM_VOLVER)) {
				vMenu.hacerVisible();
				ve.dispose();
				vc.hacerInvisible();
			} else if (ev.getActionCommand().equals(ve.MNTM_CONSULTAR)) {
				pec.hacerVisible();
				ve.cargarPanel(pec);
				pec.limpiartabla();
				pec.cargarCmbMarcas(modelCoche.selectDisctinctMarca());
				pec.cargarCmbModelos(modelCoche.selectDisctinctModelo());
			} else if (ev.getActionCommand().equals(ve.MNTM_MODIFICAR)) {
				pem.hacerVisible();
				ve.cargarPanel(pem);
				pem.cargarCmbMarcasModi(modelCoche.selectDisctinctMarca());
				pem.cargarCmbModelosModi(modelCoche.selectDisctinctModelo());
				pem.disableDetalles();
				pem.limpiarDetalles();
				pem.enableTabla();
			} else if (ev.getActionCommand().equals(ve.MNTM_INSERT)) {
				pei.hacerVisible();
				ve.cargarPanel(pei);
			} else if (ev.getActionCommand().equals(ve.MNTM_RESERVAS)) {
				per.hacerVisible();
				ve.cargarPanel(per);
				consultarReservaEmple();
			}
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(VPMenu.BTN_ACC_CLIENTE)) {
				vc.hacerVisible();
				vc.hacerReservaInvisible();
				vc.enableTabla();
				vc.limpiartabla();
				vc.cargarCmbMarcas(modelCoche.selectDisctinctMarca());
				vc.cargarCmbModelos(modelCoche.selectDisctinctModelo());
				vMenu.hacerInvisible();
				vc.limpiarComponentes();
			} else if (ev.getActionCommand().equals(VCliente.BTN_VOLVER)) {
				vMenu.hacerVisible();
				vc.hacerInvisible();
			} else if (ev.getActionCommand().equals(VCliente.BTN_RESERVAR)) {
				if (!vc.isCocheSelected()) {
					ps.playSound(vMenu.errorURL);
					vc.mostrarMsjError("DEBE SELECCIONAR UN COCHE PARA RESERVAR");
				} else {
					vc.hacerReservaVisible();
					vc.disableTabla();
				}

			} else if (ev.getActionCommand().equals(VCliente.BTN_CANCELAR)) {
				vc.enableTabla();
				vc.hacerReservaInvisible();
				vc.limpiarComponentes();
			} else if (ev.getActionCommand().equals(VCliente.BTN_GUARDAR)) {
				int idCoche = modelCoche.selectIDCoche(vc.getMarcaSeleccionada(), vc.getModeloSeleccionado(),
						vc.getTraccionSeleccionada(), vc.getAniadidosSeleccionados(), vc.getFechaSeleccionada());
				String dni = vc.getDNI();
				String apeNom = vc.getApeNom();

				if (dni.length() != 9) {
					ps.playSound(vMenu.errorURL);
					vc.mostrarMsjError("DEBE INTRODUCIR UN DNI VALIDO");

				} else {
					int res1 = modelCoche.reservarCoche(idCoche);
					int res2 = modelReserva.insertarReserva(idCoche, apeNom, dni);
					if (res1 == -1 || res2 == -1) {
						ps.playSound(vMenu.errorURL);
						vc.mostrarMsjError("No se ha podido reservar el coche");

					} else {
						ps.playSound(vMenu.errorURL);
						vc.mostrarMsjInfo("El coche se ha reservado correctamente");

						vc.limpiarComponentes();
						consultarCocheCliente();
						vc.enableTabla();
						vc.hacerReservaInvisible();
					}

				}

			} else if (ev.getActionCommand().equals(VCliente.BTN_COMPROBAR)) {
				vc.enableTabla();
				vc.hacerReservaInvisible();
				consultarCocheCliente();

			} else if (ev.getActionCommand().equals(VPMenu.BTN_ACC_EMPLE)) {
				contInt = 0;
				vv.hacerVisible();
				vMenu.hacerInvisible();
			} else if (ev.getActionCommand().equals(VVerificacion.BTN_LOGIN)) {
				Empleados empleado = vv.getDatos();

				if (empleado != null) {
					String pwdDB = modeloEmple.getPasswordUser(empleado.getUser());

					if (pwdDB == null) {
						ps.playSound(vMenu.errorURL);
						vv.mostrarMsjError("El usuario indicado no existe");

					} else {
						if (pwdDB.equals(empleado.getPwd())) {
							// PERMITIR ACCESO
							vv.dispose();
							ve.hacerVisible();
							vv.limpiarDatos();
							pec.limpiartabla();
							pei.limpiarComponentes();
							pec.hacerInvisible();
							pei.hacerInvisible();
							pem.hacerInvisible();
							per.hacerInvisible();
						} else {
							// CONTRASEÑA NO COINCIDE
							contInt++;
							ps.playSound(vMenu.errorURL);
							vv.mostrarMsjError(
									"La contraseña no es válida. Te quedan " + (INTENTOS - contInt) + " intentos.");

							vv.limpiarContrasenia();
							if (contInt == INTENTOS) {
								vv.dispose();
								vMenu.hacerVisible();
								vv.limpiarDatos();
							}
						}
					}
				}
			} else if (ev.getActionCommand().equals(VVerificacion.BTN_CANCELAR)) {
				vv.dispose();
				vMenu.hacerVisible();
			} else if (ev.getActionCommand().equals(PEmpleCons.BTN_CONSULTAR)) {
				consultarCocheEmple();
			} else if (ev.getActionCommand().equals(PEmpleCons.BTN_ELIMINAR)) {
				int idCoche = pec.getCocheSeleccionado();

				if (idCoche != -1) {
					if (modelCoche.isCocheReservado(idCoche).isEmpty()) {
						ps.playSound(vMenu.errorURL);
						int opcion = JOptionPane.showConfirmDialog(pec,
								"Se va a eliminar el coche seleccionado ¿Desea continuar?", "Confirmación de borrado",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							int res = modelCoche.deleteCoche(idCoche);

							if (res == -1) {
								ps.playSound(vMenu.errorURL);
								pec.mostrarMsjError("No se podido eliminar el coche");

							} else {
								ps.playSound(vMenu.errorURL);
								pec.mostrarMsjInfo("El borrado se ha realizado con éxito");

								pec.cargarCmbMarcas(modelCoche.selectDisctinctMarca());
								pec.cargarCmbModelos(modelCoche.selectDisctinctModelo());
								consultarCocheEmple();
							}
						}
					} else {
						ps.playSound(vMenu.errorURL);
						int opcion = JOptionPane.showConfirmDialog(pec,
								"Se va a eliminar el coche seleccionado y su reserva ¿Desea continuar?",
								"Confirmación de borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (opcion == JOptionPane.YES_OPTION) {
							int res1 = modelReserva.deleteReserva(idCoche);
							int res2 = modelCoche.deleteCoche(idCoche);

							if (res1 == -1 || res2 == -1) {
								ps.playSound(vMenu.errorURL);
								pec.mostrarMsjError("No se podido eliminar el coche");

							} else {
								ps.playSound(vMenu.errorURL);
								pec.mostrarMsjInfo("El borrado se ha realizado con éxito");
								pec.cargarCmbMarcas(modelCoche.selectDisctinctMarca());
								pec.cargarCmbModelos(modelCoche.selectDisctinctModelo());
								consultarCocheEmple();
							}
						}

					}

				}

			} else if (ev.getActionCommand().equals(PEmpleInsert.BTN_INSERTAR)) {
				if (pei.getMarcaInsert().isEmpty() || pei.getModeloInsert().isEmpty()
						|| pei.getTraccionInsert().isEmpty() || pei.getFechaSalidaInsert().isEmpty()
						|| pei.getReservadoInsert().isEmpty()) {
					ps.playSound(vMenu.errorURL);
					pei.mostrarMsjInfo(
							"DEBE COMPLETAR TODOS LOS CAMPOS OBLIGATORIOS (MARCA, MODELO, TRACCION, FECHA DE SALIDA, RESERVADO)");

				} else if (!pei.getTraccionInsert().equals("Delantera") && !pei.getTraccionInsert().equals("Trasera")
						&& !pei.getTraccionInsert().equals("Total")) {
					ps.playSound(vMenu.errorURL);
					pei.mostrarMsjInfo("LA TRACCION DEBE SER: Delantera, Trasera o Total");

				} else if (!pei.getReservadoInsert().equals("SI") && !pei.getReservadoInsert().equals("NO")) {
					ps.playSound(vMenu.errorURL);
					pei.mostrarMsjInfo("EL CAMPO RESERVADO DEBE SER: SI O NO");

				} else {
					int res = modelCoche.insertCoche(pei.getMarcaInsert(), pei.getModeloInsert(),
							pei.getTraccionInsert(), pei.getAniadidosInsert(), pei.getFechaSalidaInsert(),
							pei.getReservadoInsert());

					if (res == -1) {
						ps.playSound(vMenu.errorURL);
						pei.mostrarMsjError("NO SE PUDO REALIZAR LA INSERCIÓN");

					} else {
						ps.playSound(vMenu.errorURL);
						pei.mostrarMsjInfo("SE HA REALIZADO LA INSERCIÓN");

						pei.limpiarComponentes();
					}
				}

			} else if (ev.getActionCommand().equals(PEmpleInsert.BTN_LIMPIAR)) {
				pei.limpiarComponentes();
			} else if (ev.getActionCommand().equals(PEmpleModi.BTN_CONSULTAR)) {
				if (inModif) {
					pem.disableDetalles();
					pem.limpiarDetalles();
					pem.enableTabla();
				}
				consultarCocheEmpleModi();
			} else if (ev.getActionCommand().equals(PEmpleModi.BTN_MODIF)) {
				if (pem.isCocheSelected()) {
					inModif = true;
					pem.enableDetalles();
					pem.disableTabla();

					pem.cargarDetalles(modelCoche.selectCocheEmpleId(pem.getCocheSeleccionadoModi()));
				} else {
					ps.playSound(vMenu.errorURL);
					pem.mostrarMsjError("DEBE SELECCIONAR UN COCHE PARA MODIFICARLO");

					pem.disableDetalles();
					pem.limpiarDetalles();
				}
			} else if (ev.getActionCommand().equals(PEmpleModi.BTN_GUARDAR)) {
				String fechaSalida = pem.getDiaModif() + "/" + pem.getMesModif() + "/" + pem.getAnioModif();
				String marca = pem.getMarcaModif();
				String modelo = pem.getModeloModif();

				if (marca.isEmpty() && modelo.isEmpty()) {
					ps.playSound(vMenu.errorURL);
					pem.mostrarMsjInfo("DEBE INTRODUCIR UNA MARCA Y UN MODELO");

				} else if (marca.isEmpty()) {
					ps.playSound(vMenu.errorURL);
					pem.mostrarMsjInfo("DEBE INTRODUCIR UNA MARCA");

				} else if (modelo.isEmpty()) {
					ps.playSound(vMenu.errorURL);
					pem.mostrarMsjInfo("DEBE INTRODUCIR UN MODELO");

				} else {
					modelCoche.updateCoche(pem.getCocheSeleccionadoModi(), marca, modelo, pem.getTraccionModif(),
							pem.getAniadidosModif(), fechaSalida);
					ps.playSound(vMenu.errorURL);
					pem.mostrarMsjInfo("SE HA REALIZADO LA MODIFICACION");

					consultarCocheEmpleModi();
					pem.disableDetalles();
					pem.limpiarDetalles();
					pem.enableTabla();
				}

			} else if (ev.getActionCommand().equals(PEmpleModi.BTN_CANCELAR)) {
				pem.deselectTabla();
				pem.disableDetalles();
				pem.limpiarDetalles();
				pem.enableTabla();
			} else if (ev.getActionCommand().equals(PEmpleReserva.BTN_ELIMINAR)) {
				int idCoche = per.getCocheSeleccionado();

				if (idCoche != -1) {
					ps.playSound(vMenu.errorURL);

					int opcion = JOptionPane.showConfirmDialog(per,
							"Se va a eliminar la reserva seleccionada ¿Desea continuar?", "Confirmación de borrado",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

					if (opcion == JOptionPane.YES_OPTION) {
						int res = modelReserva.deleteReserva(idCoche);
						modelCoche.quitarReserva(idCoche);

						if (res == -1) {
							ps.playSound(vMenu.errorURL);
							per.mostrarMsjError("No se podido eliminar la reserva");

						} else {
							ps.playSound(vMenu.errorURL);
							per.mostrarMsjInfo("El borrado se ha realizado con éxito");
							consultarReservaEmple();
						}
					}
				}

			}

		}

	}

	private void consultarCocheCliente() {
		String marcaFiltro = vc.getMarcaFiltro();
		String modeloFiltro = vc.getModeloFiltro();

		ArrayList<Coche> listaCoches = null;

		if (marcaFiltro.equals(VCliente.OPT_CUALQUIER) && modeloFiltro.equals(VCliente.OPT_CUALQUIER)) {
			listaCoches = modelCoche.selectCocheCliente();
			vc.cargarTabla(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				vc.mostrarMsjInfo("NO HAY COCHES DISPONIBLES");
			}
		} else if ((marcaFiltro.equals(VCliente.OPT_CUALQUIER) && !modeloFiltro.equals(VCliente.OPT_CUALQUIER))) {
			ps.playSound(vMenu.errorURL);
			vc.mostrarMsjError("Debe seleccionar marca");
		} else if ((!marcaFiltro.equals(VCliente.OPT_CUALQUIER) && modeloFiltro.equals(VCliente.OPT_CUALQUIER))) {
			listaCoches = modelCoche.selectCocheMarca(marcaFiltro);
			vc.cargarTabla(listaCoches);
		} else {
			listaCoches = modelCoche.selectCocheMarcaModelo(marcaFiltro, modeloFiltro);
			vc.cargarTabla(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				vc.mostrarMsjInfo("NO SE ENCONTRARON RESULTADOS DE LA BÚSQUEDA");

			}
		}

	}

	private void consultarCocheEmple() {
		String marcaFiltro = pec.getMarcaFiltro();
		String modeloFiltro = pec.getModeloFiltro();

		ArrayList<Coche> listaCoches = null;

		if (marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER)) {
			listaCoches = modelCoche.selectCocheEmple();
			pec.cargarTabla(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				pec.mostrarMsjInfo("NO HAY COCHES DISPONIBLES");

			}
		} else if ((marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && !modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER))) {
			ps.playSound(vMenu.errorURL);
			pec.mostrarMsjError("Debe seleccionar marca");

		} else if ((!marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER))) {
			listaCoches = modelCoche.selectCocheMarcaEmple(marcaFiltro);
			pec.cargarTabla(listaCoches);
		} else {
			listaCoches = modelCoche.selectCocheMarcaModeloEmple(marcaFiltro, modeloFiltro);
			pec.cargarTabla(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				pec.mostrarMsjInfo("NO SE ENCONTRARON RESULTADOS DE LA BÚSQUEDA");

			}
		}
	}

	private void consultarCocheEmpleModi() {
		String marcaFiltro = pem.getMarcaFiltroModi();
		String modeloFiltro = pem.getModeloFiltroModi();

		ArrayList<Coche> listaCoches = null;

		if (marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER)) {
			listaCoches = modelCoche.selectCocheEmple();
			pem.cargarTablaModi(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				pem.mostrarMsjInfo("NO HAY COCHES DISPONIBLES");

			}
		} else if ((marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && !modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER))) {
			ps.playSound(vMenu.errorURL);
			pem.mostrarMsjError("Debe seleccionar marca");

		} else if ((!marcaFiltro.equals(PEmpleCons.OPT_CUALQUIER) && modeloFiltro.equals(PEmpleCons.OPT_CUALQUIER))) {
			listaCoches = modelCoche.selectCocheMarcaEmple(marcaFiltro);
			pem.cargarTablaModi(listaCoches);
		} else {
			listaCoches = modelCoche.selectCocheMarcaModeloEmple(marcaFiltro, modeloFiltro);
			pem.cargarTablaModi(listaCoches);
			if (listaCoches.isEmpty()) {
				ps.playSound(vMenu.errorURL);
				pem.mostrarMsjInfo("NO SE ENCONTRARON RESULTADOS DE LA BÚSQUEDA");

			}
		}
	}

	private void consultarReservaEmple() {
		ArrayList<Reserva> listaReservas = null;

		listaReservas = modelReserva.selectReservaEmple();
		per.cargarTabla(listaReservas);

		if (listaReservas.isEmpty()) {
			ps.playSound(vMenu.errorURL);
			per.mostrarMsjInfo("NO SE ENCONTRARON RESULTADOS DE LA BÚSQUEDA");

		}
	}
}
