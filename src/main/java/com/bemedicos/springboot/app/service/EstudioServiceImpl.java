package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Estudio;
import com.bemedicos.springboot.app.repository.EstudioRepository;

@Service
public class EstudioServiceImpl implements EstudioService 
{
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EstudioRepository repository;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> findAll() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call estudios_view();").getResultList();
	}

	@Override
	@Transactional
	public void save(Estudio estudio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Estudio findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<Object[]> select(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findAllEstudios() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call estudios_view();").getResultList();
	}

}
