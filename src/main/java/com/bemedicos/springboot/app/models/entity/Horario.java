package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="horario")
public class Horario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long horario_id;
	
	@Column
	private String horario_dia;
	
	@Column
	private String horario_hora_inicial;
	
	@Column
	private String horario_hora_final;
	
	@Column
	private String horario_duracion;
	
	@Column
	private String medico_id;

	public Long getHorario_id() {
		return horario_id;
	}

	public void setHorario_id(Long horario_id) {
		this.horario_id = horario_id;
	}

	public String getHorario_dia() {
		return horario_dia;
	}

	public void setHorario_dia(String horario_dia) {
		this.horario_dia = horario_dia;
	}

	public String getHorario_hora_inicial() {
		return horario_hora_inicial;
	}

	public void setHorario_hora_inicial(String horario_hora_inicial) {
		this.horario_hora_inicial = horario_hora_inicial;
	}

	public String getHorario_hora_final() {
		return horario_hora_final;
	}

	public void setHorario_hora_final(String horario_hora_final) {
		this.horario_hora_final = horario_hora_final;
	}

	public String getHorario_duracion() {
		return horario_duracion;
	}

	public void setHorario_duracion(String horario_duracion) {
		this.horario_duracion = horario_duracion;
	}

	public String getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(String medico_id) {
		this.medico_id = medico_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
