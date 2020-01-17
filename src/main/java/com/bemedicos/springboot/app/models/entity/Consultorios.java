package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_consultorios")
public class Consultorios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long consultorio_id;
	
	@Column
	private String consultorio_id_text;
	
	@Column
	private String horario;
	
	@Column
	private String consultorio_telefono	;
	
	@Column
	private String direccion_id;

	public Long getConsultorio_id() {
		return consultorio_id;
	}

	public void setConsultorio_id(Long consultorio_id) {
		this.consultorio_id = consultorio_id;
	}

	public String getConsultorio_id_text() {
		return consultorio_id_text;
	}

	public void setConsultorio_id_text(String consultorio_id_text) {
		this.consultorio_id_text = consultorio_id_text;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getConsultorio_telefono() {
		return consultorio_telefono;
	}

	public void setConsultorio_telefono(String consultorio_telefono) {
		this.consultorio_telefono = consultorio_telefono;
	}

	public String getDireccion_id() {
		return direccion_id;
	}

	public void setDireccion_id(String direccion_id) {
		this.direccion_id = direccion_id;
	}
	
	
}
