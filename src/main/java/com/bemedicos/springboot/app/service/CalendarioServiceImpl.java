package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Calendario;
import com.bemedicos.springboot.app.repository.CalendarioRepository;
@Service
public class CalendarioServiceImpl implements CalendarioService {

	@Autowired
	private CalendarioRepository repository;
	
	@Autowired
	private EntityManager em;
	
	@Override
	@Transactional(readOnly=true)
	public List<Calendario> findAll(){
		return (List<Calendario>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Calendario calendario) {
		
		repository.save(calendario);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Calendario findOne(Long id) {
		return repository.findById(id).orElse(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Calendario> findByDateBetween() {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT c.inicio,c.fin FROM Calendario c").getResultList();
	}
}
