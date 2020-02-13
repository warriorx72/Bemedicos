package com.bemedicos.springboot.app.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.tomcat.jni.FileInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfIndirectObject;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class TableTemplate 
{ 
	public static final String IMG = "c:/temp/logo.png";
	
    public static void main(String[] args) throws IOException, DocumentException 
    {
    	//File file = new File(DEST);
    	//file.getParentFile().mkdirs();
    	//new TableTemplate().createPdf(DEST);
    }
	
	public void createPdf(String dest, JSONArray jsonArray, int monto, Long id_sol) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        Font titleFont = FontFactory.getFont("Times Roman", 15, BaseColor.BLACK);
        Font subtitleFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK);
        Paragraph docTitle = new Paragraph("Solicitud de Estudios", titleFont);
        docTitle.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph docSubtitle = new Paragraph("#" + (100000000 + id_sol), subtitleFont);
        docTitle.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(docTitle);
        document.add(docSubtitle);

        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        PdfPTable table = new PdfPTable(10); // the arg is the number of columns
        table.setWidthPercentage(90);
        //Fuentes
        Font HeadFont = FontFactory.getFont("Times Roman", 14, BaseColor.BLACK);
        Font nomFont = FontFactory.getFont("Times Roman", 9, BaseColor.BLACK);
        Font preFont = FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK);
        Font infoFont = FontFactory.getFont("Times Roman", 8, BaseColor.BLACK);
        float altura = 30;
	    
        //Logo
        Image img = Image.getInstance(IMG);
        img.setAbsolutePosition(400, 770);
        img.scaleToFit(150, 150);
        document.add(img);
        
        //Cabezeros
        BaseColor color = new BaseColor(152, 213, 213, 70);
	    Paragraph nomHead = new Paragraph("Nombre", HeadFont);
        PdfPCell nomHeadCell = new PdfPCell(nomHead);
        nomHeadCell.setColspan(3);
        nomHeadCell.setBorder(PdfPCell.NO_BORDER);
        nomHeadCell.setBackgroundColor(color);
        nomHeadCell.setFixedHeight(altura);
        nomHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(nomHeadCell);
        Paragraph preHead = new Paragraph("Precio", HeadFont);
        PdfPCell preHeadCell = new PdfPCell(preHead);
        preHeadCell.setColspan(1);
        preHeadCell.setBorder(PdfPCell.NO_BORDER);
        preHeadCell.setBackgroundColor(color);
        preHeadCell.setFixedHeight(altura);
        table.addCell(preHeadCell);
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
        infoHeadCell.setColspan(4);
        infoHeadCell.setBorder(PdfPCell.NO_BORDER);
        infoHeadCell.setBackgroundColor(color);
        infoHeadCell.setFixedHeight(altura);
        infoHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(infoHeadCell);
        

		for (int i = 0; i < jsonArray.length(); i++)
		{
			BaseColor col;
			
			if(i % 2 == 0)
			{
				col = new BaseColor(174, 249, 249);
			}
			else
			{
				col = new BaseColor(255, 255, 255);
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
	        Paragraph pre = new Paragraph("$" + object.getString("precio"), preFont);
	        PdfPCell preCell = new PdfPCell(pre);
	        preCell.setBorder(PdfPCell.NO_BORDER);
	        preCell.setBackgroundColor(col);
	        preCell.setFixedHeight(altura);
	        preCell.setColspan(1);
	        preCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table.addCell(preCell);
	        
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
	        infoCell.setColspan(4);
	        table.addCell(infoCell);
		}
        
        //Precio
        Paragraph tot = new Paragraph("Total: ");
        PdfPCell totCell = new PdfPCell(tot);
        totCell.setBorder(PdfPCell.NO_BORDER);
        totCell.setBackgroundColor(color);
        totCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totCell.setFixedHeight(altura);
        totCell.setColspan(5);
        table.addCell(totCell);
        
        //Indicaciones
        
        Paragraph totCan = new Paragraph("$ " + monto);
        PdfPCell totCanCell = new PdfPCell(totCan);
        totCanCell.setBorder(PdfPCell.NO_BORDER);
        totCanCell.setBackgroundColor(color);
        totCanCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totCanCell.setFixedHeight(altura);
        totCanCell.setColspan(5);
        table.addCell(totCanCell);
        
		document.add(table);
        document.close();
        
            
    }
	

}
