package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Estudio;

public interface EstudioService 
{	
	public List<Object> findAll();
	
	public void save (Estudio estudio);
	
	public Estudio findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object[]> select(Long id);
	
	public List<Object> findAllEstudios();
	
}
