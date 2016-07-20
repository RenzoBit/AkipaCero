package com.akiparestaurant.bean;

public class PedidoDetalle {

	private int idcliente;
	private int idplato;

	public static final String CATEGORIA_ID = "idcliente";
	public static final String CATEGORIA_PL = "idplato";


	private String comentario;
	private String facebook;
	private String nombre;
	private int calificacion;
	public static final String DISPOSITIVO_CO = "comentario";
	public static final String DISPOSITIVO_FA = "facebook";
	public static final String DISPOSITIVO_NO = "nombre";
	public static final String DISPOSITIVO_CA = "calificacion";

	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdplato() {
		return idplato;
	}

	public void setIdplato(int idplato) {
		this.idplato = idplato;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	

}
