package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bemedicos.springboot.app.models.entity.Embarazos;
import com.bemedicos.springboot.app.repository.EmbarazosRepository;

@Service
public class EmbarazosServiceImpl implements EmbarazosService {

@Autowired
private EmbarazosRepository repository;
@Override
@Transactional(readOnly=true)
public List<Embarazos> findAlll() {
// TODO Auto-generated method stub
return (List<Embarazos>) repository.findAll();
}

@Override
@Transactional
public void save(Embarazos embarazos) {
// TODO Auto-generated method stub
repository.save(embarazos);
}

@Override
@Transactional
public void delete(Long id) {
// TODO Auto-generated method stub
repository.deleteById(id);
}

@Override
@Transactional(readOnly=true)
public Embarazos findOne(Long id) {
// TODO Auto-generated method stub
return repository.findById(id).orElse(null);
}

@Override
public Embarazos findbyPacienteId(Long id) {
	return repository.findByPacienteId(id).orElse(null);
}
}