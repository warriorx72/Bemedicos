package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedicos.springboot.app.models.entity.Direccion;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.DireccionService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PacienteService;
import com.bemedicos.springboot.app.service.PersonaService;

@Controller
public class PacienteController {
	@Autowired
	MedicoService medicoService;
	@Autowired
	PacienteService pacienteService;
	@Autowired
	DireccionService direccionService;
	@Autowired
	PersonaService personaService;
	
	@RequestMapping(value="/alta_paciente", method=RequestMethod.GET)
	public String crear(Model model, Map<String, Object> m) {
		Paciente paciente=new Paciente();
		Persona persona=new Persona();
		Direccion direccion=new Direccion();
		m.put("paciente", paciente);
		m.put("persona", persona);
		m.put("direccion", direccion);
		return "alta_paciente";
	   }
	@RequestMapping(value="/guardar_paciente", method=RequestMethod.POST)
	public String guardarPaciente(Model model, Map<String, Object> m, Direccion direccion, Persona persona, Paciente paciente) {
		direccion.setPersona(persona);
		persona.setDireccion(direccion);
		persona.setPaciente(paciente);
		paciente.setPersona(persona);
		direccionService.save(direccion);
		return "antecedentes_familiares";
	   }
}
