package com.bemedicos.springboot.app.models.dao;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.Medicos;

public interface IMedicosDao {
	
	public List<Medicos> findAll();
	
	public Medicos findOne(Long MedicosId);
	
	public void save(Medicos medicosApp);

}
