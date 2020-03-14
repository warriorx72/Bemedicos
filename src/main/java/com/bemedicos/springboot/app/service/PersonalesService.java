package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.AntecedentesPersonales;

public interface PersonalesService {

public List<AntecedentesPersonales> findAll();

public void save(AntecedentesPersonales personales);

public void delete(Long id);

public AntecedentesPersonales findOne(Long id);

public String findByPaciente(Long id);

public void saveEntity(AntecedentesPersonales personales);

public String findByPacientes(Long id);
}