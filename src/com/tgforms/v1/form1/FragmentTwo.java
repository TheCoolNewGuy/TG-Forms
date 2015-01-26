package com.tgforms.v1.form1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

import com.tgforms.v1.R;
import com.tgforms.v1.SendActivity;
import com.tgforms.v1.pojo.ClearanceData;
import com.tgforms.v1.signaturecapture.CaptureSignature;
import com.tgforms.v1.utils.Utilities;

public class FragmentTwo extends android.support.v4.app.Fragment {
	
	View rootView;
	
	EditText workComplete;
	ArrayList<EditText> nameList,openDate,closeDate; 
	HashMap<String, ImageView> signList;
	HashMap<String, EditText > dateList;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.pagetwo, container, false);
        
        nameList = new ArrayList<EditText>();
        openDate = new ArrayList<EditText>();
        closeDate = new ArrayList<EditText>();
        
        signList = new HashMap<String, ImageView>();
        dateList = new HashMap<String, EditText>();
        
        StoreData.clearanceDataList = new HashMap<String, ClearanceData>();
        
        for(int i=1;i<11;i++)
        {
        	StoreData.clearanceDataList.put(String.valueOf(i), new ClearanceData(i+". ", null, null, "n/a", "n/a", null, null, "n/a ", "n/a", false));
        }
        
        workComplete = (EditText) rootView.findViewById(R.id.editWorkComplete);
        workComplete.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				StoreData.workComplete = "Work Complete(Print) : "+workComplete.getText().toString();
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
        
        setNameEditTexts();
        setDateEditTexts();
        setSignatueViews();
        addWatchers(); 
        
        return rootView;
    }
    
	private void setSignatueViews ()
	{
		ImageView view;
		
		view= (ImageView) rootView.findViewById(R.id.form1Page2openSign1);
		StoreData.clearanceDataList.get("1").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign2);
		StoreData.clearanceDataList.get("2").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign3);
		StoreData.clearanceDataList.get("3").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign4);
		StoreData.clearanceDataList.get("4").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign5);
		StoreData.clearanceDataList.get("5").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign6);
		StoreData.clearanceDataList.get("6").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign7);
		StoreData.clearanceDataList.get("7").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign8);
		StoreData.clearanceDataList.get("8").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign9);
		StoreData.clearanceDataList.get("9").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign10);
		StoreData.clearanceDataList.get("10").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign1);
		StoreData.clearanceDataList.get("1").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign2);
		StoreData.clearanceDataList.get("2").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign3);
		StoreData.clearanceDataList.get("3").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign4);
		StoreData.clearanceDataList.get("4").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign5);
		StoreData.clearanceDataList.get("5").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign6);
		StoreData.clearanceDataList.get("6").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign7);
		StoreData.clearanceDataList.get("7").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign8);
		StoreData.clearanceDataList.get("8").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign9);
		StoreData.clearanceDataList.get("9").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign10);
		StoreData.clearanceDataList.get("10").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.workCompleteSign);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
	}
	
	private void setNameEditTexts()
	{

        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name1));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name2));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name3));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name4));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name5));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name6));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name7));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name8));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name9));
        nameList.add((EditText) rootView.findViewById(R.id.form1Page2name10));
        
	}
	
	private void setDateEditTexts()
	{
		EditText view;
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate1);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate2);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate3);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate4);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate5);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate6);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate7);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate8);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate9);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2dopenate10);
		dateList.put(view.getTag().toString(), view);
		
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate1);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate2);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate3);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate4);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate5);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate6);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate7);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate8);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate8);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate10);
		dateList.put(view.getTag().toString(), view);
		
	}
	
    private void addWatchers()
    {
   	 
    	for(int i=0;i<nameList.size();i++)
   	 {
   		 addTextChangedListener(nameList.get(i), i);
   	 }
    	
    }
    
    private void addTextChangedListener(final EditText editText,final int index)
    {
    	editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
				String value = editText.getText().toString();
				ClearanceData data = StoreData.clearanceDataList.get(String.valueOf(index+1));
				data.setName(Integer.toString(index+1)+". "+value);
					
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
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	 if (resultCode == 1) {
    		 
             Bitmap b = BitmapFactory.decodeByteArray(
                     data.getByteArrayExtra("byteArray"), 0,
                     data.getByteArrayExtra("byteArray").length);
             
             String viewId = data.getStringExtra("view_id");
             
             if(signList.get(viewId)!=null)
            	 scaleImage(signList.get(viewId), b);
             
             if(dateList.get(viewId)!=null){
            	 dateList.get(viewId).setText(Utilities.getDate());
            	 setClearanceData(viewId,b);
             }else{
            	 //last signature
            	 StoreData.workCompleteSignBitmap=b;
            	 StoreData.workCompleteSignDone=true;
             }
            	 
             
             
             //SAVING IN SD-CARD
             
//             String root = Environment.getExternalStorageDirectory().toString();
//             File myDir = new File(root + "/saved_images");    
//             myDir.mkdirs();
//             
//             String fname = data.getStringExtra("view_id") +".png";
//             File file = new File (myDir, fname);
//             if (file.exists ()) file.delete (); 
//             try {
//                    FileOutputStream out = new FileOutputStream(file);
//                    b.compress(Bitmap.CompressFormat.PNG, 90, out);
//                    out.flush();
//                    out.close();
//
//             } catch (Exception e) {
//                    e.printStackTrace();
//             }
             
//             store.image_url=myDir.toString()+"/"+fname;
//             System.out.println(store.image_url);
         }

    }
    
    private void setClearanceData(String tag,Bitmap bitmap)
    {
    	for (ClearanceData value : StoreData.clearanceDataList.values()) {
    	    
    		if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoClose().getTag().toString())){
    			value.setBitmapSignCleartoClose(bitmap);
    			value.setBase64SignCleartoClose(Utilities.encodeTobase64(bitmap));
    	    	value.setDateClose(Utilities.getDate());
    	    }else if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoOpen().getTag().toString())){
    	    	value.setBitmapSignCleartoOpen(bitmap);
    			value.setBase64SignCleartoOpen(Utilities.encodeTobase64(bitmap));
    	    	value.setDateOpen(Utilities.getDate());
    	    }
    	}
    	
    	
    }
    
    private void addTouchListener(ImageView view)
    {
    	view.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				ClearanceData data = getClearanceDataFromTag(v.getTag().toString());
				if(data!=null){
					
					if(data.getName().length()>3){
						Intent intent = new Intent(getActivity(), CaptureSignature.class);
						intent.putExtra("view_id",v.getTag().toString());
						getActivity().startActivityForResult(intent, 0);
					}
					
				}else{
				
					if(Utilities.isFormOneComplete())
					{
						Intent intent = new Intent(getActivity(), CaptureSignature.class);
						intent.putExtra("view_id",v.getTag().toString());
						getActivity().startActivityForResult(intent, 0);
					}
					
				}
				
			}
		});
    }
    
    private ClearanceData getClearanceDataFromTag(String tag)
    {
    	for (ClearanceData value : StoreData.clearanceDataList.values()) {
    	    
    		if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoClose().getTag().toString())){
    			return value;
    	    }else if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoOpen().getTag().toString())){
    	    	return value;
    	    }
    	}
		return null;
    	
    	
    }
    
    private void scaleImage(ImageView view,Bitmap bitMap)
    {
    	int bwidth=bitMap.getWidth();
    	int bheight=bitMap.getHeight();
    	int swidth=view.getWidth();
    	
    	int new_width=swidth;
    	int new_height = (int) Math.floor((double) bheight *( (double) new_width / (double) bwidth));
    	
    	Bitmap newbitMap = Bitmap.createScaledBitmap(bitMap,new_width,new_height, true);
    	view.setImageBitmap(newbitMap);
        
    }


}
