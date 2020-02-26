package com.bemedicos.springboot.app.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bemedicos.springboot.app.service.EvolucionService;
import com.bemedicos.springboot.app.service.SolicitudDetalleService;
import com.bemedicos.springboot.app.service.SolicitudService;
import com.bemedicos.springboot.app.service.UserService;
import com.bemedicos.springboot.app.models.entity.Solicitud;
import com.bemedicos.springboot.app.models.entity.Solicitud_Detalle;
import com.lowagie.text.Anchor;
import com.lowagie.text.BadElementException;
import java.awt.Color;
import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Section;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.parser.PdfTextExtractor;
@Controller
public class SolicitudController 
{
	@Autowired
	SolicitudService solicitudService;
	
	@Autowired
	SolicitudDetalleService solicituddetalleService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private EvolucionService evolucionService;
	
	@Autowired
    public JavaMailSender emailSender;
	
    private static Font catFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
    
    private static Font redFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.RED);
    
    private static Font subFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
    
    private static Font smallBold = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
	
	
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
	public String Prueba_Solicitud(@RequestParam(value = "data") String jsonStr, @RequestParam(value = "correo") String correo, @RequestParam(value = "id") int id, @RequestParam(value = "monto") int monto, @RequestParam(value = "nompac") String nompac, @RequestParam(value = "edad") int edad, @RequestParam(value = "genero") String genero,  @RequestParam(value = "nommed") String nommed, @RequestParam(value = "cedula") String cedula, @RequestParam(value = "tel_cel") String tel_cel, HttpServletRequest request) throws MessagingException 
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
		sol.setSolicitud_idtext("SE-"+sol.getMedico_id()+"-"+sol.getPaciente_id()+"-"+(sol.getSolicitud_id()+10000));
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
		
		//Se bautiza el archivo jsjs
	    String DEST = "c:/temp/SOL_" + (100000000 + sol.getSolicitud_id()) + ".pdf";
		
		PDFMain(jsonArray, monto, DEST, sol.getSolicitud_id(), nommed, nompac, cedula, tel_cel, genero, edad);
        
        //
		//Se envia el correo
		MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
        String htmlMsg = "Buen día, como archivo adjunto se encuentra su solictud de estudios.";
        
        helper.setTo(correo);
        helper.setSubject("Solicitud de Estudios");
        helper.setText(htmlMsg);
        
        
        FileSystemResource archivo = new FileSystemResource(new File(DEST));
        helper.addAttachment("Solicitud: " + (100000000 + sol.getSolicitud_id()) + ".pdf", archivo);
        
        
        emailSender.send(message);
	        
		
		return "historial_clinico";
	}
	
	
	public String PDFMain(JSONArray jsonArray, int monto, String DEST, Long id_sol, String nommed, String nompac, String cedula, String tel_cel, String genero, int edad) 
	{	
		try 
		{
			TableTemplate tt = new TableTemplate();
			String pronos = evolucionService.GetLast();
			tt.createPdf(DEST, jsonArray, monto, id_sol, nompac, nommed, cedula, tel_cel, genero, edad, pronos);
        } 
		catch (Exception e) 
		{
            e.printStackTrace();
        }
		
		
		return "index";
	}
}
