package com.bemedicos.springboot.app.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	public String crear(Map<String, Object> model, HttpServletRequest request, Model m) throws ParseException {
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
	
	@RequestMapping(value = "/horario_reserva", method = RequestMethod.POST)
	public String fechas(@RequestParam(name = "start") String startparam,@RequestParam(name = "end") String endparam,
			HttpServletRequest request) {
		UserController user = new UserController();
	List<LocalDate> totalDates = new ArrayList<>();
	if(startparam.equals("hoy")) {
		Date currentdate = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(currentdate);
		LocalDate start = LocalDate.parse(modifiedDate);
		horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		if(endparam.equals("5dias")) {
			LocalDate end=start.plusDays(5);
		while (start.isBefore(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		}
		}
		else if(endparam.equals("10dias")){
			LocalDate end=start.plusDays(10);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			    //for(int i=0;i<horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService)).size();i++) 
			    //{
			    	//importante para bloquear boton System.out.println(horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService)).get(0));
			    	
			   // }
			    
			}
			
		}
		else if(endparam.equals("15dias")){
			LocalDate end=start.plusDays(15);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		
	
	}
	else if(startparam.equals("maniana")) {
		Date currentdate = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(currentdate);
		LocalDate start2 = LocalDate.parse(modifiedDate);
		LocalDate start=start2.plusDays(1);
		 horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		if(endparam.equals("5dias")) {
			LocalDate end=start.plusDays(5);
		while (start.isBefore(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		}
		}
		else if(endparam.equals("10dias")){
			LocalDate end=start.plusDays(10);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		else if(endparam.equals("15dias")){
			LocalDate end=start.plusDays(15);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		
	
		
	}
	else if(startparam.equals("2dias")) {
		Date currentdate = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(currentdate);
		LocalDate start2 = LocalDate.parse(modifiedDate);
		LocalDate start=start2.plusDays(2);
		 horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		if(endparam.equals("5dias")) {
			LocalDate end=start.plusDays(5);
		while (start.isBefore(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		}
		}
		else if(endparam.equals("10dias")){
			LocalDate end=start.plusDays(10);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		else if(endparam.equals("15dias")){
			LocalDate end=start.plusDays(15);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		
	
		
	}
	else if(startparam.equals("3dias")) {
		Date currentdate = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(currentdate);
		LocalDate start2 = LocalDate.parse(modifiedDate);
		LocalDate start=start2.plusDays(3);
		 horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		if(endparam.equals("5dias")) {
			LocalDate end=start.plusDays(5);
		while (start.isBefore(end)) {
		    totalDates.add(start);
		    start = start.plusDays(1);
		    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
		}
		}
		else if(endparam.equals("10dias")){
			LocalDate end=start.plusDays(10);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			}
			
		}
		else if(endparam.equals("15dias")){
			LocalDate end=start.plusDays(15);
			while (start.isBefore(end)) {
			    totalDates.add(start);
			    start = start.plusDays(1);
			    horarioService.genCitas(start.toString(),user.UsuarioDoctor(request, userService));
			   
			}
			
		}
		
	
		
	}
	
	
	return "redirect:horario";
	}
	
}
