package com.bemedicos.springboot.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bemedicos.springboot.app.models.entity.Horario;

public interface HorarioService {
	
	public List<Horario> findAll();
	
	public Horario findonde(Long horario_id);
	
	public void save(Horario horario);
	
	public void delete(Long id);

}
