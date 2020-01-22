package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	private Long paciente_id;
	
	@Column
	private String evolucion_previos;
	
	@Column
	private String evolucion_evolucion;
	
	@Column
	private String evolucion_pronostico;
	
	@Column
	private String evolucion_terapeutica;

	@Column
	private String evolucion_tension;
	
	@Column
	private String evolucion_temperatura;
	
	@Column
	private String evolucion_cardiaca;
	
	@Column
	private String evolucion_respiratoria;
	
	@Column
	private String evolucion_peso;
	
	@Column
	private String evolucion_talla;
	
	@Column
	private String evolucion_id_text;
	

	@CreationTimestamp
	private LocalDateTime fecha_evo;

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

	public String getEvolucion_evolucion() {
		return evolucion_evolucion;
	}

	public void setEvolucion_evolucion(String evolucion_evolucion) {
		this.evolucion_evolucion = evolucion_evolucion;
	}

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
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

	public String getEvolucion_tension() {
		return evolucion_tension;
	}

	public void setEvolucion_tension(String evolucion_tension) {
		this.evolucion_tension = evolucion_tension;
	}

	public String getEvolucion_temperatura() {
		return evolucion_temperatura;
	}

	public void setEvolucion_temperatura(String evolucion_temperatura) {
		this.evolucion_temperatura = evolucion_temperatura;
	}

	public String getEvolucion_cardiaca() {
		return evolucion_cardiaca;
	}

	public void setEvolucion_cardiaca(String evolucion_cardiaca) {
		this.evolucion_cardiaca = evolucion_cardiaca;
	}

	public String getEvolucion_respiratoria() {
		return evolucion_respiratoria;
	}

	public void setEvolucion_respiratoria(String evolucion_respiratoria) {
		this.evolucion_respiratoria = evolucion_respiratoria;
	}

	public String getEvolucion_peso() {
		return evolucion_peso;
	}

	public void setEvolucion_peso(String evolucion_peso) {
		this.evolucion_peso = evolucion_peso;
	}

	public String getEvolucion_talla() {
		return evolucion_talla;
	}

	public void setEvolucion_talla(String evolucion_talla) {
		this.evolucion_talla = evolucion_talla;
	}
	
	public String getEvolucion_id_text() {
		return evolucion_id_text;
	}

	public void setEvolucion_id_text(String evolucion_id_text) {
		this.evolucion_id_text = evolucion_id_text;
	}

	public LocalDateTime getFecha_evo() {
		return fecha_evo;
	}

	public void setFecha_evo(LocalDateTime fecha_evo) {
		this.fecha_evo = fecha_evo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
