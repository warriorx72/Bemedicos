package com.bemedicos.springboot.app.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bemedicos.springboot.app.service.CalendarioService;
import com.bemedicos.springboot.app.service.UserService;

@Controller
public class CalendarioController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CalendarioService calendarioService;

	
	@GetMapping(value="/agenda")
	public String agenda(HttpServletRequest request, Model model) {
		return "calendario";
	}

	
}
