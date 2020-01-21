package com.bemedicos.springboot.app.service;

import java.util.List;
import java.util.Optional;

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
@Override
@Transactional(readOnly=true)
public List<AntecedentesPersonales> findAll(){
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
@Transactional(readOnly=true)
public AntecedentesPersonales findOne(Long id) {
// TODO Auto-generated method stub
return repository.findById(id).orElse(null);
}
}