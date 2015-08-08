package com.tgforms.v1.form1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;


@SuppressWarnings("serial")
public class StoreData implements Serializable{
	
	public static String FormDate;
	public static String Location;
	public static String Permit_manager;
	public static String Equipment; 
	public static String workComplete;
	
	public static ArrayList<String> lotoSteps;
	public static ArrayList<EditText> lotoStepsEditText;
	
	public static HashMap<String, ClearanceData> clearanceDataList;
	public static HashMap<String, ImageView> signList;
	
	public static Bitmap workCompleteSignBitmap;
	public static Bitmap headerBitmap;
	public static boolean workCompleteSignDone=false;
	
	public static EditText editDate,editLocation,editPermitManager,editEquipment, editWorkComplete;
	public static ArrayList<EditText> nameList,openDate,closeDate;
	public static HashMap<String, EditText > dateList;
	
	public static void initiateData()
	{
		System.out.println("initiating");
		
		FormDate="Date : ";
		Location ="Location : ";
		Permit_manager="Permit Manager : ";
		Equipment ="Equipment : ";
		workComplete="Work Complete(Print) : ";
		
		lotoSteps = new ArrayList<String>();
		lotoStepsEditText  = new ArrayList<EditText>();;
		
		
	}
	
	public static void ClearData(){
		
		System.out.println("clearing");
		
		FormDate=null;
		Location =null;
		Permit_manager=null;
		Equipment =null;
		workComplete=null;
		
		lotoSteps = null;
		lotoStepsEditText  = null;
		
		clearanceDataList= null;
		signList= null;
		
		workCompleteSignBitmap= null;
		headerBitmap= null;
		workCompleteSignDone= false;
		
		editDate= null;
		editLocation= null;
		editPermitManager= null;
		editEquipment= null;
		editWorkComplete= null;
		nameList= null;
		openDate= null;
		closeDate= null;
		dateList= null;
	}
	
	public static StringBuilder getData(){
		
		StringBuilder pdfBody = new StringBuilder();
		
		pdfBody.append(FormDate).append(System.getProperty("line.separator"));
		pdfBody.append(Location).append(System.getProperty("line.separator"));
		pdfBody.append(Permit_manager).append(System.getProperty("line.separator"));
		pdfBody.append(Equipment).append(System.getProperty("line.separator"));
		
		for(String steps : lotoSteps)
		{
			pdfBody.append(steps).append(System.getProperty("line.separator"));
		}
		
		pdfBody.append("Clearance Holders").append(System.getProperty("line.separator"));
		
		for(int i=0; i<10;i++){
            
		   ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
		   
		   String name  = data.getName();
		   pdfBody.append(name+" ").append(data.getDateOpen()+" "+data.getDateClose()).append(System.getProperty("line.separator"));
		   
        }
		
		return pdfBody;
	}
}
