package com.tgforms.v1.form1;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tgforms.v1.utils.Constants;
import com.tgforms.v1.utils.Utilities;

public class PdfMaker {
	
	static FragmentOneData f1Data;
	static FragmentTwoData f2Data;

	private static String FILE;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
			Font.BOLD);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
			Font.BOLD);
	private static Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
			Font.NORMAL);
	private static Font cellFont = new Font(Font.FontFamily.TIMES_ROMAN, 7,
			Font.NORMAL);

	public PdfMaker(Context con,String fileName, FragmentOneData f1Data, FragmentTwoData f2Data) {
		
		this.f1Data = f1Data;
		this.f2Data = f2Data;
		
		String root = Environment.getExternalStorageDirectory().toString();
		File myDir = new File(root + "/TG Forms");
		myDir.mkdir();
		File mySubDir = new File(myDir,"LOTO Form");
		mySubDir.mkdir();
		File myAnotherSubDir = new File(mySubDir,"Saved");
		myAnotherSubDir.mkdir();
		
		FILE = root + Constants.rootPath+"/LOTO Form/Saved/"+fileName+".pdf";
		System.out.println(FILE);
		
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addContent(document);
			document.close();
			Utilities.showToast(con, "Form data saved at SD CARD"+Constants.loto_path_saved+" "+fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static void addContent(Document document) throws DocumentException {

		Anchor anchor = new Anchor("TG Field Forms", catFont);
		anchor.setName("TG Field Forms ");

		
		// Second parameter is the number of the chapter
		Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		Paragraph subPara = new Paragraph("LOTO", subFont);
		Section subCatPart = catPart.addSection(subPara);
		subCatPart.setNumberStyle(subCatPart.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		//addHeaderImage(subCatPart);
		
		Paragraph titlePara = new Paragraph("", subFont);
		addEmptyLine(titlePara, 1);
		subCatPart.add(titlePara);
		
		Paragraph firstP = new Paragraph("Date : "+ f1Data.getFormDate()  + "  "
				+ "Site : "+f1Data.getLocation());
		firstP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(firstP);

		Paragraph secondP = new Paragraph("Permit Manager : "+f1Data.getPermit_manager() + "  "
				+ "Asset : "+f1Data.getEquipment());
		secondP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(secondP);

		Paragraph thirdP = new Paragraph("Work Detail : "+f1Data.getWorkDetail() + "  "
				+ "LOTO type : "+f1Data.getLotoType());
		thirdP.setAlignment(Element.ALIGN_CENTER);
		subCatPart.add(thirdP);
		
		createList(subCatPart);
		Paragraph paragraph = new Paragraph();
		addEmptyLine(paragraph, 1);
		subCatPart.add(paragraph);

		// add a table
		createTable(subCatPart,document);
		
		Paragraph empP = new Paragraph("");
		addEmptyLine(empP, 1);
		subCatPart.add(empP);
		
		Paragraph workP = new Paragraph("Work Complete(Print) : "+f2Data.getWorkComplete());
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
               
				TransferableClearanceData data = f2Data.getClearanceDataList().get(String.valueOf(i+1));
			   
			    table.addCell(new PdfPCell(new Phrase(String.valueOf(i+1)+"."+data.getName(),cellFont)));
			    
			    Bitmap bitmap = Utilities.decodeBase64(data.getBase64SignCleartoOpen());
			    if(bitmap!=null){
			    
			    	Image img = getImageInstanceFromBitmap(bitmap);
			    	
				    if(img!=null){
				    	table.addCell(img);
				    }
				    else
				    	table.addCell(new PdfPCell(new Phrase("n/a",cellFont)));
			    }else{
			    	table.addCell(new PdfPCell(new Phrase("n/a",cellFont)));
			    }
			    
			    table.addCell(new PdfPCell(new Phrase(data.getDateOpen(),cellFont)));
			    
			    bitmap =  Utilities.decodeBase64(data.getBase64SignCleartoClose());
			    if(bitmap!=null){
			    	Image img = getImageInstanceFromBitmap(bitmap);
			    	if(img!=null){
				    	table.addCell(img);
				    }
				    else
				    	table.addCell(new PdfPCell(new Phrase("n/a",cellFont)));
			    }else{
			    	table.addCell(new PdfPCell(new Phrase("n/a",cellFont)));
			    }
			    System.out.println(data.getDateClose());
				table.addCell(new PdfPCell(new Phrase(data.getDateClose(),cellFont)));
			    table.completeRow();
	   }

		subCatPart.add(table);

	}

	private static void createList(Section subCatPart) {

		Paragraph title = new Paragraph("LOTO Steps",subFont);
		addEmptyLine(title, 1);
		subCatPart.add(title);

		PdfPTable table = new PdfPTable(1);
		
		for (int i = 0; i < 10; i++) {
			String step = String.valueOf(i+1)+"."+f1Data.getLotoSteps().get(i);
			table.addCell(new Paragraph(step,smallFont));
		}
		subCatPart.add(table);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

	private static void addWCompleteSign(Section document) throws DocumentException {

		try {
			// get input stream
			Bitmap bmp = Utilities.decodeBase64(f2Data.getWorkCompleteSignBase64());
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
			Image image;
			try {
				image = Image.getInstance(stream.toByteArray());
				image.scaleAbsolute(100f, 100f);
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
