package com.bemedicos.springboot.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bemedicos.springboot.app.dto.MedicoPersonaDTO;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.UserService;

@RestController
public class NavegacionRestController {

	@Autowired
	private MedicoService medicoService;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private UserService userService;

	@GetMapping(value = "/fotos")
	public List<MedicoPersonaDTO> getMedicos(HttpServletRequest request) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		UserController user = new UserController();

		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());
		
		return medicoService.findAllById(medicos.getMedico_id());
	}

}
