package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedicos.springboot.app.models.entity.Solicitud_Detalle;
import com.bemedicos.springboot.app.repository.SolicitudDetalleRepository;

@Service
public class SolicitudDetalleServiceImpl implements SolicitudDetalleService 
{
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private SolicitudDetalleRepository repository;
	
	
	@Override
	public void save(Solicitud_Detalle solicitud_detalle) {
		// TODO Auto-generated method stub
		repository.save(solicitud_detalle);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public List<Solicitud_Detalle> findAll() {
		// TODO Auto-generated method stub
		return (List<Solicitud_Detalle>) repository.findAll();
	}

	@Override
	public Solicitud_Detalle findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

}
