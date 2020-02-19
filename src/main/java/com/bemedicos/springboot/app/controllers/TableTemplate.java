package com.bemedicos.springboot.app.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.Color;

import com.bemedicos.springboot.app.service.EvolucionService;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.VerticalPositionMark;

public class TableTemplate 
{ 
	public static final String IMG = "c:/temp/logo.png";
	
    public static void main(String[] args) throws IOException, DocumentException 
    {
    	//File file = new File(DEST);
    	//file.getParentFile().mkdirs();
    	//new TableTemplate().createPdf(DEST);
    }
	
	public void createPdf(String dest, JSONArray jsonArray, int monto, Long id_sol, String nompac, String nommed, String cedula, String tel_cel, String genero, int edad, String pronos) throws IOException, DocumentException {
		
		SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.forLanguageTag("es-ES"));
		String fecha = df.format(new Date());
		
		System.out.println(fecha);
		
		Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        
        //Fuentes
        Font Fuente = FontFactory.getFont("Times Roman", 10, Color.BLACK);
        Font FuenteN = FontFactory.getFont("Times Roman", 10, Font.BOLD, Color.BLACK);
        Font FuenteBold = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, Font.BOLD, Color.BLACK);
        Font FuenteTexto = FontFactory.getFont("Times Roman", 11, Color.BLACK);
        Font FuenteBoldTexto = FontFactory.getFont("Times Roman", 12, Font.BOLD, Color.BLACK);
        Font medicoDatFont = FontFactory.getFont("Times Roman", 10, Color.BLACK);
        Font medicoDatBold = FontFactory.getFont("Times Roman", 10, Font.BOLD, Color.BLACK);
        
        //Se pinta la solicitud con su numero
        Chunk tituloSolicitudChunk = new Chunk("Solicitud de Estudios: ", Fuente);
        Chunk numeroSolicitudChunk = new Chunk("#" + (100000000 + id_sol), FuenteBold);
        Phrase frase = new Phrase();
        frase.add(tituloSolicitudChunk);
        frase.add(numeroSolicitudChunk);
        Paragraph tituloSolicitud = new Paragraph();
        tituloSolicitud.setAlignment(Paragraph.ALIGN_RIGHT);
        tituloSolicitud.add(frase);
        
        //Se pinta la fecha
        Chunk fechaPrimer = new Chunk(fecha.substring(0, 1).toUpperCase() + fecha.substring(1, 4), Fuente);
        Chunk fechaDia = new Chunk(" " + fecha.substring(5, 7), FuenteN);
        Chunk fechaSecond = new Chunk(" de " + fecha.substring(8, 9).toUpperCase() + fecha.substring(9),Fuente);
        Phrase fechas = new Phrase();
        fechas.add(fechaPrimer);
        fechas.add(fechaDia);
        fechas.add(fechaSecond);
        Paragraph fechaParrafo = new Paragraph();
        fechaParrafo.setAlignment(Paragraph.ALIGN_RIGHT);
        fechaParrafo.add(fechas);
        
        
        //Tabla de datos Medico-Paciente 
        PdfPTable tablaMedPac = new PdfPTable(10); // the arg is the number of columns
        tablaMedPac.setWidthPercentage(90);
        float alturaMedPac = 15;
        Color colorMedPac = new Color(255, 255, 255);
        
        
        //Contenido de la Tabla
        //Se pinta el nombre del Medico
        Chunk MedNomChunk = new Chunk("Médico: " + nommed, FuenteTexto);
        Phrase MedNomPhrase = new Phrase(MedNomChunk);
        Paragraph MedNomParagraph = new Paragraph(MedNomPhrase);
        PdfPCell MedNom = new PdfPCell(MedNomParagraph);
        MedNom.setBorder(PdfPCell.NO_BORDER);
        MedNom.setBackgroundColor(colorMedPac);
        MedNom.setHorizontalAlignment(Element.ALIGN_LEFT);
        MedNom.setFixedHeight(alturaMedPac);
        MedNom.setColspan(5);
        tablaMedPac.addCell(MedNom);
        
        //Se pinta el nombre del Paciente
        Chunk PacNomChunk = new Chunk("Paciente: " + nompac, FuenteTexto);
        Phrase PacNomPhrase = new Phrase(PacNomChunk);
        Paragraph PacNomParagraph = new Paragraph(PacNomPhrase);
        PdfPCell PacNom = new PdfPCell(PacNomParagraph);
        PacNom.setBorder(PdfPCell.NO_BORDER);
        PacNom.setBackgroundColor(colorMedPac);
        PacNom.setHorizontalAlignment(Element.ALIGN_LEFT);
        PacNom.setFixedHeight(alturaMedPac);
        PacNom.setColspan(5);
        tablaMedPac.addCell(PacNom);
        
        //Se pinta la cedula
        Chunk MedCedChunk = new Chunk("Cédula: " + cedula, FuenteTexto);
        Phrase MedCedPhrase = new Phrase(MedCedChunk);
        Paragraph MedCedParagraph = new Paragraph(MedCedPhrase);
        PdfPCell MedCed = new PdfPCell(MedCedParagraph);
        MedCed.setBorder(PdfPCell.NO_BORDER);
        MedCed.setBackgroundColor(colorMedPac);
        MedCed.setHorizontalAlignment(Element.ALIGN_LEFT);
        MedCed.setFixedHeight(alturaMedPac);
        MedCed.setColspan(5);
        tablaMedPac.addCell(MedCed);
        
        //Se pinta el Genero y la Edad
        Chunk GenPacChunk = new Chunk("Género: " + genero + "  Edad: " + edad, FuenteTexto);
        Phrase GenPacPhrase = new Phrase(GenPacChunk);
        Paragraph GenPacParagraph = new Paragraph(GenPacPhrase);
        PdfPCell GenPac = new PdfPCell(GenPacParagraph);
        GenPac.setBorder(PdfPCell.NO_BORDER);
        GenPac.setBackgroundColor(colorMedPac);
        GenPac.setHorizontalAlignment(Element.ALIGN_LEFT);
        GenPac.setFixedHeight(alturaMedPac);
        GenPac.setColspan(5);
        tablaMedPac.addCell(GenPac);
        
        //Se pinta el telefono del Medico
        Chunk MedCelChunk = new Chunk("Teléfono: " + tel_cel, FuenteTexto);
        Phrase MedCelPhrase = new Phrase(MedCelChunk);
        Paragraph MedCelParagraph = new Paragraph(MedCelPhrase);
        PdfPCell MedCel = new PdfPCell(MedCelParagraph);
        MedCel.setBorder(PdfPCell.NO_BORDER);
        MedCel.setBackgroundColor(colorMedPac);
        MedCel.setHorizontalAlignment(Element.ALIGN_LEFT);
        MedCel.setFixedHeight(alturaMedPac);
        MedCel.setColspan(5);
        tablaMedPac.addCell(MedCel);
        
        //Se pinta el diagnostico
        Chunk DiaPacChunk = new Chunk("Diagnóstico: " + pronos, FuenteTexto);
        Phrase DiaPacPhrase = new Phrase(DiaPacChunk);
        Paragraph DiaPacParagraph = new Paragraph(DiaPacPhrase);
        PdfPCell DiaPac = new PdfPCell(DiaPacParagraph);
        DiaPac.setBorder(PdfPCell.NO_BORDER);
        DiaPac.setBackgroundColor(colorMedPac);
        DiaPac.setHorizontalAlignment(Element.ALIGN_LEFT);
        DiaPac.setFixedHeight(alturaMedPac);
        DiaPac.setColspan(5);
        tablaMedPac.addCell(DiaPac);

        
        
        //Se añaden los parrafos
        document.add(tituloSolicitud);
        document.add(fechaParrafo);
        document.add(Chunk.NEWLINE);
        document.add(tablaMedPac);
        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(10); // the arg is the number of columns
        table.setWidthPercentage(90);
        
        //Fuentes
        Font HeadFont = FontFactory.getFont("Times Roman", 12, Color.BLACK);
        Font nomFont = FontFactory.getFont("Times Roman", 9, Color.BLACK);
        Font preFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, Color.BLACK);
        Font infoFont = FontFactory.getFont("Times Roman", 8, Color.BLACK);
        float altura = 30;
	    
        //Logo
        Image img = Image.getInstance(IMG);
        img.setAbsolutePosition(40, 775);
        img.scaleToFit(150, 150);
        document.add(img);
        
        //Cabezeros
        Color color = new Color(152, 213, 213, 70);
	    Paragraph nomHead = new Paragraph("Nombre", HeadFont);
        PdfPCell nomHeadCell = new PdfPCell(nomHead);
        nomHeadCell.setColspan(3);
        nomHeadCell.setBorder(PdfPCell.NO_BORDER);
        nomHeadCell.setBackgroundColor(color);
        nomHeadCell.setFixedHeight(altura);
        nomHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(nomHeadCell);
        Paragraph canHead = new Paragraph("Cantidad", HeadFont);
        PdfPCell canHeadCell = new PdfPCell(canHead);
        canHeadCell.setColspan(2);
        canHeadCell.setBorder(PdfPCell.NO_BORDER);
        canHeadCell.setBackgroundColor(color);
        canHeadCell.setFixedHeight(altura);
        canHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(canHeadCell);
        Paragraph infoHead = new Paragraph("Indicaciones", HeadFont);
        PdfPCell infoHeadCell = new PdfPCell(infoHead);
        infoHeadCell.setColspan(5);
        infoHeadCell.setBorder(PdfPCell.NO_BORDER);
        infoHeadCell.setBackgroundColor(color);
        infoHeadCell.setFixedHeight(altura);
        infoHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(infoHeadCell);
        

		for (int i = 0; i < jsonArray.length(); i++)
		{
			Color col;
			
			if(i % 2 == 0)
			{
				col = new Color(174, 249, 249);
			}
			else
			{
				col = new Color(255, 255, 255);
			}
			JSONObject object = jsonArray.getJSONObject(i);
		    //Nombre
		    Paragraph nom = new Paragraph(object.getString("nom"), nomFont);
	        PdfPCell nomCell = new PdfPCell(nom);
	        nomCell.setBorder(PdfPCell.NO_BORDER);
	        nomCell.setBackgroundColor(col);
	        nomCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        nomCell.setFixedHeight(altura);
	        nomCell.setColspan(3);
	        table.addCell(nomCell);
	        
	        //Precio
	        Paragraph can = new Paragraph(String.valueOf(object.getInt("can")), preFont);
	        PdfPCell canCell = new PdfPCell(can);
	        canCell.setBorder(PdfPCell.NO_BORDER);
	        canCell.setBackgroundColor(col);
	        canCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        canCell.setFixedHeight(altura);
	        canCell.setColspan(2);
	        table.addCell(canCell);
	        
	        //Indicaciones
	        Paragraph info = new Paragraph(object.getString("info"), infoFont);
	        PdfPCell infoCell = new PdfPCell(info);
	        infoCell.setBorder(PdfPCell.NO_BORDER);
	        infoCell.setBackgroundColor(col);
	        infoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        infoCell.setFixedHeight(altura);
	        infoCell.setColspan(5);
	        table.addCell(infoCell);
		}
        
        //Se pinta la linea de la firma
        //Se pinta el nombre del Paciente
        Chunk linea = new Chunk("_______________________________________________", FuenteBoldTexto);
        Phrase firmas = new Phrase();
        firmas.add(linea);
        Paragraph firmaParrafo = new Paragraph();
        firmaParrafo.add(firmas);
        firmaParrafo.setAlignment(Element.ALIGN_CENTER);
        //
        Chunk lineaBajo = new Chunk("Nombre y firma del Médico", FuenteTexto);
        Phrase firmas2 = new Phrase();
        firmas2.add(lineaBajo);
        Paragraph firmaParrafo2 = new Paragraph();
        firmaParrafo2.add(firmas2);
        firmaParrafo2.setAlignment(Element.ALIGN_CENTER);
		
        
		document.add(table);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		document.add(firmaParrafo);
		document.add(firmaParrafo2);
        document.close();
        
            
    }
	

}
