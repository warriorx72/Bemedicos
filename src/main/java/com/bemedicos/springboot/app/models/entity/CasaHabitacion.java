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
	private String cual;
	
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
	private String especie_mascotas;
	
	@Column
	private String otro_material_vivienda;

	@Column
	private String otro_enfermedad_infancia;

	@Column
	private String otro_enfermedad_adulto;

	@Column
	private String personas_viviendo_casa;
	
	@Column
	private String quien_receto;
	
	@Column
	private String servicios_basicos;
	
	@Column
	private String tipo_piso;
	
	@Column
	private String toma_medicamento;
	
	@Column
	private String traumatismo;
	
	@Column
	private String ventilacion_iluminacion;
	
	@Column
	private Long paciente_id;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public String getCual() {
		return cual;
	}

	public void setCual(String cual) {
		this.cual = cual;
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

	public String getEspecie_mascotas() {
		return especie_mascotas;
	}

	public void setEspecie_mascotas(String especie_mascotas) {
		this.especie_mascotas = especie_mascotas;
	}

	public String getOtro_material_vivienda() {
		return otro_material_vivienda;
	}

	public void setOtro_material_vivienda(String otro_material_vivienda) {
		this.otro_material_vivienda = otro_material_vivienda;
	}

	public String getOtro_enfermedad_infancia() {
		return otro_enfermedad_infancia;
	}

	public void setOtro_enfermedad_infancia(String otro_enfermedad_infancia) {
		this.otro_enfermedad_infancia = otro_enfermedad_infancia;
	}

	public String getOtro_enfermedad_adulto() {
		return otro_enfermedad_adulto;
	}

	public void setOtro_enfermedad_adulto(String otro_enfermedad_adulto) {
		this.otro_enfermedad_adulto = otro_enfermedad_adulto;
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

	public String getToma_medicamento() {
		return toma_medicamento;
	}

	public void setToma_medicamento(String toma_medicamento) {
		this.toma_medicamento = toma_medicamento;
	}

	public String getTraumatismo() {
		return traumatismo;
	}

	public void setTraumatismo(String traumatismo) {
		this.traumatismo = traumatismo;
	}

	public String getVentilacion_iluminacion() {
		return ventilacion_iluminacion;
	}

	public void setVentilacion_iluminacion(String ventilacion_iluminacion) {
		this.ventilacion_iluminacion = ventilacion_iluminacion;
	}

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	
}

