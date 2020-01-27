package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Calendario;

public interface CalendarioService {
	
	public List<Calendario> findAll();
	
	public void save(Calendario calendario);
	
	public void delete(Long id);
	
	public Calendario findOne(Long id);

}
