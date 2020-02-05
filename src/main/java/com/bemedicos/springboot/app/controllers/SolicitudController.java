package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bemedicos.springboot.app.service.SolicitudDetalleService;
import com.bemedicos.springboot.app.service.SolicitudService;
import com.bemedicos.springboot.app.service.UserService;
import com.bemedicos.springboot.app.models.entity.Solicitud;
import com.bemedicos.springboot.app.models.entity.Solicitud_Detalle;

@Controller
public class SolicitudController 
{
	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	SolicitudDetalleService solicituddetalleService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/guardar_solcitud", method = RequestMethod.GET)
	public String guardar(HttpServletRequest request, Model model, Map<String, Object> m) 
	{
		Solicitud solicitud = new Solicitud();
		Solicitud_Detalle solicitud_detalle = new Solicitud_Detalle();
		
		//solicitud.setPaciente_id();
		//solicitud.setMedico_id();
		//solicitud.setEstatus_id();
		
		return "historial_clinico";
	}
	
	@PostMapping(value = "/prueba_solicitud")
	public String Prueba_Solicitud(@RequestParam(value = "data") String jsonStr, @RequestParam(value = "id") int id, @RequestParam(value = "monto") int monto, HttpServletRequest request) 
	{
		//Se transforma a JSON el objeto recibido
		JSONArray jsonArray = new JSONArray(jsonStr); 
		
		//Esto es para el usuario del doctor
		UserController us = new UserController();
		
		//Se guarda la solicitud
		Solicitud sol = new Solicitud();
		sol.setMedico_id(us.UsuarioDoctor(request, userService).longValue());
		sol.setPaciente_id(Long.valueOf(id));
		sol.setEstatus_id(Long.valueOf(0));
		sol.setMonto(Long.valueOf(monto));
		solicitudService.save(sol);
		
		
		for (int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject object = jsonArray.getJSONObject(i);
			Solicitud_Detalle sol_det = new Solicitud_Detalle();
		    sol_det.setSolicitud_id(sol.getSolicitud_id());
		    sol_det.setEstudio(Long.valueOf(object.getString("id_real")));
		    sol_det.setTipo((String) object.getString("tipo"));
		    sol_det.setCantidad_id(Long.valueOf(object.getInt("can")));
		    solicituddetalleService.save(sol_det);
		}
		return "historial_clinico";
	}
}
