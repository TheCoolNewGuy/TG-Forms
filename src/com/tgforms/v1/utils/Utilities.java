package com.tgforms.v1.utils;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

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
}
