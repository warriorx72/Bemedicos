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
@Table(name="app_medicos")
public class Medicos implements Serializable {

	private static final long serialVersionUID = 1L;	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long medico_id;
	
	
	
	@Column
	private String especialidad_doc;
	
	@Column
	private String cedula_doc;
	
	
	@Column
	private String primer_consulta;
	
	@Column
	private String control_consulta;
	
	
	
	@Column
	private String foto_doc;
	
	
	
	@Column
	private Long paciente_id;
	
	
	
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
    @JoinTable(name = "app_medico_paciente",
        joinColumns = @JoinColumn(name = "medico_id", referencedColumnName = "medico_id"),
        inverseJoinColumns = @JoinColumn(name = "paciente_id", referencedColumnName = "paciente_id"))
    private Set<Paciente> paciente= new HashSet<>();

	public Set<Paciente> getPaciente() {
		return paciente;
	}

	public void setPaciente(Set<Paciente> paciente) {
		this.paciente = paciente;
	}

	public Long getMedicos_id() {
		return medico_id;
	}

	public void setMedicos_id(Long medicos_id) {
		this.medico_id = medicos_id;
	}

	

	public String getEspecialidad_doc() {
		return especialidad_doc;
	}

	public void setEspecialidad_doc(String especialidad_doc) {
		this.especialidad_doc = especialidad_doc;
	}

	public String getCedula_doc() {
		return cedula_doc;
	}

	public void setCedula_doc(String cedula_doc) {
		this.cedula_doc = cedula_doc;
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

	

	public String getFoto_doc() {
		return foto_doc;
	}

	public void setFoto_doc(String foto_doc) {
		this.foto_doc = foto_doc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
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

	
	
}
