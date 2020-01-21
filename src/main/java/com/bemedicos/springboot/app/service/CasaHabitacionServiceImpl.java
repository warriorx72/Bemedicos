package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.CasaHabitacion;
import com.bemedicos.springboot.app.repository.CasaHabitacionRepository;

@Service
public class CasaHabitacionServiceImpl implements CasaHabitacionService {

	@Autowired
	private CasaHabitacionRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<CasaHabitacion> findAll() {
		// TODO Auto-generated method stub
		return (List<CasaHabitacion>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(CasaHabitacion casahabitacion) {
		// TODO Auto-generated method stub
		repository.save(casahabitacion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public CasaHabitacion findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

}
