package com.bemedicos.springboot.app.controllers;
import java.util.Enumeration;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.validation.Valid;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import com.bemedicos.springboot.app.dto.ChangePasswordForm;
import com.bemedicos.springboot.app.exception.CustomeFieldValidationException;
import com.bemedicos.springboot.app.models.entity.CodigoConfirmacion;
import com.bemedicos.springboot.app.models.entity.Direccion;
import com.bemedicos.springboot.app.models.entity.Medicos;
import com.bemedicos.springboot.app.models.entity.Persona;
import com.bemedicos.springboot.app.models.entity.User;
import com.bemedicos.springboot.app.repository.UserRepository;
import com.bemedicos.springboot.app.service.CodigoConfirmacionService;
import com.bemedicos.springboot.app.repository.CodigoConfirmacionRepository;
import com.bemedicos.springboot.app.service.DireccionService;
import com.bemedicos.springboot.app.service.MedicoService;
import com.bemedicos.springboot.app.service.PersonaService;
import com.bemedicos.springboot.app.service.UserService;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;


@Controller

public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MedicoService medicoDao;
	
	@Autowired
	DireccionService direccionDao;
	
	@Autowired
	PersonaService personaDao;
	
	@Autowired
	CodigoConfirmacionRepository CodigoConfirmacionDao;

    @Autowired
    public JavaMailSender emailSender;
	
	
	
	
	
	@GetMapping("/signup")
	public String signup(Model model) {
		
		model.addAttribute("signup",true);
		model.addAttribute("userForm", new User());
		model.addAttribute("direccion", new Direccion());
		model.addAttribute("persona", new Persona());
		model.addAttribute("medico", new Medicos());
		return "user-form/user-signup";
	}
	
	
	
	
	@PostMapping("/signup")
   	public String postSignup(@Valid @ModelAttribute("direccion")Direccion direccion,@ModelAttribute("persona")Persona persona, @ModelAttribute("medico")Medicos medico, @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
	
		model.addAttribute("userForm", user);
	
		model.addAttribute("signup",true);
		if(result.hasErrors()) {
			return "user-form/user-signup";
		}else {
			try {
				///user.setMedico_id(2);
				userService.createUser(user);
				///direccion.setDireccionCalle("independencia");
		/////sirve		direccionDao.save(direccion);
		////sirve		persona.setPersona_nombre(user.getFirstName());
		////sirve		persona.setPersona_ap(user.getLastName());
				////persona.setPersonaAm("lopez");
				///persona.setIdDireccion(direccion.getDireccionId());
		///sirve		personaDao.save(persona);
				////medico.setMedico_cedula("dfasp");
				///medico.setPersona_id(persona.getPersonaId());
				direccion.setPersona(persona);
				persona.setDireccion(direccion);
				persona.setMedicos(medico);
				persona.setPersona_nombre(user.getFirstName());
				persona.setPersona_ap(user.getLastName());
				medico.setPersona(persona);
				direccionDao.save(direccion);
			////sirve	medicoDao.save(medico);
				userService.updateUser(user);
			   user.setMedico_id(medico.getMedico_id());
			  //// System.out.println(medico.getMedico_id());
			   userService.updateUser(user);
				
			} 
			catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				return "user-form/user-signup";
			}
			catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				return "user-form/user-signup";
			}
			
		}//End_else
		
		
		// Se crea el Mail
        SimpleMailMessage message = new SimpleMailMessage();
        
        //Se genera el codigo
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        
        //Con el codigo generado se almacena el registro en la BD
		CodigoConfirmacion cc = new CodigoConfirmacion();
		
		cc.setCodigo(randomUUIDString);
		cc.setusermed(user.getId().intValue());
		
		CodigoConfirmacionDao.save(cc);
        
        
        message.setTo(user.getEmail());
        message.setSubject("Bienvenido a Bemédia: " + user.getFirstName() + " " + user.getLastName());
        message.setText("Hola, Gracias por registrarte, da clic al enlace para confirmar tu email: " 
        + "http://localhost:8090/confirmar-correo?token=" + randomUUIDString );
 
        // Send Message!
        this.emailSender.send(message);
        
		return "redirect:/login?send=correo_enviado";
	}//End
	
	@GetMapping({"/","/login"})
	public String index() {
		return "login";
	}
	
	
	@GetMapping("/index")
	public String getUserForm(HttpServletRequest request,@ModelAttribute("userForm")User user, BindingResult result, ModelMap model) throws Exception {
	   
		///System.out.println("usuario doctor"+UsuarioDoctor(req));
	  UsuarioDoctor(request,userService); 
	  
		//UsuarioDoctor(req);
	  //userService.getIdDoc(UsuarioDoctor(req));
	  //System.out.println(userService.getIdDoc(UsuarioDoctor(req)).toArray());
	  //Object[] idD=userService.getIdDoc(UsuarioDoctor(req)).toArray();
	  ///Object[] idD2=idD[0];
	  //System.out.println("el ides"+idD[0]);
	  //Integer idDoct=idD[0].hashCode();
	  //System.out.println(idp);
	 /// System.out.println("hola"+test(request));
	
	 // System.out.println(idD2[0]);
	  //String IdDoctor23=idD2[0].toString();
	  //System.out.println("EL ID D"+IdDoctor23); 
	 /// System.out.println(IdDoctor2);
	 	model.addAttribute("id_med_user",UsuarioDoctor(request,userService));
		model.addAttribute("userForm", new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
	
		
		//Validacion sobre si esta activo
		User u = userService.getUserById(Long.valueOf(UsuarioDoctor(request, userService)));
		
		if(u.getUser_med_status() == 1)
		{
			return "index";
		}
		else
		{
			User usuario = new User();
			model.put("usuario", usuario);
			return "redirect:/login?status=no_confirmado";
		}

		
	}//End	
	
	public String hola() {
		
		String hola="hola mundo";
		return hola;
		
	}
	
	
	public Integer UsuarioDoctor(HttpServletRequest request,UserService userService) {
		String usuarioDoctor= request.getUserPrincipal().getName();
		///System.out.println("el id es"+IdDoctor2);
		////return IdDoctor2;
		
		////-------------------
		 
		  userService.getIdDoc(usuarioDoctor);
		  Object[] idD=userService.getIdDoc(usuarioDoctor).toArray();
		  Integer idDoct=idD[0].hashCode();
		 //// System.out.println("metodo sin return"+idDoct);
	return idDoct;
		
	}
	
	
	@PostMapping("/index")
	public String postUserForm( @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
		}else {
			try {//Aca tendras error porque este metodo no existe, pero lo crearemos en la siguiente seccion.
				userService.createUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
				
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				
			}
			
		}//End_else
		model.addAttribute("userList", userService.getAllUsers());
		System.out.println(model.addAttribute("userList", userService.getAllUsers()));
		
		return "index";
	}//End
	
	public void imprimir(String email) {
		System.out.println(email+"holamundo");
		
	}
	
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name ="id")Long id)throws Exception{
		User userToEdit = userService.getUserById(id);

		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		model.addAttribute("passwordForm",new ChangePasswordForm(id));

		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
			model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new User());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("editMode","true");
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		return "user-form/user-view";
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/login";
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
			if( errors.hasErrors()) {
				String result = errors.getAllErrors()
                        .stream().map(x -> x.getDefaultMessage())
                        .collect(Collectors.joining(""));

				throw new Exception(result);
			}
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("Success");
	}
}

