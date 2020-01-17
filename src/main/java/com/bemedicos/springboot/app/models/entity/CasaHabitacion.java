package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="casa_habitacion") 
public class CasaHabitacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="casahabitacion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CasaHabitacionId;
	
	@Column(name="alergias")
	private String Alergias;
	
	@Column(name="cirugias")
	private String Cirugias;
	
	@Column(name="enfermedades_adulto")
	private String EnfermedadesAdulto;
	
	@Column(name="enfermedades_infancia")
	private String EnfermedadesInfancia;
	
	@Column(name="inmunizacion")
	private String Inmunizacion;
	
	@Column(name="material_vivienda")
	private String MaterialVivienda;
	
	@Column(name="numero_mascotas")
	private String NumeroMascotas;
	
	@Column(name="otro")
	private String Otro;

	@Column(name="otro1")
	private String Otro1;

	@Column(name="otro2")
	private String Otro2;

	@Column(name="personas_viviendo_casa")
	private String PersonasViviendoCasa;
	
	@Column(name="quien_receto")
	private String QuienReceto;
	
	@Column(name="servicios_basicos")
	private String ServiciosBasicos;
	
	@Column(name="tipo_piso")
	private String TipoPiso;
	
	@Column(name="toma_medicementos")
	private String TomaMedicementos;
	
	@Column(name="traumetismo")
	private String Traumatismo;
	
	@Column(name="ventilacion_iluminacion")
	private String VentilacionIluminacion;

	public Long getCasaHabitacionId() {
		return CasaHabitacionId;
	}

	public void setCasaHabitacionId(Long casaHabitacionId) {
		CasaHabitacionId = casaHabitacionId;
	}

	public String getAlergias() {
		return Alergias;
	}

	public void setAlergias(String alergias) {
		Alergias = alergias;
	}

	public String getCirugias() {
		return Cirugias;
	}

	public void setCirugias(String cirugias) {
		Cirugias = cirugias;
	}

	public String getEnfermedadesAdulto() {
		return EnfermedadesAdulto;
	}

	public void setEnfermedadesAdulto(String enfermedadesAdulto) {
		EnfermedadesAdulto = enfermedadesAdulto;
	}

	public String getEnfermedadesInfancia() {
		return EnfermedadesInfancia;
	}

	public void setEnfermedadesInfancia(String enfermedadesInfancia) {
		EnfermedadesInfancia = enfermedadesInfancia;
	}

	public String getInmunizacion() {
		return Inmunizacion;
	}

	public void setInmunizacion(String inmunizacion) {
		Inmunizacion = inmunizacion;
	}

	public String getMaterialVivienda() {
		return MaterialVivienda;
	}

	public void setMaterialVivienda(String materialVivienda) {
		MaterialVivienda = materialVivienda;
	}

	public String getNumeroMascotas() {
		return NumeroMascotas;
	}

	public void setNumeroMascotas(String numeroMascotas) {
		NumeroMascotas = numeroMascotas;
	}

	public String getOtro() {
		return Otro;
	}

	public void setOtro(String otro) {
		Otro = otro;
	}

	public String getOtro1() {
		return Otro1;
	}

	public void setOtro1(String otro1) {
		Otro1 = otro1;
	}

	public String getOtro2() {
		return Otro2;
	}

	public void setOtro2(String otro2) {
		Otro2 = otro2;
	}

	public String getPersonasViviendoCasa() {
		return PersonasViviendoCasa;
	}

	public void setPersonasViviendoCasa(String personasViviendoCasa) {
		PersonasViviendoCasa = personasViviendoCasa;
	}

	public String getQuienReceto() {
		return QuienReceto;
	}

	public void setQuienReceto(String quienReceto) {
		QuienReceto = quienReceto;
	}

	public String getServiciosBasicos() {
		return ServiciosBasicos;
	}

	public void setServiciosBasicos(String serviciosBasicos) {
		ServiciosBasicos = serviciosBasicos;
	}

	public String getTipoPiso() {
		return TipoPiso;
	}

	public void setTipoPiso(String tipoPiso) {
		TipoPiso = tipoPiso;
	}

	public String getTomaMedicementos() {
		return TomaMedicementos;
	}

	public void setTomaMedicementos(String tomaMedicementos) {
		TomaMedicementos = tomaMedicementos;
	}

	public String getTraumatismo() {
		return Traumatismo;
	}

	public void setTraumatismo(String traumatismo) {
		Traumatismo = traumatismo;
	}

	public String getVentilacionIluminacion() {
		return VentilacionIluminacion;
	}

	public void setVentilacionIluminacion(String ventilacionIluminacion) {
		VentilacionIluminacion = ventilacionIluminacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}

