package com.sigarep.modelo;


//Clase de Filtros para Profesor, por nombre, apellido,sexo
public class ProfesorFiltros {
	private String nombre="",apellido="",email="",sexo="";

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre==null?"":nombre.trim();//revisar
	}
//revisar esto
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido==null?"":apellido.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo==null?"":sexo.trim();
	}
}
