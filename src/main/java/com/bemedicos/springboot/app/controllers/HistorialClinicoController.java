package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedicos.springboot.app.models.entity.Evolucion;
import com.bemedicos.springboot.app.models.entity.Paciente;
import com.bemedicos.springboot.app.service.EvolucionService;
import com.bemedicos.springboot.app.service.PacienteService;
import com.bemedicos.springboot.app.service.UserService;

@Controller
public class HistorialClinicoController {

	@Autowired
	private EvolucionService evolucionService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/historial_clinico", method=RequestMethod.GET)
    public String listar(HttpServletRequest request, Model model) {
		UserController us=new UserController();
		us.UsuarioDoctor(request,userService);
		model.addAttribute("id_med_user",us.UsuarioDoctor(request,userService));
		model.addAttribute("evolucionvista", evolucionService.AppNotas(us.UsuarioDoctor(request,userService).longValue()));

		return "historial_clinico";
	   }
	
	@RequestMapping(value="/notas/{id}", method=RequestMethod.GET)
	public String listarNotas(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) {
		 
		Paciente paciente = new Paciente();
		if(id>0) {
			paciente = pacienteService.findOne(id);
		}
		else {
			return "redirect:historial_clinico";
		}
		m.addAttribute("evolucionlistar", evolucionService.AppListarNotas(id));
		m.addAttribute("id",id);
		return "notas";		
	}
	
	@RequestMapping(value="/notas_evolucion/{id}", method=RequestMethod.GET)
	public String crearNotas(@PathVariable(value="id") Long id, Model model, Map<String, Object> m) {
		Evolucion evolucion=new Evolucion();
		model.addAttribute("crearnota", evolucionService.AppCrearNotas(id));
		evolucion.setPaciente_id(id);
		m.put("evolucion", evolucion);
		return "notas_evolucion";
	}
	
	@RequestMapping(value="/guardar_notas", method=RequestMethod.POST)
	public String guardarEmbarazos(Model model, Map<String, Object> m, Evolucion evolucion) {
		evolucionService.save(evolucion);
		if(evolucion.getEvolucion_id_text()==null) {
			evolucion.setEvolucion_id_text("evo"+(evolucion.getEvolucion_id()+10000));
		}
		evolucionService.save(evolucion);
		System.out.println("Aqui estoy, bien evolucionado");
		return "redirect:historial_clinico";
		
	   }
	
	@RequestMapping(value="/notas_evolucion/{id}/{id2}", method=RequestMethod.GET)
    public String editarNotas(@PathVariable(value="id") Long id, @PathVariable(value="id2") Long id2, Model model,  Map<String, Object> m) {
		if(id>0&id2>0) {
			Evolucion evolucion=new Evolucion();
			evolucion=evolucionService.findOne(id2); 
			evolucion.setEvolucion_id(id2);
			evolucion.setPaciente_id(id);
			m.put("evolucion", evolucion);
		}
		else {
		 return "redirect:historial_clinico";
		}
		return "notas_evolucion";
	   }

}
