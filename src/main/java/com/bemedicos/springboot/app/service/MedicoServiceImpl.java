package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.repository.MedicoRepository;

public class MedicoServiceImpl implements MedicoService{
	
	@Autowired
	private MedicoRepository repository;
	@Override
	@Transactional(readOnly=true)
	public List<Medicos> findAll() {
		// TODO Auto-generated method stub
		return (List<Medicos>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Medicos medico) {
		// TODO Auto-generated method stub
		repository.save(medico);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Medicos findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

}
