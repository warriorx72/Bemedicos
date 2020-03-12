package com.bemedicos.springboot.app.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.AntecedentesFamiliares;
import com.bemedicos.springboot.app.repository.AntecedentesFamiliaresRepository;

@Service
public class AntecedentesFamiliaresServiceImpl implements AntecedentesFamiliaresService {
	@Autowired
	private AntecedentesFamiliaresRepository repository;

	@Autowired
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public List<AntecedentesFamiliares> findAll() {
		// TODO Auto-generated method stub
		return (List<AntecedentesFamiliares>) repository.findAll();
	}

	@Override
	@Transactional
	public void save(AntecedentesFamiliares antecedentesfamiliares) {
		// TODO Auto-generated method stub
		repository.save(antecedentesfamiliares);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public AntecedentesFamiliares findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).orElse(null);
	}

	@Override
	public String findByFamiliares(Long id) {
		// TODO Auto-generated method stub
		return em
				.createNativeQuery("SELECT antecedentesfam_id FROM app_antecedentes_familiares af WHERE paciente_id =" + id)
				.getSingleResult().toString();
	}

	@Override
	@Transactional
	public void saveEntity(AntecedentesFamiliares antecedentesfamiliares) {
		if (antecedentesfamiliares.getAntecedentesfam_id() != null
				&& antecedentesfamiliares.getAntecedentesfam_id() > 0) {
			em.merge(antecedentesfamiliares);
		} else {
			em.persist(antecedentesfamiliares);
		}
	}

}
