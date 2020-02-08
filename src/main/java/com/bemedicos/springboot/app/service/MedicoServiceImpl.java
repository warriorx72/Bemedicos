package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.dto.MedicoPersonaDTO;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.repository.MedicoRepository;
@Service
public class MedicoServiceImpl implements MedicoService{
	
	@Autowired
	private EntityManager em;
	@Autowired
	private MedicoRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Medicos> findAll() {
		// TODO Auto-generated method stub
		return (List<Medicos>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(Medicos medico) {
		// TODO Auto-generated method stub
		repository.save(medico);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Medicos findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<MedicoPersonaDTO> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery("SELECT distinct	new com.bemedicos.springboot.app.dto.MedicoPersonaDTO (m.medico_id, m.medico_foto, m.persona.persona_id, m.persona.persona_nombre, m.persona.persona_ap) FROM Medicos m WHERE m.medico_id =" + id, MedicoPersonaDTO.class ).getResultList();
	}
	
}
