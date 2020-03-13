package com.bemedicos.springboot.app.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedicos.springboot.app.models.entity.Embarazos;
import com.bemedicos.springboot.app.models.entity.User;

@Repository
public interface EmbarazosRepository extends CrudRepository<Embarazos, Long>
{
	public Optional<Embarazos> findByPacienteId(Long id);
}