package com.bemedicos.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_embarazos")
public class Embarazos implements Serializable{

private static final long serialVersionUID = 1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long embarazos_id;

@Column
private String cesareas;

@Column
private String metodo_anticonceptivos;

@Column
private String numero_abortos;

@Column
private String numero_embarazos;

@Column
private String partos_naturales;

@Column
private Long paciente_id;


public Long getEmbarazos_id() {
return embarazos_id;
}

public void setEmbarazos_id(Long embarazos_id) {
this.embarazos_id = embarazos_id;
}

public String getCesareas() {
return cesareas;
}

public void setCesareas(String cesareas) {
this.cesareas = cesareas;
}

public String getMetodo_anticonceptivos() {
return metodo_anticonceptivos;
}

public void setMetodo_anticonceptivos(String metodos_anticonceptivos) {
this.metodo_anticonceptivos = metodos_anticonceptivos;
}

public String getNumero_abortos() {
return numero_abortos;
}

public void setNumero_abortos(String numeros_abortos) {
this.numero_abortos = numeros_abortos;
}

public String getNumero_embarazos() {
return numero_embarazos;
}

public void setNumero_embarazos(String numeros_embarazos) {
this.numero_embarazos = numeros_embarazos;
}

public String getPartos_naturales() {
return partos_naturales;
}

public void setPartos_naturales(String partos_naturales) {
this.partos_naturales = partos_naturales;
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