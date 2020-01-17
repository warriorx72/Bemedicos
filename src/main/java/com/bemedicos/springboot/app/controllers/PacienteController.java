package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.service.MedicoService;

@Controller
public class PacienteController {
	@Autowired
	MedicoService medicoService;
	
	@RequestMapping(value="/alta_paciente", method=RequestMethod.GET)
	public String listar(Model model, Map<String, Object> m) {
		model.addAttribute("titulo","Condiciones paciente");
		Medicos medico =new Medicos();
		Paciente paciente1 = new Paciente();
		Paciente paciente2 = new Paciente();
		paciente1.setEstado_civil("soltero");
		paciente1.setExpediente("1234");
		paciente1.setPersona_id("2");
		paciente2.setEstado_civil("casado");
		paciente2.setExpediente("1233");
		paciente2.setPersona_id("3");
		medico=medicoService.findOne(1L);
		medico.getPaciente().add(paciente1);
		medico.getPaciente().add(paciente2);
		medicoService.save(medico);
		return "alta_paciente";
	   }
}
