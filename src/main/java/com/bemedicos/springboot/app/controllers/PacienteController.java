package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.bemedicos.springboot.app.models.entity.AntecedentesFamiliares;
import com.bemedicos.springboot.app.models.entity.AntecedentesPersonales;
import com.bemedicos.springboot.app.models.entity.CasaHabitacion;
import com.bemedicos.springboot.app.models.entity.Direccion;
import com.bemedicos.springboot.app.models.entity.Embarazos;
import com.bemedicos.springboot.app.models.entity.Evolucion;
import com.bemedicos.springboot.app.models.entity.MedicoPaciente;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.AntecedentesFamiliaresService;
import com.bemedicos.springboot.app.service.CasaHabitacionService;
import com.bemedicos.springboot.app.service.DireccionService;
import com.bemedicos.springboot.app.service.EmbarazosService;
import com.bemedicos.springboot.app.service.EvolucionService;
import com.bemedicos.springboot.app.service.MedicoPacienteService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PacienteService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.PersonalesService;
import com.bemedicos.springboot.app.service.UserService;

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

	@Autowired
	PersonalesService personalService;

	@Autowired
	AntecedentesFamiliaresService antecedentesfamiliaresservice;

	@Autowired
	CasaHabitacionService casaHabitacionService;

	@Autowired
	EmbarazosService embarazosService;

	@Autowired
	UserService userService;

	@Autowired
	MedicoPacienteService medpaService;

	@Autowired
	EvolucionService evolucionService;

	public Long id2;

	@RequestMapping(value = "/alta_paciente/{id}", method = RequestMethod.GET)
	public String crearDatos(HttpServletRequest request, Model model, Map<String, Object> m,
			@PathVariable(value = "id") Long id) {

		UserController us = new UserController();
		Paciente pacientes = new Paciente();
		Persona personas = new Persona();
		Direccion direccions = new Direccion();

		us.UsuarioDoctor(request, userService);
		model.addAttribute("id_med_user", us.UsuarioDoctor(request, userService));

		pacientes = pacienteService.findOne(id);
		personas = personaService.findOne(Long.parseLong(personaService.findByPaciente(id).toString()));
		direccions = direccionService.findOne(Long.parseLong(personaService.findByDireccion(id).toString()));

		m.put("paciente", pacientes);
		m.put("persona", personas);
		m.put("direccion", direccions);

		id2 = id;
		return "/alta_paciente";
	}

	@RequestMapping(value = "/alta_paciente", method = RequestMethod.GET)
	public String crear(HttpServletRequest request, Model model, Map<String, Object> m) {
		Paciente paciente = new Paciente();
		Persona persona = new Persona();
		Direccion direccion = new Direccion();
		UserController us = new UserController();
		us.UsuarioDoctor(request, userService);
		model.addAttribute("id_med_user", us.UsuarioDoctor(request, userService));
		m.put("paciente", paciente);
		m.put("persona", persona);
		m.put("direccion", direccion);
		return "alta_paciente";
	}

	@RequestMapping(value = "/guardar_paciente", method = RequestMethod.POST)
	public String guardarPaciente(HttpServletRequest request, Model model, Map<String, Object> m, Direccion direccion,
			Persona persona, Paciente paciente) 
	{
		AntecedentesFamiliares antecedentesfamiliares = new AntecedentesFamiliares();
		MedicoPaciente medpa = new MedicoPaciente();
		UserController us = new UserController();
		
		if (paciente.getPaciente_id()==null ) {
			direccion.setPersona(persona);
			persona.setDireccion(direccion);
			persona.setPaciente(paciente);
			paciente.setPersona(persona);

			direccionService.save(direccion);

			antecedentesfamiliares.setPaciente_id(paciente.getPaciente_id());
			medpa.setPaciente_id(paciente.getPaciente_id());
			medpa.setMedico_id(us.UsuarioDoctor(request, userService).longValue());
			medpaService.save(medpa);

			paciente.setExpediente("pac-" + medpa.getMedico_id() + "-" + (10000 + paciente.getPaciente_id()));
			pacienteService.save(paciente);
			System.out.println("dssssssssssssssssssssssssssssss"+antecedentesfamiliares.getAntecedentesfam_id());
			m.put("paciente", paciente);
			m.put("persona", persona);
			m.put("direccion", direccion);
			m.put("antecedentesfamiliares", antecedentesfamiliares);
		}

		else {
			
			Paciente pacientes = pacienteService.findOne(paciente.getPaciente_id());
			Persona personas = personaService.findOne(Long.parseLong(personaService.findByPaciente(id2).toString()));
			Direccion direccions = direccionService
					.findOne(Long.parseLong(personaService.findByDireccion(id2).toString()));

			direccions = direccion;
			direccionService.save(direccions);			
			personas = persona;
			personas.setPersona_id(personas.getPersona_id());
			personas.setDireccion(direccions);
			personaService.save(personas);

			pacientes = paciente;
			pacientes.setPersona(personas);
			
			pacienteService.save(pacientes);
			// direccionService.save(direccions);
			antecedentesfamiliares = antecedentesfamiliaresservice.findOne(Long.parseLong(antecedentesfamiliaresservice.findByFamiliares(id2)));

			// pacienteService.save(pacientes);

			m.put("paciente", paciente);
			m.put("persona", persona);
			m.put("direccion", direccion);
			m.put("antecedentesfamiliares", antecedentesfamiliares);
		}
		
		if (request.getParameter("action1").equals("0")) {
			return "antecedentes_familiares";
		}
		return "notas_evolucion";
	}

	@RequestMapping(value = "/antecedentes_familiares", method = RequestMethod.GET)
	public String listarantecedentesfamiliares(HttpServletRequest request, Model model, Map<String, Object> m) {
		AntecedentesFamiliares antecedentesfamiliares = new AntecedentesFamiliares();
		

		m.put("antecedentesfamiliares", antecedentesfamiliares);
		return "antecedentes_familiares";
	}

	@RequestMapping(value = "/guardar_ant_fam", method = RequestMethod.POST)
	public String guardar2(AntecedentesFamiliares antecedentesfamiliares, Paciente paciente, HttpServletRequest request,
			Model model, SessionStatus status, Map<String, Object> m) {
		AntecedentesPersonales personales = new AntecedentesPersonales();
		Evolucion evolucion = new Evolucion();
		System.out.println(paciente.getPaciente_id());
		
		antecedentesfamiliaresservice.save(antecedentesfamiliares);
		personales.setPaciente_id(antecedentesfamiliares.getPaciente_id());

		evolucion.setPaciente_id(paciente.getPaciente_id());
		m.put("antecedentesfamiliares", antecedentesfamiliares);
		m.put("personales", personales);
		m.put("evolucion", evolucion);
		
		
		if (request.getParameter("action1").equals("0")) {
			return "antecedentes_personales";
		}
		return "notas_evolucion";
	}

	@RequestMapping(value = "/antecedentes_personales", method = RequestMethod.GET)
	public String crear2(Model model, Map<String, Object> m, AntecedentesPersonales personales) {
		m.put("personales", personales);
		return "antecedentes_personales";
	}

	@RequestMapping(value = "/guardar_personales", method = RequestMethod.POST)
	public String guardarPersonales(HttpServletRequest request, Paciente paciente, Model model, Map<String, Object> m,
			AntecedentesPersonales personales) {
		Evolucion evolucion = new Evolucion();
		CasaHabitacion casahabitacion = new CasaHabitacion();
		personalService.save(personales);
		casahabitacion.setPaciente_id(personales.getPaciente_id());
		m.put("personales", personales);
		m.put("casahabitacion", casahabitacion);
		m.put("evolucion", evolucion);

		evolucion.setPaciente_id(paciente.getPaciente_id());
		if (request.getParameter("action1").equals("0")) {
			return "casa_habitacion";
		}
		return "notas_evolucion";
	}

	@RequestMapping(value = "/casa_habitacion", method = RequestMethod.GET)
	public String listarcasahabitacion(Model model, Map<String, Object> m, CasaHabitacion casahabitacion) {
		m.put("casahabitacion", casahabitacion);
		return "casa_habitacion";
	}

	@RequestMapping(value = "/guardar_casahabitacion", method = RequestMethod.POST)
	public String guardar3(HttpServletRequest request, Paciente paciente, CasaHabitacion casahabitacion, Model model,
			Persona persona, SessionStatus status, Map<String, Object> m) {
		Embarazos embarazos = new Embarazos();
		Evolucion evolucion = new Evolucion();

		casaHabitacionService.save(casahabitacion);

		embarazos.setPaciente_id(casahabitacion.getPaciente_id());
		evolucion.setPaciente_id(paciente.getPaciente_id());

		m.put("casahabitacion", casahabitacion);
		m.put("embarazos", embarazos);
		m.put("evolucion", evolucion);

		String lol = pacienteService.findOne(paciente.getPaciente_id()).getPersona().getPersona_genero();

		if (request.getParameter("action1").equals("0") && lol.equals("Femenino")) {
			return "embarazos";
		}
		return "notas_evolucion";

	}

	@RequestMapping(value = "/embarazos", method = RequestMethod.GET)
	public String crearEmbarazos(Model model, Map<String, Object> m, Embarazos embarazos) {
		m.put("embarazos", embarazos);
		return "embarazos";
	}

	@RequestMapping(value = "/guardar_embarazos", method = RequestMethod.POST)
	public String guardarEmbarazos(HttpServletRequest request, Model model, Map<String, Object> m, Paciente paciente,
			Embarazos embarazos) {
		Evolucion evolucion = new Evolucion();

		embarazosService.save(embarazos);
		evolucion.setPaciente_id(paciente.getPaciente_id());
		m.put("evolucion", evolucion);
		return "notas_evolucion";
	}

	@RequestMapping(value = "/notas_evolucion", method = RequestMethod.GET)
	public String crearNotasEvolucion(HttpServletRequest request, Model model, Map<String, Object> m) {
		Evolucion evolucion = new Evolucion();
		m.put("notasEvolucion", evolucion);

		return "notas_evolucion";
	}

}
