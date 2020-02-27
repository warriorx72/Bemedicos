package com.bemedicos.springboot.app.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Horario;
import com.bemedicos.springboot.app.repository.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService {

	@Autowired
	HorarioRepository repository;

	@Autowired
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Horario> findAll() {
		// TODO Auto-generated method stub
		return (List<Horario>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Horario findOne(Long horario_id) {
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

	@Override
	public void saveAll(List<Horario> h) {
		repository.saveAll(h);
	}

	@Override
	public boolean exist(Long id) {
		String bool = (em.createNativeQuery("call horario(" + id + ")").getSingleResult().toString());
		boolean x;
		if (bool.equals("1")) {
			x = true;
			return x;
		} else {
			x = false;
			return x;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findByMedicoId(Long id) {
		return em.createNativeQuery("call findOne("+id+")").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> genCitas(String fecha,Integer med_id) {
		
		 return em.createNativeQuery("call genci('"+fecha+"',"+med_id+");").getResultList();
		 
	}

	
	
	
}
