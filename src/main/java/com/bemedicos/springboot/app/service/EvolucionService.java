package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Evolucion;
import com.bemedicos.springboot.app.models.entity.Paciente;

public interface EvolucionService {
	
	public List<Evolucion> findAll();
	
	public Evolucion findOne(Long evolucion_id);
	
	public void save(Evolucion evolucion);
	
	public void delete(Long id);
	
	public List<Object> AppNotas(Long id);
	
	public List<Object> AppListarNotas(Long id);
	
	public List<Object> AppCrearNotas(Long id);
}
