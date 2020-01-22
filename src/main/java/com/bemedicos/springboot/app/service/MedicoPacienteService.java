package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.MedicoPaciente;


public interface MedicoPacienteService {
	public List<MedicoPaciente> findAll();

	public void save(MedicoPaciente medicopaciente);

	public void delete(Long id);

	public MedicoPaciente findOne(Long id);
}
