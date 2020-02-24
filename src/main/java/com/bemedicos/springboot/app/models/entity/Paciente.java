package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
	private String paciente_referencia_nombre;
	
	@Column
	private String paciente_referencia_ap;
	
	@Column
	private String paciente_referencia_am;
	
	@Column 
	private String paciente_referencia_tel;
	
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;
	
	@ManyToMany(mappedBy = "paciente")
    private Set<Medicos> medico = new HashSet<>();

	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<Medicos> getMedico() {
		return medico;
	}

	public void setMedico(Set<Medicos> medico) {
		this.medico = medico;
	}

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

	public String getPaciente_referencia_nombre() {
		return paciente_referencia_nombre;
	}

	public void setPaciente_referencia_nombre(String paciente_referencia_nombre) {
		this.paciente_referencia_nombre = paciente_referencia_nombre;
	}

	public String getPaciente_referencia_ap() {
		return paciente_referencia_ap;
	}

	public void setPaciente_referencia_ap(String paciente_referencia_ap) {
		this.paciente_referencia_ap = paciente_referencia_ap;
	}

	public String getPaciente_referencia_am() {
		return paciente_referencia_am;
	}

	public void setPaciente_referencia_am(String paciente_referencia_am) {
		this.paciente_referencia_am = paciente_referencia_am;
	}

	public String getPaciente_referencia_tel() {
		return paciente_referencia_tel;
	}

	public void setPaciente_referencia_tel(String paciente_referencia_tel) {
		this.paciente_referencia_tel = paciente_referencia_tel;
	}

	
	
}
