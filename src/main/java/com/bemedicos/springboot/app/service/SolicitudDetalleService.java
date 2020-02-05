package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Solicitud_Detalle;

public interface SolicitudDetalleService 
{
	public void save(Solicitud_Detalle solicitud_detalle);
	
	public void delete(Long id);
	
	public List<Solicitud_Detalle> findAll();
	
	public Solicitud_Detalle findOne(Long solicitud_detalle);
}
