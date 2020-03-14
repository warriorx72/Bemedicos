package com.bemedicos.springboot.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.AntecedentesPersonales;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.repository.PersonalesRepository;

@Service
public class PersonalesServiceImpl implements PersonalesService {

	@Autowired
	private PersonalesRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<AntecedentesPersonales> findAll() {
		return (List<AntecedentesPersonales>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(AntecedentesPersonales personales) {
// TODO Auto-generated method stub
		repository.save(personales);
	}

	@Override
	@Transactional
	public void delete(Long id) {
// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public AntecedentesPersonales findOne(Long id) {
// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public String findByPaciente(Long id) {
		// TODO Auto-generated method stub
		return em
				.createNativeQuery(
						"SELECT ap.antecedentespersonales_id FROM app_antecedentes_personales ap WHERE ap.paciente_id =" + id)
				.getSingleResult().toString();
	}

	@Override
	@Transactional
	public void saveEntity(AntecedentesPersonales personales) {
		if (personales.getPaciente_id() != null && personales.getPaciente_id() > 0) {
			em.merge(personales);
		} else {
			em.persist(personales);
		}
	}
	
	@Override
	public String findByPacientes(Long id) {
		// TODO Auto-generated method stub
		return em
				.createNativeQuery("SELECT IF((SELECT COUNT(*) FROM app_antecedentes_personales pac WHERE pac.paciente_id = " + id + " )>0, 1 , 0)").getSingleResult().toString();
	}
}