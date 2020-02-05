package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "app_medicos")
public class Medicos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medico_id;

	@Column
	private String medico_especialidad;

	@Column
	private String medico_cedula;

	@Column
	private String primer_consulta;

	@Column
	private String control_consulta;

	@Column
	private String medico_foto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
	private Persona persona;

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "app_medico_paciente", joinColumns = @JoinColumn(name = "medico_id", referencedColumnName = "medico_id"), inverseJoinColumns = @JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id"))
	private Set<Paciente> paciente = new HashSet<>();

	public Set<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(Set<Paciente> paciente) {
		this.paciente = paciente;
	}

	public Long getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(Long medico_id) {
		this.medico_id = medico_id;
	}

	public String getMedico_especialidad() {
		return medico_especialidad;
	}

	public void setMedico_especialidad(String medico_especialidad) {
		this.medico_especialidad = medico_especialidad;
	}
	
	public String getMedico_cedula() {
		return medico_cedula;
	}

	public void setMedico_cedula(String medico_cedula) {
		this.medico_cedula = medico_cedula;
	}

	public String getPrimer_consulta() {
		return primer_consulta;
	}

	public void setPrimer_consulta(String primer_consulta) {
		this.primer_consulta = primer_consulta;
	}

	public String getControl_consulta() {
		return control_consulta;
	}

	public void setControl_consulta(String control_consulta) {
		this.control_consulta = control_consulta;
	}

	public String getMedico_foto() {
		return medico_foto;
	}

	public void setMedico_foto(String medico_foto) {
		this.medico_foto = medico_foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
