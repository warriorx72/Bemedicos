package com.bemedicos.springboot.app.dto;

import java.util.ArrayList;
import java.util.List;

import com.bemedicos.springboot.app.models.entity.Horario;

public class HorarioDTO {
	public List<Horario> horarios = new ArrayList<>();

	public void addHorario(Horario horario, String id, String duracion, String dia) {
		horario.setMedico_id(id);
		horario.setHorario_duracion(duracion);
		horario.setHorario_dia(dia);
		horarios.add(horario);

	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}

}
