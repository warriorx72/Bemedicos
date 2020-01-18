package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="persona") 
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long persona_id;
	
	@Column
	private String persona_nombre;
	
	@Column
	private String persona_ap;
	
	@Column
	private String persona_am;
	
	@Column
	private String persona_fecha_na;

	@Column
	private String persona_rfc;
	
	@Column
	private String persona_genero;
	@Column
	private String persona_email;
	
	@Column
	private String persona_tel_casa;
	
	@Column
	private String persona_tel_cel;
	
	@Column
	private String persona_tel_oficina;
	
	@Column
	private String persona_tel_exten;
	
	
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Paciente paciente;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;
    
	public Long getPersona_id() {
		return persona_id;
	}
	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
	public String getPersona_nombre() {
		return persona_nombre;
	}
	public void setPersona_nombre(String persona_nombre) {
		this.persona_nombre = persona_nombre;
	}
	public String getPersona_ap() {
		return persona_ap;
	}
	public void setPersona_ap(String persona_ap) {
		this.persona_ap = persona_ap;
	}
	public String getPersona_am() {
		return persona_am;
	}
	public void setPersona_am(String persona_am) {
		this.persona_am = persona_am;
	}
	public String getPersona_fecha_na() {
		return persona_fecha_na;
	}
	public void setPersona_fecha_na(String persona_fecha_na) {
		this.persona_fecha_na = persona_fecha_na;
	}
	public String getPersona_rfc() {
		return persona_rfc;
	}
	public void setPersona_rfc(String persona_rfc) {
		this.persona_rfc = persona_rfc;
	}
	public String getPersona_genero() {
		return persona_genero;
	}
	public void setPersona_genero(String persona_genero) {
		this.persona_genero = persona_genero;
	}
	public String getPersona_email() {
		return persona_email;
	}
	public void setPersona_email(String persona_email) {
		this.persona_email = persona_email;
	}
	public String getPersona_tel_casa() {
		return persona_tel_casa;
	}
	public void setPersona_tel_casa(String persona_tel_casa) {
		this.persona_tel_casa = persona_tel_casa;
	}
	public String getPersona_tel_cel() {
		return persona_tel_cel;
	}
	public void setPersona_tel_cel(String persona_tel_cel) {
		this.persona_tel_cel = persona_tel_cel;
	}
	public String getPersona_tel_oficina() {
		return persona_tel_oficina;
	}
	public void setPersona_tel_oficina(String persona_tel_oficina) {
		this.persona_tel_oficina = persona_tel_oficina;
	}
	public String getPersona_tel_exten() {
		return persona_tel_exten;
	}
	public void setPersona_tel_exten(String persona_tel_exten) {
		this.persona_tel_exten = persona_tel_exten;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
}
