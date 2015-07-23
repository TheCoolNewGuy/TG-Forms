package com.tgforms.v1.utils;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

import com.tgforms.v1.form1.ClearanceData;
import com.tgforms.v1.form1.StoreData;


public class Utilities {

	private static ProgressDialog progress;
	
	public static String encodeTobase64(Bitmap image)
	{
		if(image!=null){
			
			Bitmap immagex=image;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		    byte[] b = baos.toByteArray();
		    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

		    return imageEncoded;
		    
		}
		return null;
	    
	}
	public static Bitmap decodeBase64(String input) 
	{
	    byte[] decodedByte = Base64.decode(input, 0);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}
	
	public static String getDate()
    {
    	final Calendar c = Calendar.getInstance();
        int year  = c.get(Calendar.YEAR);
        int  month = c.get(Calendar.MONTH);
        int   day = c.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int sec = c.get(Calendar.SECOND);
        int ampm = c.get(Calendar.AM_PM);
        
        String minString =minute>9?String.valueOf(minute):"0"+minute;
        String ShourOfDay =hourOfDay>9?String.valueOf(hourOfDay):"0"+hourOfDay;
        String Ssec =sec>9?String.valueOf(sec):"0"+sec;
        
        String am_pm;
        
        if(ampm==0){
        	am_pm="AM";
        }else{
        	am_pm="PM";
        }
       StringBuilder date= new StringBuilder().append(month+1).append("/").append(day).append("/").append(year).append("  "+ShourOfDay+":"+minString+":"+Ssec+" "+am_pm);
        
       return date.toString();
    }
	
	public static boolean isFormOneComplete(){
		return StoreData.Location.length()>11 && StoreData.Equipment.length()>11 && StoreData.Permit_manager.length()>17 && StoreData.workComplete.length()>23 && isClearenceDataComplete();
	}
	
	public static void showToast(Context con,String message){
		
		Toast.makeText(con, message,
				   Toast.LENGTH_LONG).show();
		
	}
	
	public static void showLoadingDialog(Context con,String message) {

	    if (progress == null) {
	        progress = new ProgressDialog(con);
	        progress.setTitle("Please wait");
	        progress.setMessage(message);
	        progress.setCancelable(true);
	        progress.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					
				}
			});
	    }
	    progress.show();
	}

	public static void dismissLoadingDialog() {

		if (progress != null) {
			if(progress.isShowing()){
				progress.dismiss();
				progress.cancel();
			}
	        progress=null;
	    }
	}
	
	private static boolean isClearenceDataComplete(){
		
		for(int i=0; i<10;i++){
            
			ClearanceData data = StoreData.clearanceDataList.get(String.valueOf(i+1));
			
			if(data.getName().length()>4 ){
				System.out.println(data.getName());
				System.out.println(data.getDateOpen());
				System.out.println(data.getDateClose());
				if(data.getDateOpen().length()>4 && data.getDateClose().length()>4){
					
				}else{
					return false;
				}
			}
		   
		}

		return true;
	}
	
	public interface MyCallback {
	    void onProgressBarCancel();
	}

	
}
