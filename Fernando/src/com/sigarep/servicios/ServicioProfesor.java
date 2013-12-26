package com.sigarep.servicios;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sigarep.modelo.Profesor;
import com.sigarep.modelo.ProfesorFiltros;
import com.sigarep.repository.IProfesorDAO;
// El servicio interactua con la base de datos

@Service("sp") //Definiendo la variable servicio
public class ServicioProfesor{
	private @Autowired IProfesorDAO pr ;
	private Profesor p;

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
	public List<Profesor> buscarP(ProfesorFiltros filtros){
		List<Profesor> result = new ArrayList<Profesor>();
		String nom = filtros.getNombre().toLowerCase();
        String ape = filtros.getApellido().toLowerCase();
        String sex = filtros.getSexo().toLowerCase();
        if(nom==null || ape==null ||  sex==null){
        	result= listadoProfesor();
        }
        else{
			for (Profesor p: listadoProfesor())
			{
				if (p.getNombre().toLowerCase().contains(nom)&&
					p.getApellido().toLowerCase().contains(ape)&&
					p.getSexo().toLowerCase().contains(sex)){
					result.add(p);
				}
			}
        }
		return result;
        } 
	}

	
