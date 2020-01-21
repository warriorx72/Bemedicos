package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_antecedentes_familiares") 
public class AntecedentesFamiliares implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long antecedentesfam_id;
	
	@Column
	private String accidente_cerebrovascular;
	
	@Column
	private String alergias;
	
	@Column
	private String anemia_leucemia_linfoma_mieloma;
	
	@Column
	private String artritis;
	
	@Column
	private String cancer;
	
	@Column
	private String depresion_bipolaridad_demencia;
	
	@Column 
	private String diabetes;

	@Column
	private String distrofia_muscular;
	
	@Column
	private String hepatitis_cirrosis_sx_de_reye;
	
	@Column
	private String neumonia_asma_epoc_rinitis;
	
	@Column
	private String otros;
	
	@Column
	private String parkinson_alzheimer;
	
	@Column
	private String problemas_cardiacos;
	
	@Column
	private String problemas_de_tiroides;
	
	@Column
	private	String tension_arterial_alta;
	
	@Column
	private	String tubercolosis;
	
	@Column
	private Long paciente_id;
		

	public Long getAntecedentesfam_id() {
		return antecedentesfam_id;
	}

	public void setAntecedentesfam_id(Long antecedentesfam_id) {
		this.antecedentesfam_id = antecedentesfam_id;
	}

	public String getAccidente_cerebrovascular() {
		return accidente_cerebrovascular;
	}

	public void setAccidente_cerebrovascular(String accidente_cerebrovascular) {
		this.accidente_cerebrovascular = accidente_cerebrovascular;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getAnemia_leucemia_linfoma_mieloma() {
		return anemia_leucemia_linfoma_mieloma;
	}

	public void setAnemia_leucemia_linfoma_mieloma(String anemia_leucemia_linfoma_mieloma) {
		this.anemia_leucemia_linfoma_mieloma = anemia_leucemia_linfoma_mieloma;
	}

	public String getArtritis() {
		return artritis;
	}

	public void setArtritis(String artritis) {
		this.artritis = artritis;
	}

	public String getCancer() {
		return cancer;
	}

	public void setCancer(String cancer) {
		this.cancer = cancer;
	}

	public String getDepresion_bipolaridad_demencia() {
		return depresion_bipolaridad_demencia;
	}

	public void setDepresion_bipolaridad_demencia(String depresion_bipolaridad_demencia) {
		this.depresion_bipolaridad_demencia = depresion_bipolaridad_demencia;
	}

	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

	public String getDistrofia_muscular() {
		return distrofia_muscular;
	}

	public void setDistrofia_muscular(String distrofia_muscular) {
		this.distrofia_muscular = distrofia_muscular;
	}

	public String getHepatitis_cirrosis_sx_de_reye() {
		return hepatitis_cirrosis_sx_de_reye;
	}

	public void setHepatitis_cirrosis_sx_de_reye(String hepatitis_cirrosis_sx_de_reye) {
		this.hepatitis_cirrosis_sx_de_reye = hepatitis_cirrosis_sx_de_reye;
	}

	public String getNeumonia_asma_epoc_rinitis() {
		return neumonia_asma_epoc_rinitis;
	}

	public void setNeumonia_asma_epoc_rinitis(String neumonia_asma_epoc_rinitis) {
		this.neumonia_asma_epoc_rinitis = neumonia_asma_epoc_rinitis;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getParkinson_alzheimer() {
		return parkinson_alzheimer;
	}

	public void setParkinson_alzheimer(String parkinson_alzheimer) {
		this.parkinson_alzheimer = parkinson_alzheimer;
	}

	public String getProblemas_cardiacos() {
		return problemas_cardiacos;
	}

	public void setProblemas_cardiacos(String problemas_cardiacos) {
		this.problemas_cardiacos = problemas_cardiacos;
	}

	public String getProblemas_de_tiroides() {
		return problemas_de_tiroides;
	}

	public void setProblemas_de_tiroides(String problemas_de_tiroides) {
		this.problemas_de_tiroides = problemas_de_tiroides;
	}

	public String getTension_arterial_alta() {
		return tension_arterial_alta;
	}

	public void setTension_arterial_alta(String tension_arterial_alta) {
		this.tension_arterial_alta = tension_arterial_alta;
	}

	public String getTubercolosis() {
		return tubercolosis;
	}

	public void setTubercolosis(String tubercolosis) {
		this.tubercolosis = tubercolosis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	
}
