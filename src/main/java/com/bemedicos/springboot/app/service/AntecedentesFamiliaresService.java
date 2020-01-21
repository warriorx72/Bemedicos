package com.bemedicos.springboot.app.service;

import java.util.List;

import com.bemedicos.springboot.app.models.entity.AntecedentesFamiliares;

public interface AntecedentesFamiliaresService {
	
	public List <AntecedentesFamiliares> findAll();
	
	public void save(AntecedentesFamiliares antecedentesfamiliares);
	
	public void delete(Long id);
	
	public AntecedentesFamiliares findOne(Long id);
}
