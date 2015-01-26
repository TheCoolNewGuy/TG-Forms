package com.tgforms.v1.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tgforms.v1.form1.StoreData;
import com.tgforms.v1.pojo.ClearanceData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;

public class PdfMaker {

	private static String FILE;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
			Font.BOLD);

	public PdfMaker(Context con) {
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/FormPdfs");
		FILE = root + "/FormPdfs/form1.pdf";
		myDir.mkdir();

		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addContent(document);
			document.close();
			Utilities.showToast(con, "Form data saved at SD CARD/FormPdfs");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static void addContent(Document document) throws DocumentException {

		Anchor anchor = new Anchor("LOTO", catFont);
		anchor.setName("LOTO");

		
		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("TG LOGO PERMITS", subFont);
		Section subCatPart = catPart.addSection(subPara);
		addHeaderImage(subCatPart);
		
		Paragraph titlePara = new Paragraph("", subFont);
		addEmptyLine(titlePara, 3);
		subCatPart.add(titlePara);
		
		Paragraph firstP = new Paragraph(StoreData.FormDate + "  "
				+ StoreData.Location);
		firstP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(firstP);

		Paragraph secondP = new Paragraph(StoreData.Permit_manager + "  "
				+ StoreData.Equipment);
		secondP.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(secondP, 5);
		subCatPart.add(secondP);

		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart,document);
		
		Paragraph empP = new Paragraph("");
		addEmptyLine(empP, 3);
		subCatPart.add(empP);
		
		Paragraph workP = new Paragraph(StoreData.workComplete);
		workP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(workP);

		Paragraph workS = new Paragraph("Work Complete (Sign) : ");
		workS.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(workS);
		
		addWCompleteSign(subCatPart);
		
		// now add all this to the document
		document.add(catPart);


	}

	private static void createTable(Section subCatPart,Document document)
			throws BadElementException {
		
		document.newPage();
		
		Paragraph title = new Paragraph("Clearance Holders",subFont);
		addEmptyLine(title, 2);
		subCatPart.add(title);
		
		PdfPTable table = new PdfPTable(5);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Sign Clear to Open"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Date"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Sign Clear to Close"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Date"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		
		for(int i=0; i<10;i++){
               
				ClearanceData data = StoreData.clearanceDataList.get(String.valueOf(i+1));
			   
			    table.addCell(data.getName());
			    
			    Bitmap bitmap = data.getBitmapSignCleartoOpen();
			    if(bitmap!=null){
			    
			    	Image img = getImageInstanceFromBitmap(data.getBitmapSignCleartoOpen());
				    if(img!=null)
				    	table.addCell(img);
				    else
				    	table.addCell("n/a");
			    }else{
			    	table.addCell("n/a");
			    }
			    
			    table.addCell(data.getDateOpen());
			    
			    bitmap = data.getBitmapSignCleartoClose();
			    if(bitmap!=null){
			    	Image img = getImageInstanceFromBitmap(data.getBitmapSignCleartoClose());
				    if(img!=null)
				    	table.addCell(img);
				    else
				    	table.addCell("n/a");
			    }else{
			    	table.addCell("n/a");
			    }
			    
				table.addCell(data.getDateClose());
			    table.completeRow();
	   }

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {

		Paragraph title = new Paragraph("Loto Steps",subFont);
		addEmptyLine(title, 2);
		subCatPart.add(title);

		for (int i = 0; i < 10; i++) {
			subCatPart.add(new Paragraph(StoreData.lotoSteps.get(i)));
		}

	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void addHeaderImage(Section document) throws DocumentException {

		try {
			// get input stream
			Bitmap bmp = StoreData.headerBitmap;
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			Image image;
			try {
				image = Image.getInstance(stream.toByteArray());
				image.scaleAbsolute(500f, 200f);
				document.add(image);
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException ex) {
			return;
		}
	}
	
	private static void addWCompleteSign(Section document) throws DocumentException {

		try {
			// get input stream
			Bitmap bmp = StoreData.workCompleteSignBitmap;
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			Image image;
			try {
				image = Image.getInstance(stream.toByteArray());
				image.scaleAbsolute(200f, 200f);
				image.setAlignment(Element.ALIGN_CENTER);
				document.add(image);
			} catch (BadElementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException ex) {
			return;
		}
	}
	
	
	private static Image getImageInstanceFromBitmap(Bitmap bitmap) throws BadElementException{
		
		try {
			// get input stream
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
			Image image;
			image = Image.getInstance(stream.toByteArray());
			return image;
			
		} catch (IOException ex) {
			return null;
		}
	}
}
