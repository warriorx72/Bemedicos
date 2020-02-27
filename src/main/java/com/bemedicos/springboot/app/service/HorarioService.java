package com.bemedicos.springboot.app.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.bemedicos.springboot.app.models.entity.Horario;

public interface HorarioService {

	public List<Horario> findAll();

	public Horario findOne(Long horario_id);

	public void save(Horario horario);

	public void delete(Long id);

	public void saveAll(List<Horario> h);

	public boolean exist(Long id);

	public List <Object[]> findByMedicoId(Long id);

	List<Object> genCitas(String fecha,Integer med_id);
	

}
