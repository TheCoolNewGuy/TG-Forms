package com.tgforms.v1.utils;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import com.tgforms.v1.form1.StoreData;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.Toast;

public class Utilities {

	public static String encodeTobase64(Bitmap image)
	{
	    Bitmap immagex=image;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();  
	    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

	    return imageEncoded;
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
        
       StringBuilder date= new StringBuilder().append(month+1).append("/").append(day).append("/").append(year).append(" ");
        
       return date.toString();
    }
	
	public static boolean isFormOneComplete(){
		return StoreData.Location.length()>11 && StoreData.Equipment.length()>11 && StoreData.Permit_manager.length()>17 && StoreData.workComplete.length()>23;
	}
	
	public static void showToast(Context con,String message){
		
		Toast.makeText(con, message,
				   Toast.LENGTH_LONG).show();
		
	}
}
