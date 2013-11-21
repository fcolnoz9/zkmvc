package com.sigarep.servicios;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.sigarep.modelo.Profesor;
import com.sigarep.repository.IProfesorDAO;
// El servicio interactua con la base de datos

@Service("sp") //Definiendo la variable servicio
public class ServicioProfesor{
	private @Autowired IProfesorDAO pr ;

	public void guardar(Profesor pro) {
		
    pr.save(pro);
	}
	public void actualizar(){
		
	}
	public void eliminar(String nombre){
		pr.delete(nombre);
	}
	public Profesor buscar(String nombre){
		return pr.findOne(nombre);
	}
	public List<Profesor> listadoProfesor() {
		List<Profesor> profesorLista=pr.findAll();
	    return profesorLista ;
	}
	public List<Profesor> buscarP(String nombre){
		List<Profesor> result = new LinkedList<Profesor>();
		if (nombre==null || "".equals(nombre)){//si el nombre es null o vacio,el resultado va a ser la lista completa de todos los profesores
			result = listadoProfesor();
		}else{//caso contrario se recorre toda la lista y busca los profesores con el nombre indicado en la caja de texto y tambien busca todos los que tengan  las letras iniciales de ese nombre. Realiza la busqueda con el apellido e inicial del apellido.
			for (Profesor p: listadoProfesor()){
				if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())||
					p.getApellido().toLowerCase().contains(nombre.toLowerCase())){
					result.add(p);
				}
			}
		}
		return result;

	}
}

	
