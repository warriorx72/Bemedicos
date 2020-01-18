package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.Persona;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}
