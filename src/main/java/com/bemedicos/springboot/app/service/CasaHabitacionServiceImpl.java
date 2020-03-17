package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.CasaHabitacion;
import com.bemedicos.springboot.app.repository.CasaHabitacionRepository;

@Service
public class CasaHabitacionServiceImpl implements CasaHabitacionService {

	@Autowired
	private CasaHabitacionRepository repository;
	
	@Autowired
	private EntityManager em;
	
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
	public void save2(CasaHabitacion casahabitacion) {
		if ( casahabitacion.getCasahabitacion_id() != null && casahabitacion.getCasahabitacion_id() > 0) {
			em.merge(casahabitacion);
		} else {
			em.persist(casahabitacion);
		}
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

	@Override
	public String findByPacienteId(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT IF((SELECT COUNT(*) FROM app_casa_habitacion pac "
				+ "WHERE pac.paciente_id="+id+" )>0, 1 , 0)").getSingleResult().toString();
	}
	
	@Override
	public String findBycasa(Long id) {
	
		return em.createNativeQuery("SELECT antecedentesfam_id FROM app_antecedentes_familiares af WHERE paciente_id =" + id)
				.getSingleResult().toString();
	}
	
	
	
	
	
	

}
