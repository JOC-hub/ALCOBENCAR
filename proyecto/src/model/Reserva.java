package model;

public class Reserva {
	
	private String ape_nom;
	private String dni;
	private String fecha_res;
	private int id;
	
	public Reserva(String ape_nom, String dni, String fecha_res, int id) {
		this.ape_nom = ape_nom;
		this.dni = dni;
		this.fecha_res = fecha_res;
		this.id = id;
	}

	public String getApe_nom() {
		return ape_nom;
	}

	public String getDni() {
		return dni;
	}

	public String getFecha_res() {
		return fecha_res;
	}

	public int getId() {
		return id;
	}
	
	
}
