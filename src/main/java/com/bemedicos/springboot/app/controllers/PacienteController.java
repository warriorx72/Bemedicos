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
		return "alta_paciente";
	}

	@RequestMapping(value = "/guardar_paciente", method = RequestMethod.POST)
	public String guardarPaciente(HttpServletRequest request, Model model, Map<String, Object> m, Direccion direccion,
			Persona persona, Paciente paciente) {
		Evolucion evolucion = new Evolucion();
		AntecedentesFamiliares antecedentesfamiliares = new AntecedentesFamiliares();
		MedicoPaciente medpa = new MedicoPaciente();
		UserController us = new UserController();

		if (paciente.getPaciente_id() == null) {
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
			m.put("paciente", paciente);
			m.put("persona", persona);
			m.put("direccion", direccion);
			m.put("antecedentesfamiliares", antecedentesfamiliares);
			m.put("evolucion", evolucion);
			evolucion.setPaciente_id(paciente.getPaciente_id());

			if (request.getParameter("action1").equals("0")) {
				return "antecedentes_familiares";
			} else {
				return "notas_evolucion";
			}
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
			antecedentesfamiliares = antecedentesfamiliaresservice
					.findOne(Long.parseLong(antecedentesfamiliaresservice.findByFamiliares(id2)));
			m.put("paciente", paciente);
			m.put("persona", persona);
			m.put("direccion", direccion);

			m.put("antecedentesfamiliares", antecedentesfamiliares);

			if (request.getParameter("action1").equals("0")) {
				return "antecedentes_familiares";
			} else {
				return "redirect:/historial_clinico";
			}
		}

	}

	@RequestMapping(value = "/antecedentes_familiares", method = RequestMethod.GET)
	public String listarantecedentesfamiliares(HttpServletRequest request, Model model, Map<String, Object> m) {
		AntecedentesFamiliares antecedentesfamiliares = new AntecedentesFamiliares();

		m.put("antecedentesfamiliares", antecedentesfamiliares);
		return "antecedentes_familiares";
	}

	@RequestMapping(value = "/antecedentes_familiares/{id}", method = RequestMethod.GET)
	public String editarfamilia(HttpServletRequest request, Model model, Map<String, Object> m,
			@PathVariable(value = "id") Long id) {
		AntecedentesFamiliares antecedentesfamiliares = new AntecedentesFamiliares();
		Paciente pacientes = new Paciente();
		pacientes = pacienteService.findOne(id);

		if (antecedentesfamiliaresservice.findByPaciente(id).equals("0")) {
			antecedentesfamiliares.setPaciente_id(pacientes.getPaciente_id());
			antecedentesfamiliaresservice.save(antecedentesfamiliares);
			antecedentesfamiliares = antecedentesfamiliaresservice.findOne(
					Long.parseLong(antecedentesfamiliaresservice.findByFamiliares(pacientes.getPaciente_id())));
			m.put("antecedentesfamiliares", antecedentesfamiliares);
		} else {
			antecedentesfamiliares = antecedentesfamiliaresservice.findOne(
					Long.parseLong(antecedentesfamiliaresservice.findByFamiliares(pacientes.getPaciente_id())));
			m.put("antecedentesfamiliares", antecedentesfamiliares);
		}
		return "antecedentes_familiares";
	}

	@RequestMapping(value = "/guardar_ant_fam", method = RequestMethod.POST)
	public String guardar2(AntecedentesFamiliares antecedentesfamiliares, Paciente paciente, HttpServletRequest request,
			Model model, SessionStatus status, Map<String, Object> m) {
		AntecedentesPersonales personales = new AntecedentesPersonales();
		Evolucion evolucion = new Evolucion();

		if (antecedentesfamiliares.getAntecedentesfam_id() == null) {
			antecedentesfamiliaresservice.save(antecedentesfamiliares);
			personales.setPaciente_id(antecedentesfamiliares.getPaciente_id());
			evolucion.setPaciente_id(paciente.getPaciente_id());
			m.put("antecedentesfamiliares", antecedentesfamiliares);
			m.put("personales", personales);
			m.put("evolucion", evolucion);

			if (request.getParameter("action1").equals("0")) {
				return "antecedentes_personales";
			} else {
				return "notas_evolucion";
			}
		}

		else {
			AntecedentesFamiliares antfamiliares = antecedentesfamiliaresservice
					.findOne(antecedentesfamiliares.getAntecedentesfam_id());
			antfamiliares = antecedentesfamiliares;
			personales = personalService
					.findOne(Long.parseLong(personalService.findByPaciente(paciente.getPaciente_id())));
			antecedentesfamiliaresservice.saveEntity(antfamiliares);
			m.put("antecedentesfamiliares", antecedentesfamiliares);
			m.put("personales", personales);
			m.put("evolucion", evolucion);
			m.put("personales", personales);

			if (request.getParameter("action1").equals("0")) {
				return "antecedentes_personales";
			} else {
				return "redirect:/historial_clinico";
			}
		}
	}

	@RequestMapping(value = "/antecedentes_personales", method = RequestMethod.GET)
	public String crear2(Model model, Map<String, Object> m, AntecedentesPersonales personales) {
		m.put("personales", personales);
		return "antecedentes_personales";
	}

	@RequestMapping(value = "/antecedentes_personales/{id}", method = RequestMethod.GET)
	public String crearAntecedentesFamiliares(HttpServletRequest request, Model model, Map<String, Object> m,
			@PathVariable(value = "id") Long id3) {
		AntecedentesPersonales antecedentespersonales = new AntecedentesPersonales();
		Paciente pacientes = new Paciente();
		pacientes = pacienteService.findOne(id3);

		if (personalService.findByPacientes(id3).equals("0")) {
			antecedentespersonales.setPaciente_id(pacientes.getPaciente_id());
			personalService.save(antecedentespersonales);
			antecedentespersonales = personalService
					.findOne(Long.parseLong(personalService.findByPaciente(pacientes.getPaciente_id())));
		} else {
			antecedentespersonales = personalService
					.findOne(Long.parseLong(personalService.findByPaciente(pacientes.getPaciente_id())));
		}
		m.put("personales", antecedentespersonales);
		return "antecedentes_personales";
	}

	@RequestMapping(value = "/guardar_personales", method = RequestMethod.POST)
	public String guardarPersonales(HttpServletRequest request, Paciente paciente, Model model, Map<String, Object> m,
			AntecedentesPersonales personales) {
		Evolucion evolucion = new Evolucion();
		CasaHabitacion casahabitacion = new CasaHabitacion();

		personalService.saveEntity(personales);

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

	@RequestMapping(value = "/casa_habitacion/{id}", method = RequestMethod.GET)
	public String casaHab(HttpServletRequest request, Model model, Map<String, Object> m, CasaHabitacion casahabitacion,
			@PathVariable(value = "id") Long id3) {
		CasaHabitacion casah = new CasaHabitacion();
		Paciente pac = new Paciente();
		pac = pacienteService.findOne(id3);
		System.out.println(id3);
		if (casaHabitacionService.findByPacienteId(id3).equals("0")) {
			casah.setPaciente_id(pac.getPaciente_id());
			casaHabitacionService.save(casah);
			casah = casaHabitacionService.findOne(Long.parseLong(casaHabitacionService.findBycasa(pac.getPaciente_id())));
		} else {
			casah = casaHabitacionService.findOne(Long.parseLong(casaHabitacionService.findBycasa(pac.getPaciente_id())));
		}		

		m.put("casahabitacion", casah);
		return "casa_habitacion";
	}

	@RequestMapping(value = "/guardar_casahabitacion", method = RequestMethod.POST)
	public String guardar3(HttpServletRequest request, Paciente paciente, CasaHabitacion casahabitacion, Model model,
			Persona persona, SessionStatus status, Map<String, Object> m) {
		Embarazos embarazos = new Embarazos();
		Evolucion evolucion = new Evolucion();

		if (casahabitacion.getCasahabitacion_id() == null) {
			casaHabitacionService.save(casahabitacion);
			//casahabitacion.setPaciente_id(casahabitacion.getPaciente_id());
			embarazos.setPaciente_id(casahabitacion.getPaciente_id());
			evolucion.setPaciente_id(paciente.getPaciente_id());
			m.put("casahabitacion", casahabitacion);
			m.put("embarazos", embarazos);
			m.put("evolucion", evolucion);
			String lol = pacienteService.findOne(paciente.getPaciente_id()).getPersona().getPersona_genero();

			if (request.getParameter("action1").equals("0") && lol.equals("Femenino")) {
				return "embarazos";
			} else {
				return "notas_evolucion";
			}	
			
		} else {

			CasaHabitacion casahabitacions = casaHabitacionService.findOne(casahabitacion.getCasahabitacion_id());
			// embarazos.setPaciente_id(casahabitacion.getPaciente_id());
//			evolucion.setPaciente_id(paciente.getPaciente_id());
			casahabitacions = casahabitacion;
			embarazos = embarazosService.findbyPacienteId(paciente.getPaciente_id());
			casaHabitacionService.save2(casahabitacions);
			m.put("casahabitacion", casahabitacions);
			m.put("embarazos", embarazos);
			m.put("evolucion", evolucion);

			Paciente paci = pacienteService.findOne(paciente.getPaciente_id());
			String lol = paci.getPersona().getPersona_genero();
						
			if (request.getParameter("action1").equals("0") && lol.equals("Femenino")) {
				return "embarazos";
			} else {
				return "redirect:/historial_clinico";
			}

		}
	}

	@RequestMapping(value = "/embarazos/{id4}", method = RequestMethod.GET)
	public String crearEmbarazos(Model model, Map<String, Object> m, Embarazos embarazos,
			@PathVariable(value = "id4") Long id4) {
		// Se busca el embarazo que coincida con el id enviado.
		Embarazos emb = embarazosService.findbyPacienteId(id4);
		Embarazos embarazoEnviar = new Embarazos();

		// Validacion para saber si existe el embarazo
		if (emb != null) {
			// Si existe el embarazo, solo se iguala al que se va a enviar
			embarazoEnviar = emb;
		}

		// Se envia el objeto al template.
		embarazoEnviar.setPaciente_id(id4);
		m.put("embarazos", embarazoEnviar);
		return "embarazos";
	}

	@RequestMapping(value = "/guardar_embarazos", method = RequestMethod.POST)
	public String guardarEmbarazos(HttpServletRequest request, Model model, Map<String, Object> m, Paciente paciente,
			Embarazos embarazos, String procedencia) {
		boolean flag;
		// Se pregunta si el id_embarazo es nulo, de serlo, es porque viene del
		// historial.
		if (embarazos.getEmbarazos_id() != null) {
			// Solo se va a editar, regresa a Historial Clinico
			Embarazos embarazoViejo = embarazosService.findbyPacienteId(paciente.getPaciente_id());
			embarazoViejo = embarazos;
			embarazosService.save(embarazoViejo);
			flag = true;
		} else {
			// Se creo uno nuevo, tons se manda a Notas evolucion
			embarazosService.save(embarazos);
			flag = false;
		}

		if (flag) {
			UserController us = new UserController();
			us.UsuarioDoctor(request, userService);
			model.addAttribute("id_med_user", us.UsuarioDoctor(request, userService));
			model.addAttribute("evolucionvista",
					evolucionService.AppNotas(us.UsuarioDoctor(request, userService).longValue()));

			return "historial_clinico";
		} else {
			Evolucion evolucion = new Evolucion();
			m.put("evolucion", evolucion);
			return "notas_evolucion";
		}
	}

	@RequestMapping(value = "/notas_evolucion", method = RequestMethod.GET)
	public String crearNotasEvolucion(HttpServletRequest request, Model model, Map<String, Object> m) {
		Evolucion evolucion = new Evolucion();
		m.put("notasEvolucion", evolucion);

		return "notas_evolucion";
	}
}
