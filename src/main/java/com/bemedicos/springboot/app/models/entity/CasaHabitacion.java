package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_casa_habitacion") 
public class CasaHabitacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long casahabitacion_id;
	
	@Column
	private String alergias;
	
	@Column
	private String cirugias;
	
	@Column
	private String enfermedades_adulto;
	
	@Column
	private String enfermedades_infancia;
	
	@Column
	private String inmunizacion;
	
	@Column
	private String material_vivienda;
	
	@Column
	private String numero_mascotas;
	
	@Column
	private String otro;

	@Column
	private String otro1;

	@Column
	private String otro2;

	@Column
	private String personas_viviendo_casa;
	
	@Column
	private String quien_receto;
	
	@Column
	private String servicios_basicos;
	
	@Column
	private String tipo_piso;
	
	@Column
	private String toma_medicementos;
	
	@Column
	private String traumetismo;
	
	@Column
	private String ventilacion_iluminacion;

	public Long getCasahabitacion_id() {
		return casahabitacion_id;
	}

	public void setCasahabitacion_id(Long casahabitacion_id) {
		this.casahabitacion_id = casahabitacion_id;
	}

	public String getAlergias() {
		return alergias;
	}

	public void setAlergias(String alergias) {
		this.alergias = alergias;
	}

	public String getCirugias() {
		return cirugias;
	}

	public void setCirugias(String cirugias) {
		this.cirugias = cirugias;
	}

	public String getEnfermedades_adulto() {
		return enfermedades_adulto;
	}

	public void setEnfermedades_adulto(String enfermedades_adulto) {
		this.enfermedades_adulto = enfermedades_adulto;
	}

	public String getEnfermedades_infancia() {
		return enfermedades_infancia;
	}

	public void setEnfermedades_infancia(String enfermedades_infancia) {
		this.enfermedades_infancia = enfermedades_infancia;
	}

	public String getInmunizacion() {
		return inmunizacion;
	}

	public void setInmunizacion(String inmunizacion) {
		this.inmunizacion = inmunizacion;
	}

	public String getMaterial_vivienda() {
		return material_vivienda;
	}

	public void setMaterial_vivienda(String material_vivienda) {
		this.material_vivienda = material_vivienda;
	}

	public String getNumero_mascotas() {
		return numero_mascotas;
	}

	public void setNumero_mascotas(String numero_mascotas) {
		this.numero_mascotas = numero_mascotas;
	}

	public String getOtro() {
		return otro;
	}

	public void setOtro(String otro) {
		this.otro = otro;
	}

	public String getOtro1() {
		return otro1;
	}

	public void setOtro1(String otro1) {
		this.otro1 = otro1;
	}

	public String getOtro2() {
		return otro2;
	}

	public void setOtro2(String otro2) {
		this.otro2 = otro2;
	}

	public String getPersonas_viviendo_casa() {
		return personas_viviendo_casa;
	}

	public void setPersonas_viviendo_casa(String personas_viviendo_casa) {
		this.personas_viviendo_casa = personas_viviendo_casa;
	}

	public String getQuien_receto() {
		return quien_receto;
	}

	public void setQuien_receto(String quien_receto) {
		this.quien_receto = quien_receto;
	}

	public String getServicios_basicos() {
		return servicios_basicos;
	}

	public void setServicios_basicos(String servicios_basicos) {
		this.servicios_basicos = servicios_basicos;
	}

	public String getTipo_piso() {
		return tipo_piso;
	}

	public void setTipo_piso(String tipo_piso) {
		this.tipo_piso = tipo_piso;
	}

	public String getToma_medicementos() {
		return toma_medicementos;
	}

	public void setToma_medicementos(String toma_medicementos) {
		this.toma_medicementos = toma_medicementos;
	}

	public String getTraumetismo() {
		return traumetismo;
	}

	public void setTraumetismo(String traumetismo) {
		this.traumetismo = traumetismo;
	}

	public String getVentilacion_iluminacion() {
		return ventilacion_iluminacion;
	}

	public void setVentilacion_iluminacion(String ventilacion_iluminacion) {
		this.ventilacion_iluminacion = ventilacion_iluminacion;
	}
	
	
	
}

