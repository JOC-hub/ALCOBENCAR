package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import view.PCliente;
import view.VEmpleado;
import view.VVerificacion;
import view.VPMenu;

public class AlcoListener implements ActionListener {

	private VPMenu vMenu;
	private PCliente pc;
	private VVerificacion vv;
	private VEmpleado ve;

	public AlcoListener(VPMenu vMenu, PCliente pc, VVerificacion pv, VEmpleado ve) {
		this.vMenu = vMenu;
		this.pc = pc;
		this.vv = pv;
		this.ve = ve;

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
			}
		} else if (ev.getSource() instanceof JButton) {
			if (ev.getActionCommand().equals(VVerificacion.BTN_BYPASS)) {
				vv.dispose();	
				ve.hacerVisible();
			} 

		}

	}
}
