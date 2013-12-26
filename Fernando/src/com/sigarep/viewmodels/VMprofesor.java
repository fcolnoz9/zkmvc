package com.sigarep.viewmodels;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;
import com.sigarep.modelo.Profesor;
import com.sigarep.modelo.ProfesorFiltros;
import com.sigarep.servicios.ServicioProfesor;

@SuppressWarnings("serial")
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VMprofesor {
	@WireVariable ServicioProfesor sp;
	private String nombre;private String apellido; private String email;private String sexo,apellidofiltro,nombrefiltro,sexofiltro;
	private ProfesorFiltros filtros = new ProfesorFiltros();
	public String getApellidofiltro() {
		return apellidofiltro;
	}
	public void setApellidofiltro(String apellidofiltro) {
		this.apellidofiltro = apellidofiltro;
	}
	public String getNombrefiltro() {
		return nombrefiltro;
	}
	public void setNombrefiltro(String nombrefiltro) {
		this.nombrefiltro = nombrefiltro;
	}
	public String getSexofiltro() {
		return sexofiltro;
	}
	public void setSexofiltro(String sexofiltro) {
		this.sexofiltro = sexofiltro;
	}
	private List<Profesor> listaProfesor;
	private Profesor profeseleccionado;
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
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
	public void setSexo(String sexo){
		this.sexo= sexo;
	}
	public List<Profesor> getListaProfesor() {
		return listaProfesor;
	}

	public void setListaProfesor(List<Profesor> listaProfesor) {
		this.listaProfesor = listaProfesor;
	}
	public Profesor getProfeseleccionado() {
		return profeseleccionado;
	}
	public void setProfeseleccionado(Profesor profeseleccionado) {
		this.profeseleccionado = profeseleccionado;
	}
	@Init
	public void init(){
        //initialization code
		ListadoProfesor();
    }
	@Command
	@NotifyChange({"nombre", "apellido", "email","sexo","listaProfesor"})//el notifychange le  avisa a que parametros en la pantalla se van a cambiar, en este caso es nombre,apellido,email,sexo se va a colocar en blanco al guardar!!
	public void guardar(){
		if (nombre.equals("")||apellido.equals("")|| sexo.equals("Seleccione una Opcion...")|| email.equals(""))
			Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
		Profesor pro = new Profesor(nombre,apellido,email,sexo);
		sp.guardar(pro);
		Messagebox.show("Se ha Registrado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
		limpiar();
		}
	}
	@Command
	@NotifyChange({"nombre", "apellido", "email","sexo"})
	public void limpiar(){
		nombre = "";apellido="";email="";sexo="";
		filtros();
	}
	@Command
	@NotifyChange({"listaProfesor"})
	public void filtros(){
		listaProfesor =sp.buscarP(filtros);
	}
	@NotifyChange({"filtros"})
	public ProfesorFiltros getFiltros() {
		return filtros;
	}
	public void setFiltros(ProfesorFiltros filtros) {
		this.filtros = filtros;
	}
	@Command
	@NotifyChange({"listaProfesor"})
	public void ListadoProfesor(){
		listaProfesor =sp.listadoProfesor();
	}
	@Command
	@NotifyChange({"listaProfesor"})
	public void eliminarProfesor(){
		sp.eliminar(nombre);
		limpiar();
		Messagebox.show("Se ha Eliminado Correctamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
	}
	@Command
	@NotifyChange({"nombre", "apellido", "email","sexo"})
	public void mostrarSeleccionado(){
		nombre=getProfeseleccionado().getNombre();
		apellido=getProfeseleccionado().getApellido();
		email=getProfeseleccionado().getEmail();
		sexo=getProfeseleccionado().getSexo();
	}
	
}
