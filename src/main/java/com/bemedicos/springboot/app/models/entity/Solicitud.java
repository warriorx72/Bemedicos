package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_solicitud")
public class Solicitud implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long solicitud_id;
	
	@Column
	private Long medico_id;
	
	@Column
	private Long paciente_id;
	
	@Column
	private Long monto;
	
	@Column
	private Long estatus;
	
	public Long getSolicitud_id() {
		return solicitud_id;
	}

	public void setSolicitud_id(Long solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	
	public Long getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(Long medico_id) {
		this.medico_id = medico_id;
	}

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}
	
	public Long getEstatus_id() {
		return estatus;
	}

	public void setEstatus_id(Long estatus) {
		this.estatus = estatus;
	}
}
