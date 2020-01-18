package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="app_paciente")
public class Paciente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paciente_id;
	
	@Column
	private String expediente;
	
	@Column
	private String estado_civil;
	
	@Column
	private Long persona_id;
	
	@ManyToMany(mappedBy = "paciente")
    private Set<Medicos> medico = new HashSet<>();

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public Long getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
	
	
}
