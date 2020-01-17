package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_evolucion")
public class Evolucion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evolucion_id;
	
	@Column
	private String evolucion_clinicos;
	
	@Column
	private String evolucion;
	
	@Column
	private String paciente_id	;
	
	@Column
	private String evolucion_previos;
	
	@Column
	private String evolucion_pronostico;
	
	@Column
	private String evolucion_terapeutica;
	
	@Column
	private String fecha_evo;

	public Long getEvolucion_id() {
		return evolucion_id;
	}

	public void setEvolucion_id(Long evolucion_id) {
		this.evolucion_id = evolucion_id;
	}

	public String getEvolucion_clinicos() {
		return evolucion_clinicos;
	}

	public void setEvolucion_clinicos(String evolucion_clinicos) {
		this.evolucion_clinicos = evolucion_clinicos;
	}

	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public String getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(String paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getEvolucion_previos() {
		return evolucion_previos;
	}

	public void setEvolucion_previos(String evolucion_previos) {
		this.evolucion_previos = evolucion_previos;
	}

	public String getEvolucion_pronostico() {
		return evolucion_pronostico;
	}

	public void setEvolucion_pronostico(String evolucion_pronostico) {
		this.evolucion_pronostico = evolucion_pronostico;
	}

	public String getEvolucion_terapeutica() {
		return evolucion_terapeutica;
	}

	public void setEvolucion_terapeutica(String evolucion_terapeutica) {
		this.evolucion_terapeutica = evolucion_terapeutica;
	}

	public String getFecha_evo() {
		return fecha_evo;
	}

	public void setFecha_evo(String fecha_evo) {
		this.fecha_evo = fecha_evo;
	}
	
	
}
