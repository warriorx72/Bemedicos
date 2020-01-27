package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_calendario")
public class Calendario implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long calendario_id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellidopa;
	
	@Column
	private String apellidoma;
	
	@Column
	private String fecha_cita;
	
	@Column
	private String hora_cita;
	
	@Column
	private String motivo;
	
	@Column
	private String correo;
	
	@Column
	private Long medico_id;

	public Long getCalendario_id() {
		return calendario_id;
	}

	public void setCalendario_id(Long calendario_id) {
		this.calendario_id = calendario_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidopa() {
		return apellidopa;
	}

	public void setApellidopa(String apellidopa) {
		this.apellidopa = apellidopa;
	}

	public String getApellidoma() {
		return apellidoma;
	}

	public void setApellidoma(String apellidoma) {
		this.apellidoma = apellidoma;
	}

	public String getFecha_cita() {
		return fecha_cita;
	}

	public void setFecha_cita(String fecha_cita) {
		this.fecha_cita = fecha_cita;
	}

	public String getHora_cita() {
		return hora_cita;
	}

	public void setHora_cita(String hora_cita) {
		this.hora_cita = hora_cita;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Long getMedico_id() {
		return medico_id;
	}

	public void setMedico_id(Long medico_id) {
		this.medico_id = medico_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	//Hola
}
