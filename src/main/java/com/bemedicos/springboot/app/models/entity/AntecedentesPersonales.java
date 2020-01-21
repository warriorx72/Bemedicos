package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_antecedentes_personales")
public class AntecedentesPersonales implements Serializable{

private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long antecedentespersonales_id;

@Column
private String actividad_fisica;

@Column
private String alimentacion;

@Column
private String baño;

@Column
private String especifique;

@Column
private String especifique1;

@Column
private String frecuencias;

@Column
private String grupo_recreacion;

@Column
private String lavado_de_manos;

@Column
private String lavado_dientes;

@Column
private String otro;

@Column
private String otro1;

@Column
private String toxicomanias;

@Column
private String veces_al_dia;

@Column
private Long paciente_id;

public Long getAntecedentespersonales_id() {
return antecedentespersonales_id;
}

public void setAntecedentespersonales_id(Long antecedentespersonales_id) {
this.antecedentespersonales_id = antecedentespersonales_id;
}

public String getActividad_fisica() {
return actividad_fisica;
}

public void setActividad_fisica(String actividad_fisica) {
this.actividad_fisica = actividad_fisica;
}

public String getAlimentacion() {
return alimentacion;
}

public void setAlimentacion(String alimentacion) {
this.alimentacion = alimentacion;
}

public String getBaño() {
return baño;
}

public void setBaño(String baño) {
this.baño = baño;
}

public String getEspecifique() {
return especifique;
}

public void setEspecifique(String especifique) {
this.especifique = especifique;
}

public String getEspecifique1() {
return especifique1;
}

public void setEspecifique1(String especifique1) {
this.especifique1 = especifique1;
}

public String getFrecuencias() {
return frecuencias;
}

public void setFrecuencias(String frecuencias) {
this.frecuencias = frecuencias;
}

public String getGrupo_recreacion() {
return grupo_recreacion;
}

public void setGrupo_recreacion(String grupo_recreacion) {
this.grupo_recreacion = grupo_recreacion;
}

public String getLavado_de_manos() {
return lavado_de_manos;
}

public void setLavado_de_manos(String lavado_de_manos) {
this.lavado_de_manos = lavado_de_manos;
}

public String getLavado_dientes() {
return lavado_dientes;
}

public void setLavado_dientes(String lavado_dientes) {
this.lavado_dientes = lavado_dientes;
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

public String getToxicomanias() {
return toxicomanias;
}

public void setToxicomanias(String toxicomanias) {
this.toxicomanias = toxicomanias;
}

public String getVeces_al_dia() {
return veces_al_dia;
}

public void setVeces_al_dia(String veces_al_dia) {
this.veces_al_dia = veces_al_dia;
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