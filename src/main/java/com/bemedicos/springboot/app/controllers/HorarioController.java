package com.bemedicos.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bemedicos.springboot.app.dto.HorarioDTO;
import com.bemedicos.springboot.app.models.entity.Horario;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.CalendarioService;
import com.bemedicos.springboot.app.service.HorarioService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.UserService;

@Controller
public class HorarioController {

	@Autowired
	MedicoService medicoService;
	@Autowired
	PersonaService personaService;
	@Autowired
	UserService userService;

	@Autowired
	HorarioService horarioService;
	@Autowired
	CalendarioService calendarioService;

	@GetMapping(value = "/horario")
	public String crear(Map<String, Object> model, HttpServletRequest request, Model m) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		UserController user = new UserController();
		String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		HorarioDTO h = new HorarioDTO();

		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());

		Boolean aux = horarioService.exist(medicos.getMedico_id());

		if (aux) {
			for (Object a : horarioService.findByMedicoId(medicos.getMedico_id())) {

				Horario aH = new Horario();
				aH = horarioService.findOne(Long.parseLong(a.toString()));

				h.addHorario(aH, medicos.getMedico_id().toString(),
						horarioService.findOne(Long.parseLong(a.toString())).getHorario_duracion(),
						horarioService.findOne(Long.parseLong(a.toString())).getHorario_dia());
						}
		} else {
			// crear nuevos registros
			for (int i = 0; i < dias.length; i++) {
				h.addHorario(new Horario(), medicos.getMedico_id().toString(), "", "");
			}
		}
		
		if(h.horarios.get(0).getHorario_duracion().equals("15")) {
			m.addAttribute("qui", true);
			m.addAttribute("tre", false);
			m.addAttribute("cua", false);
			m.addAttribute("ses", false);
		}
		if(h.horarios.get(0).getHorario_duracion().equals("30")) {
			m.addAttribute("qui", false);
			m.addAttribute("tre", true);
			m.addAttribute("cua", false);
			m.addAttribute("ses", false);
		}			
		if(h.horarios.get(0).getHorario_duracion().equals("45")) {
			m.addAttribute("qui", false);
			m.addAttribute("tre", false);
			m.addAttribute("cua", true);
			m.addAttribute("ses", false);
		}
		if(h.horarios.get(0).getHorario_duracion().equals("60")) {
			m.addAttribute("qui", false);
			m.addAttribute("tre", false);
			m.addAttribute("cua", false);
			m.addAttribute("ses", true);
		}
		m.addAttribute("semanas", h.horarios);
		model.put("medicos", medicos);
		model.put("horario", h);
		m.addAttribute("dias", dias);
		return "horario";
	}

	@RequestMapping(value = "/horario", method = RequestMethod.POST)
	public String guardar(Map<String, Object> model, @ModelAttribute HorarioDTO h, HttpServletRequest request,
			@RequestParam(name = "duracionSelect") String duracion, BindingResult result) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		UserController user = new UserController();
		String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };
		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());

		for (int j = 0; j < dias.length; j++) {
			h.horarios.get(j).setHorario_dia(dias[j]);
			h.horarios.get(j).setHorario_duracion(duracion);
			horarioService.save(h.getHorarios().get(j));
		}
		return "redirect:/horario";
	}
}
