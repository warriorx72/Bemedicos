package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.MedicoPaciente;
import com.bemedicos.springboot.app.repository.MedicoPacienteRepository;

@Service
public class MedicoPacienteServiceImpl implements MedicoPacienteService {

	@Autowired
	private MedicoPacienteRepository repository;
	@Override
	@Transactional(readOnly=true)
	public List<MedicoPaciente> findAll() {
		// TODO Auto-generated method stub
		return (List<MedicoPaciente>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(MedicoPaciente medicopaciente) {
		// TODO Auto-generated method stub
		repository.save(medicopaciente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public MedicoPaciente findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

}
