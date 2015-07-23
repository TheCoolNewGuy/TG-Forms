package com.tgforms.v1.form1;

import java.util.ArrayList;
import java.util.Calendar;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tgforms.v1.R;

public class FragementOne extends android.support.v4.app.Fragment {
	
	private int year;
    private int month;
    private int day;
    static final int DATE_PICKER_ID = 1;
    
    EditText editDate,editLocation,editPermitManager,editEquipment,editWorkDetail;
    ArrayList<EditText> lotoStepsEditText;
    
    private RadioGroup lotoTypRadioGroup;
    private RadioButton lotoTypeRadioBtn;
    
    View rootView;
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
    	rootView = inflater.inflate(R.layout.pageone, container, false);
        
        lotoStepsEditText  = new ArrayList<EditText>();;
        
        editDate = (EditText) rootView.findViewById(R.id.editformdate);
        editDate.setInputType(InputType.TYPE_NULL);
        
        editLocation = (EditText) rootView.findViewById(R.id.editLocation);
        editPermitManager = (EditText) rootView.findViewById(R.id.editPermitManager);
        editEquipment = (EditText) rootView.findViewById(R.id.editEquipment);
        editWorkDetail = (EditText) rootView.findViewById(R.id.editWorkDetail);
        
        lotoTypRadioGroup = (RadioGroup) rootView.findViewById(R.id.radioLotoType);
        
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep1));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep2));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep3));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep4));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep5));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep6));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep7));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep8));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep9));
        lotoStepsEditText.add((EditText) rootView.findViewById(R.id.lotoStep10));
        
        setDate();
        
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
        
        
    }
    
    public FragmentOneData getData(){
    	
    	String date = editDate.getText().toString();
    	String location = editLocation.getText().toString();
    	String permit_manager = editPermitManager.getText().toString();
    	String equipments = editEquipment.getText().toString();
    	String workDetail = editWorkDetail.getText().toString();
    	String lotoType = getLotoTypeValue();
    	
    	ArrayList<String> lotoSteps = new ArrayList<String>();
    	
    	for(int i=0 ;i<lotoStepsEditText.size();i++){
    		lotoSteps.add(lotoStepsEditText.get(i).getText().toString());
    	}
    	
    	return new FragmentOneData(date, location, permit_manager,equipments,workDetail,lotoType, lotoSteps);
    	
    }
    
    public void updateView(FragmentOneData data){
    	
    	editDate.setText(data.getFormDate());
    	editLocation.setText(data.getLocation());
    	editPermitManager.setText(data.getPermit_manager());
    	editEquipment.setText(data.getEquipment());
    	editWorkDetail.setText(data.getWorkDetail());
    	setLotoTypeValue(data.getLotoType());
    	
    	for(int i=0 ;i<lotoStepsEditText.size();i++){
    		lotoStepsEditText.get(i).setText(data.getLotoSteps().get(i));
    	}
    	
    }
    
    private String getLotoTypeValue(){
    	
    	int selectedId = lotoTypRadioGroup.getCheckedRadioButtonId();
    	
    	lotoTypeRadioBtn = (RadioButton) rootView.findViewById(selectedId);
    	
    	return lotoTypeRadioBtn.getText().toString();
    	
    }
    
    private void setLotoTypeValue(String val){
    	if(val.equals("Mechanical")){
    		RadioButton b = (RadioButton) rootView.findViewById(R.id.radioMecha);
    		b.setChecked(true);
    	}else if(val.equals("Electrical")){
    		RadioButton b = (RadioButton) rootView.findViewById(R.id.radioElec);
    		b.setChecked(true);
    	}
    	
    }
    
    public  boolean isComplete(){
    	
    	return lotoTypRadioGroup.getCheckedRadioButtonId()>0 && editLocation.getText().toString().length()>0 && editEquipment.getText().toString().length()>0 && editPermitManager.getText().toString().length()>0 && editWorkDetail.getText().toString().length()>0;
    
    }    
    
}
