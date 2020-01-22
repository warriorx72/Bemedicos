package com.bemedicos.springboot.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Evolucion;
import com.bemedicos.springboot.app.repository.EvolucionRepository;

@Service
public class EvolucionServiceImpl implements EvolucionService {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private EvolucionRepository repository;
	@Override
	@Transactional(readOnly=true)
	public List<Evolucion> findAll() {
		// TODO Auto-generated method stub
		return (List<Evolucion>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Evolucion evolucion) {
		// TODO Auto-generated method stub
		repository.save(evolucion);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Evolucion findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Object> AppNotas(Long id){
		return em.createNativeQuery("call app_notas("+id+");").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Object> AppListarNotas(Long id){
		return em.createNativeQuery("call app_listarnotas("+id+");").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Object> AppCrearNotas(Long id){
		return em.createNativeQuery("call app_crearnotas("+id+");").getResultList();
	}

}
