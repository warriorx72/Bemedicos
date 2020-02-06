package com.bemedicos.springboot.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bemedicos.springboot.app.models.entity.Calendario;

public interface CalendarioService {
	
	public List<Calendario> findAll();
	
	public void save(Calendario calendario);
	
	public void delete(Long id);
	
	public Calendario findOne(Long id);

	public List<Calendario> findByDateBetween();

}
