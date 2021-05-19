package model;

public class Empleados {

	private String user;
	private String pwd;
	
	public Empleados(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}
}
