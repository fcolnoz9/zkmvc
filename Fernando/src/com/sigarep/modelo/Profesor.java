
package com.sigarep.modelo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "profesor")
public class Profesor {
	
	private String nombre;
    private String apellido;
    private String email;
    private String sexo;
 
	public Profesor(String nombre, String apellido, String email, String sexo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.sexo = sexo;
	}
	public Profesor() {
	}
	@Id
	@Column(name = "nombre")
     public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "apellido")
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "sexo")
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo){
		this.sexo= sexo;
	}
}
