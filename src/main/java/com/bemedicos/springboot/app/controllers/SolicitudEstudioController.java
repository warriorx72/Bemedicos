package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedicos.springboot.app.models.entity.Estudio;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.service.EstudioService;
import com.bemedicos.springboot.app.service.PacienteService;
import com.bemedicos.springboot.app.service.UserService;


@Controller
public class SolicitudEstudioController 
{	
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private EstudioService estudioService;
	
	
	@RequestMapping(value="/solicitud_estudios/{id}", method=RequestMethod.GET)
	public String listarNotas(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) 
	{
		Paciente paciente = new Paciente();
		
		if(id>0) 
		{
			paciente = pacienteService.findOne(id);
		}
		else 
		{
			return "redirect:historial_clinico";
		}
		
		m.addAttribute("paciente", paciente.getPersona());
		m.addAttribute("estudio", estudioService.findAll());
		m.addAttribute("id_paciente", id);
		return "solicitud_estudios1";	
	}
}
