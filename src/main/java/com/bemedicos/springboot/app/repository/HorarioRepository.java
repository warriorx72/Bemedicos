package com.bemedicos.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bemedicos.springboot.app.models.entity.Horario;

public interface HorarioRepository extends CrudRepository<Horario, Long> {
	
}
