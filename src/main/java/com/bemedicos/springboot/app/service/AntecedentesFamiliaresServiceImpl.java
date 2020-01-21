package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.AntecedentesFamiliares;
import com.bemedicos.springboot.app.repository.AntecedentesFamiliaresRepository;
@Service
public class AntecedentesFamiliaresServiceImpl implements AntecedentesFamiliaresService {
	@Autowired
	private AntecedentesFamiliaresRepository repository;
	@Override
	@Transactional(readOnly=true)
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

}
