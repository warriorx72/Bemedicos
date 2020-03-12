package com.bemedicos.springboot.app.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.bemedicos.springboot.app.models.entity.Direccion;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.service.DireccionService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.UploadFileService;
import com.bemedicos.springboot.app.service.UserService;

@Controller
@SessionAttributes("medicos")
public class MedicoController {

	@Autowired
	MedicoService medicoService;
	@Autowired
	PersonaService personaService;
	@Autowired
	UserService userService;
	@Autowired
	DireccionService direccionService;
	@Autowired
	UploadFileService uFileService;

	@GetMapping(value = "/uploads/{filename:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String filename) {

		Resource recurso = null;
		try {
			recurso = uFileService.load(filename);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"")
				.body(recurso);
	}

	@GetMapping(value = "/perfil")
	public String crear(Map<String, Object> model, HttpServletRequest request) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		Direccion direccion = new Direccion();
		UserController user = new UserController();

		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());
		
		if(persona.getDireccion() != null)
		{
			direccion = direccionService.findOne(persona.getDireccion().getDireccion_id());
		}
		
		model.put("persona", persona);
		model.put("medicos", medicos);
		model.put("direccion", direccion);

		return "perfil";
	}

	@RequestMapping(value = "/perfil", method = RequestMethod.POST)
	public String guardar(Medicos medicos, Direccion direccion, Model model, HttpServletRequest request, Persona persona, @RequestParam("file") MultipartFile foto) {
		UserController user = new UserController();

		if(!foto.isEmpty()) {
			if (medicos.getMedico_id() != null && medicos.getMedico_id() > 0 && medicos.getMedico_foto() != null
					&& medicos.getMedico_foto().length() > 0) {
				uFileService.delete(medicos.getMedico_foto());
			}
			String uniqueFileName = null;
			try {
				uniqueFileName = uFileService.copy(foto);
				medicos.setMedico_foto(uniqueFileName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Direccion oldDir = direccionService.findOne(direccion.getDireccion_id());
		oldDir.setDireccion_calle(direccion.getDireccion_calle());
		oldDir.setDireccion_colonia(direccion.getDireccion_colonia());
		oldDir.setDireccion_estado(direccion.getDireccion_estado());
		oldDir.setDireccion_municipio(direccion.getDireccion_municipio());
		oldDir.setDireccion_num_inter(direccion.getDireccion_num_inter());
		oldDir.setDireccion_numero_ext(direccion.getDireccion_numero_ext());
		oldDir.setDireccion_postal(direccion.getDireccion_postal());
		medicos.setPersona(persona);
		persona.setMedicos(medicos);
		persona.setDireccion(oldDir);
		personaService.save(persona);
		
		return "redirect:perfil";
	}

	@GetMapping(value = "/ver")
	public String ver(Map<String, Object> model, HttpServletRequest request) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		UserController user = new UserController();

		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());

		model.put("cliente", medicos);
		model.put("persona", persona);
		return "/ver";
	}
	

	
/* El siguiente método es para eliminar, por el momento está sin utilizar
	@RequestMapping(value = "/eliminar")
	public String eliminar(Map<String, Object> model, HttpServletRequest request) {
		Medicos medicos = new Medicos();
		Persona persona = new Persona();
		UserController user = new UserController();

		medicos = medicoService.findOne(user.UsuarioDoctor(request, userService).longValue());
		persona = personaService.findOne(medicos.getPersona().getPersona_id());

		medicoService.delete(medicos.getMedicos_id());
		return "redirect:/ver";
	}
	*/
}
