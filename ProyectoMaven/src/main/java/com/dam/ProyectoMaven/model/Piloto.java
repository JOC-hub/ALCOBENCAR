package com.dam.ProyectoMaven.model;

public class Piloto {
	
	private int numero;
	private String nombre;
	private int edad;
	private String escuderia;
	private String nacionalidad;
	
	public Piloto(int numero, String nombre, int edad, String escuderia, String nacionalidad) {
		this.numero = numero;
		this.nombre = nombre;
		this.edad = edad;
		this.escuderia = escuderia;
		this.nacionalidad = nacionalidad;
	}

	public int getNumero() {
		return numero;
	}

	public String getNombre() {
		return nombre;
	}

	public int getEdad() {
		return edad;
	}

	public String getEscuderia() {
		return escuderia;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}
	
	

}
