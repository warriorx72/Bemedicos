package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.CasaHabitacion;

public interface CasaHabitacionService {

	public List <CasaHabitacion> findAll();

	public void save(CasaHabitacion casahabitacion);

	public void delete(Long id);

	public CasaHabitacion findOne(Long id);
	
	public void save2(CasaHabitacion casahabitacion);
	
	public String findByPacienteId(Long id);
	
	public String findBycasa(Long id);

}
