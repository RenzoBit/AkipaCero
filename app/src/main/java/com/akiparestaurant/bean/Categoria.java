package com.akiparestaurant.bean;

public class Categoria {
	
	private int idcategoria;
	private String nombre;
	
	public static final String CATEGORIA_ID = "idcategoria";
	public static final String CATEGORIA_NO = "nombre";
	
	public int getIdcategoria() {
		return idcategoria;
	}
	public void setIdcategoria(int idcategoria) {
		this.idcategoria = idcategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return nombre;
	}
	public int getId() {
		return idcategoria;
	}

}
