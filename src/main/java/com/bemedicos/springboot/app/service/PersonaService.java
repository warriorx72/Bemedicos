package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Persona;

public interface PersonaService {
	public List<Persona> findAll();
	
	public void save(Persona persona);
	
	public void delete(Long id);
	
	public Persona findOne(Long id);
	
	public String findByPaciente (Long id);
	
	public String findByDireccion (Long id);


}
