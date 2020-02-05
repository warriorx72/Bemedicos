package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedicos.springboot.app.models.entity.Solicitud;
import com.bemedicos.springboot.app.repository.SolicitudRepository;

@Service
public class SolicitudServiceImpl implements SolicitudService 
{
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private SolicitudRepository repository;
	
	@Override
	public void save(Solicitud solicitud) {
		// TODO Auto-generated method stub
		repository.save(solicitud);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Solicitud> findAll() {
		// TODO Auto-generated method stub
		return (List<Solicitud>) repository.findAll();
	}

	@Override
	public Solicitud findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

}
