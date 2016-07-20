package com.akiparestaurant.bean;

import java.math.BigDecimal;

public class Plato {

	private int idplato;
	private String nombre;
	private BigDecimal precio;
	private int calificacion;
	private int relevancia;
	private String descripcion;

	public static final String PLATO_ID = "idplato";
	public static final String PLATO_NO = "nombre";
	public static final String PLATO_PR = "precio";
	public static final String PLATO_CA = "calificacion";
	public static final String PLATO_RE = "relevancia";
	public static final String PLATO_DE = "descripcion";

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public int getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(int relevancia) {
		this.relevancia = relevancia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
