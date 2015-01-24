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

	public PdfMaker() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static void addContent(Document document) throws DocumentException {

		Anchor anchor = new Anchor("TG LOGO PERMIT", catFont);
		anchor.setName("TG LOGO PERMIT");

		
		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("", subFont);
		Section subCatPart = catPart.addSection(subPara);

		addHeaderImage(subCatPart);
		
		Paragraph firstP = new Paragraph(StoreData.FormDate + "  "
				+ StoreData.Location);
		firstP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(firstP);

		Paragraph secondP = new Paragraph(StoreData.Permit_manager + "  "
				+ StoreData.Equipment);
		secondP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(secondP);

		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 5);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart);

		// now add all this to the document
		document.add(catPart);


	}

	private static void createTable(Section subCatPart)
			throws BadElementException {
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
		table.setHeaderRows(1);

		
		for(int i=0; i<10;i++){
            
			   ClearanceData data = StoreData.clearanceDataList.get(String.valueOf(i+1));
			   
			    table.addCell(data.getName());
			    Image img = getImageInstanceFromBitmap(data.getBitmapSignCleartoOpen());
			    if(img!=null)
			    	table.addCell("");
			    else
			    	table.addCell("");
				table.addCell(data.getDateOpen());
				img = getImageInstanceFromBitmap(data.getBitmapSignCleartoClose());
			    if(img!=null)
			    	table.addCell("");
			    else
			    	table.addCell("");
				table.addCell(data.getDateClose());
			   
			   
	   }

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {

		System.out.println("create list");
		Paragraph title = new Paragraph("Loto Steps");
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
