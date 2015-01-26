package com.tgforms.v1.form1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.webkit.WebView.FindListener;

import com.tgforms.v1.pojo.ClearanceData;

@SuppressWarnings("serial")
public class StoreData implements Serializable{
	
	public static String FormDate="Date : ";
	public static String Location ="Location : ";
	public static String Permit_manager="Permit Manager : ";
	public static String Equipment ="Equipment : ";
	public static String workComplete="Work Complete(Print) : ";
	
	public static ArrayList<String> lotoSteps = new ArrayList<String>();
	public static HashMap<String, ClearanceData> clearanceDataList;
	
	public static Bitmap workCompleteSignBitmap;
	public static Bitmap headerBitmap;
	public static boolean workCompleteSignDone=false;
	
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
