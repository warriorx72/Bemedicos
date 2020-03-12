package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {
	@Autowired
	private PersonaRepository repository;
	@Autowired
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return (List<Persona>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Persona persona) {
		// TODO Auto-generated method stub
		repository.save(persona);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public String findByPaciente(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery(
				"SELECT app_p.persona_id FROM app_paciente app_p INNER JOIN persona p ON app_p.persona_id = p.persona_id WHERE app_p.paciente_id ="
						+ id)
				.getSingleResult().toString();
	}

	@Override
	public String findByDireccion(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery(
				"SELECT re.id_direccion from (SELECT app_p.paciente_id, p.id_direccion  FROM app_paciente app_p INNER JOIN persona p ON app_p.persona_id = p.persona_id WHERE app_p.paciente_id = "
						+ id + ") as re").getSingleResult().toString();
	}

}
