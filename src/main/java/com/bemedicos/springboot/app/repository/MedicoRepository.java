package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bemedicos.springboot.app.models.entity.Medicos;

public interface MedicoRepository extends CrudRepository<Medicos, Long>{

}
