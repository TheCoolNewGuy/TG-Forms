package com.tgforms.v1.form1;

import java.util.ArrayList;
import java.util.Calendar;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.tgforms.v1.R;

public class FragementOne extends android.support.v4.app.Fragment {
	
	private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1;
    
    
    View rootView;
    EditText editDate,editLocation,editPermitManager,editEquipment;
    ArrayList<EditText> lotoSteps;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.pageone, container, false);
        
        ImageView headerView = (ImageView) rootView.findViewById(R.id.imageView1);
        StoreData.headerBitmap = ((BitmapDrawable) headerView.getDrawable()).getBitmap();
        
        lotoSteps = new ArrayList<EditText>();
        
        editDate = (EditText) rootView.findViewById(R.id.editformdate);
        editDate.setInputType(InputType.TYPE_NULL);
        editLocation = (EditText) rootView.findViewById(R.id.editLocation);
        editPermitManager = (EditText) rootView.findViewById(R.id.editPermitManager);
        editEquipment = (EditText) rootView.findViewById(R.id.editEquipment);
        
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep1));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep2));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep3));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep4));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep5));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep6));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep7));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep8));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep9));
        lotoSteps.add((EditText) rootView.findViewById(R.id.lotoStep10));
        
        for(int i=0;i<10;i++){
        	StoreData.lotoSteps.add(i, String.valueOf(i+1)+". ");
		}
		
        setDate();
        addWatchers();
        
        
        return rootView;
    }
        
    private void setDate()
    {
    	//DATE AND TIME PICKER 
        
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        
        editDate.setText(new StringBuilder().append(month+1).append("/").append(day).append("/").append(year).append(" "));
        StoreData.FormDate="Date : "+editDate.getText().toString();
        
    }
    
     private void addWatchers()
     {
    	 
    	 editLocation.addTextChangedListener(new TextWatcher() {
    		 
  			@Override
  			public void afterTextChanged(Editable s) {
  				// TODO Auto-generated method stub
  				StoreData.Location="Location : "+editLocation.getText().toString();
  			}

 			@Override
 			public void beforeTextChanged(CharSequence s, int start, int count,
 					int after) {
 				// TODO Auto-generated method stub
 				
 			}

 			@Override
 			public void onTextChanged(CharSequence s, int start, int before,
 					int count) {
 				// TODO Auto-generated method stub
 				
 			}
  		});
    	 
    	 editEquipment.addTextChangedListener(new TextWatcher() {
    		 
   			@Override
   			public void afterTextChanged(Editable s) {
   				// TODO Auto-generated method stub
   				StoreData.Equipment="Equipment : "+editEquipment.getText().toString();
   			}

  			@Override
  			public void beforeTextChanged(CharSequence s, int start, int count,
  					int after) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void onTextChanged(CharSequence s, int start, int before,
  					int count) {
  				// TODO Auto-generated method stub
  				
  			}
   		});
    	 
    	 editPermitManager.addTextChangedListener(new TextWatcher() {
    		 
   			@Override
   			public void afterTextChanged(Editable s) {
   				// TODO Auto-generated method stub
   				StoreData.Permit_manager="Permit Manager : "+editPermitManager.getText().toString();
   			}

  			@Override
  			public void beforeTextChanged(CharSequence s, int start, int count,
  					int after) {
  				// TODO Auto-generated method stub
  				
  			}

  			@Override
  			public void onTextChanged(CharSequence s, int start, int before,
  					int count) {
  				// TODO Auto-generated method stub
  				
  			}
   		});
    	 
    	 
    	 for(int i=0;i<lotoSteps.size();i++)
    	 {
    		 addTextChangedListener(lotoSteps.get(i), i);
    	 }
    }
     
    private void addTextChangedListener(final EditText editText,final int index)
    {
    	editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				StoreData.lotoSteps.set(index, Integer.toString(index+1)+". "+editText.getText().toString());
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
    }
    
}
