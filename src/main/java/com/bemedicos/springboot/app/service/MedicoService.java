package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.dto.MedicoPersonaDTO;
import com.bemedicos.springboot.app.models.entity.Medicos;

public interface MedicoService {
	public List<Medicos> findAll();
	
	public Medicos findOne(Long MedicosId);
	
	public void save(Medicos medicos);
	
	public void delete(Long id);
	
	public List<MedicoPersonaDTO> findAllById(Long id);
}
