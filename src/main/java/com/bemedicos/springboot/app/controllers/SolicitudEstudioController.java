package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedicos.springboot.app.models.entity.Estudio;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.EstudioService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PacienteService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.UserService;


@Controller
public class SolicitudEstudioController 
{	
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private EstudioService estudioService;
	
	@Autowired
	private MedicoService medicoService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PersonaService personaService;
	
	@RequestMapping(value="/solicitud_estudios/{id}", method=RequestMethod.GET)
	public String listarNotas(HttpServletRequest request, @PathVariable(value="id") Long id, Map<String, Object> model,Model m) 
	{
		Paciente paciente = new Paciente();
		Persona per = new Persona();
		UserController us = new UserController();
		
		us.UsuarioDoctor(request,userService);
		Medicos med = medicoService.findOne(Long.valueOf(us.UsuarioDoctor(request,userService)));
		per = personaService.findOne(Long.valueOf(med.getMedico_id()));
		
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
		m.addAttribute("medico_info", med);
		m.addAttribute("medico_info_persona", per);
		
		return "solicitud_estudios1";	
	}
}
