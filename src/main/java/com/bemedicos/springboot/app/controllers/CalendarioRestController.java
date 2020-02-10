package com.bemedicos.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bemedicos.springboot.app.models.entity.Calendario;
import com.bemedicos.springboot.app.service.CalendarioService;

@RestController
public class CalendarioRestController {
	
	@Autowired
	private CalendarioService calendarioService;
	
	@RequestMapping(value="/allevents", method=RequestMethod.GET)
	public List<Calendario> allEvents() {
		return calendarioService.findAll();
	}
	
	@RequestMapping(value="/event", method=RequestMethod.POST)
	public Calendario addEvent(@RequestBody Calendario calendario) {
		calendarioService.save(calendario);
		return calendario; 
	}
	@RequestMapping(value="/event", method=RequestMethod.PATCH)
	public Calendario updateEvent(@RequestBody Calendario calendario) {
		System.out.println("orale perro");
		calendarioService.save(calendario);
		return calendario;
	}
	@RequestMapping(value="/event", method=RequestMethod.DELETE)
	public void removeEvent(@RequestParam(name = "id") Long id) {
		calendarioService.delete(id);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	class BadDateFormatException extends RuntimeException {
	  private static final long serialVersionUID = 1L;

		public BadDateFormatException(String dateString) {
	        super(dateString);
	    }
	}

}
