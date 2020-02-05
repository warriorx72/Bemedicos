package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Solicitud;

public interface SolicitudService 
{
	public void save(Solicitud solicitud);
	
	public void delete(Long id);
	
	public List<Solicitud> findAll();
	
	public Solicitud findOne(Long solicitud);
}
