package com.sigarep.controlador;

import java.awt.Window;
import java.util.List;
import com.sigarep.modelo.Profesor;
import org.springframework.stereotype.Controller;
import org.zkoss.zhtml.Button;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import com.sigarep.modelo.Profesor;
import com.sigarep.servicios.ServicioProfesor;
@SuppressWarnings({ "serial", "unused" })//Sirve para eliminar warnings especificos
@Controller
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControladorProfesor extends SelectorComposer<Component> {
	private @WireVariable ServicioProfesor sp;
	private @Wire Textbox txtnombre;
	private @Wire Textbox txtapellido;
	private @Wire Combobox cmbsexo;
	private @Wire Textbox txtemail;
	private @Wire Button btnguardar;
	private @Wire Listbox profeListbox; //la Propiedad Wire conecta  al componente mediante su id "profeListbox"
	private @Wire Window ventana;
	private List<Profesor> lista;
	//@wire y @listen extienden de SelectorCOmposer
	
	@Listen("onClick = #btnguardar")//se ejecuta el metodo guardar cuando se le da click al boton que tiene como id="btnguardar"
	public void guardar() {
		String nombre = txtnombre.getValue();
		String apellido= txtapellido.getValue();
		String email= txtemail.getValue();
		String sexo= cmbsexo.getValue();
		Profesor pro = new Profesor(nombre,apellido,email,sexo);
		if (txtnombre.getValue().equals("")||txtapellido.getValue().equals("")|| cmbsexo.getValue().equals("Seleccione una Opcion...")|| txtemail.getValue().equals(""))
				Messagebox.show("Debes Llenar todos los Campos", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
		else{
				sp.guardar(pro);
				Messagebox.show("Se guardo exitosamente", "Advertencia", Messagebox.OK, Messagebox.EXCLAMATION);
				cancelar();
				listarProfesor();
		}
		
	}
	@Listen("onClick = #btnbuscar")
	public void buscar(){
		String nombre= txtnombre.getValue();
		List<Profesor> result =sp.buscarP(txtnombre.getValue());
		profeListbox.setModel(new ListModelList<Profesor>(result));	
	}
	@Listen("onClick = #btneliminar")
	public void eliminar(){
		String Nombre = txtnombre.getValue();
		if (Nombre.equals("")){
			Messagebox.show("Introduzca nombre","Advertencia",Messagebox.OK,Messagebox.EXCLAMATION);
		}
		else{
			sp.eliminar(Nombre);
			Messagebox.show("Profesor eliminado exitosamente", "Informacion", Messagebox.OK, Messagebox.INFORMATION);
			cancelar();
			listarProfesor();
		}
	}
		
	@Listen("onClick = #btncancelar")
	public void cancelar(){
		txtnombre.setValue("");
		txtapellido.setValue("");
		cmbsexo.setValue("Seleccione una Opcion...");
		txtemail.setValue("");
		
	}
	public void listarProfesor(){
		List<Profesor> profe = sp.listadoProfesor();
		profeListbox.setModel(new ListModelList<Profesor>(profe));
	}
	//en ese metodo con Onselect en la Listbox de profesor, al seleccionar con el puntero 
	//se le pide que nos de los datos de ese objeto seleccionado y es guardado en un objeto de tipo Profesor llamado selectd
	//para luego agregarlo a cada caja de texto con sus valores que estan encapsulados en el objeto.
	//Nota: hay que tomar en cuenta que la lista profeListbox debe estar cargada previamente
	@Listen("onSelect = #profeListbox")
	public void seleccion(){
		Profesor selected = profeListbox.getSelectedItem().getValue();
		txtnombre.setValue(selected.getNombre());
		txtapellido.setValue(selected.getApellido());
		cmbsexo.setValue(selected.getSexo());
		txtemail.setValue(selected.getEmail());
		
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		listarProfesor();
		
	}

}
