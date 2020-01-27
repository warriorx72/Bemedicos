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

import com.bemedicos.springboot.app.models.entity.Calendario;
import com.bemedicos.springboot.app.service.CalendarioService;
import com.bemedicos.springboot.app.service.UserService;

@Controller
public class CalendarioController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CalendarioService calendarioService;

	
	@RequestMapping(value = "/agenda", method=RequestMethod.GET)
	public String agenda(HttpServletRequest request, Model model) {
		UserController us=new UserController();
		us.UsuarioDoctor(request,userService);
		model.addAttribute("id_med_user",us.UsuarioDoctor(request,userService));
		model.addAttribute("calendario",calendarioService.findAll());
		return "agenda";
	}
	@RequestMapping(value = "/agendar_cita", method=RequestMethod.GET)
	public String agendar(HttpServletRequest request, Model model, Map<String, Object> m) {
		Calendario calendario = new Calendario();
		UserController us = new UserController();
		us.UsuarioDoctor(request, userService);
		model.addAttribute("id_med_user", us.UsuarioDoctor(request, userService));
		m.put("calendario", calendario);
		return "agendar_cita";
	}
	@RequestMapping(value = "/guardar_cita", method = RequestMethod.POST)
	public String guardarCita(HttpServletRequest request, Model model, Map<String, Object> m, Calendario calendario) {
		UserController us = new UserController();
		calendario.setMedico_id(us.UsuarioDoctor(request, userService).longValue());
		calendarioService.save(calendario);
		m.put("calendario", calendario);
		return "redirect:citas";
	}
	
	@RequestMapping(value="/citas", method=RequestMethod.GET)
	public String citas(HttpServletRequest request, Model model) {
		UserController us=new UserController();
		us.UsuarioDoctor(request,userService);
		model.addAttribute("id_med_user",us.UsuarioDoctor(request,userService));
		model.addAttribute("calendario",calendarioService.findAll());
		return "citas";
	}
	
	@RequestMapping(value="/agendar_cita/{id}", method=RequestMethod.GET)
	public String editarCita(@PathVariable(value="id") Long id, Model model,  Map<String, Object> m) {
		if(id>0) {
			Calendario calendario=new Calendario();
			calendario=calendarioService.findOne(id);
			m.put("calendario",calendario);
		}
		else {
			return"redirect:citas";
		}
		
		return "agendar_cita";
	}
	//Hola
	
}
