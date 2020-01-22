package com.bemedicos.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_medico_paciente")
public class MedicoPaciente {
	private static final long serialVersionUID = 1L;	

	@Column
	private Long medico_id;
	
	@Id
	private Long paciente_id;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
