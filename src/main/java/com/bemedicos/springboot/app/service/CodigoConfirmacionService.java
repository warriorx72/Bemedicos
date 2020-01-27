package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.CodigoConfirmacion;
import com.bemedicos.springboot.app.models.entity.Paciente;

public interface CodigoConfirmacionService 
{
	public void save(CodigoConfirmacion CodigoConfirmacion);
	
	public void delete(Long id);
	
	public CodigoConfirmacion findOne(Long id);
	
	public CodigoConfirmacion findByCodigo(String token);
	
	public CodigoConfirmacion findByusermed(int id);
}
