package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Embarazos;

public interface EmbarazosService 
{
	public List<Embarazos> findAlll();

	public void save(Embarazos embarazos);

	public void delete(Long id);

	public Embarazos findOne(Long id);
	
	public Embarazos findbyPacienteId(Long id);
}