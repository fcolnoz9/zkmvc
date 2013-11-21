package com.sigarep.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sigarep.modelo.Profesor;

public interface IProfesorDAO extends JpaRepository<Profesor,String> {
	
	@Query("select  pr from Profesor pr where pr.nombre= :nombre")
	public List<Profesor> buscar();
	@Query("select p from Profesor p where p.nombre= :nombre")
    public Profesor findBynombre(String nombre);
	@Query("select p from Profesor p where p.nombre= :nombre")
	public List<Profesor> buscarProfesor(String nombre);
	
}
