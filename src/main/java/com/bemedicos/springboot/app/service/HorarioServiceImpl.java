package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Horario;
import com.bemedicos.springboot.app.repository.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService {
	
	@Autowired
	HorarioRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Horario> findAll() {
		// TODO Auto-generated method stub
		return (List<Horario>) repository.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Horario findonde(Long horario_id) {
		// TODO Auto-generated method stub
		return repository.findById(horario_id).orElse(null);
	}

	@Override
	@Transactional
	public void save(Horario horario) {
		// TODO Auto-generated method stub
		repository.save(horario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
