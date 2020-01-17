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
		return "alta_paciente";
	   }
}
