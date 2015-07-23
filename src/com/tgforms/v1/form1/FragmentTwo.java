package com.tgforms.v1.form1;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.tgforms.v1.R;
import com.tgforms.v1.signaturecapture.CaptureSignature;
import com.tgforms.v1.utils.Utilities;

public class FragmentTwo extends android.support.v4.app.Fragment {
	
	View rootView;
	
	ArrayList<EditText> nameList,openDate,closeDate;
	ActivityConnector mCallback;


	HashMap<String, ClearanceData> clearanceDataList;
	HashMap<String, ImageView> signList;
	HashMap<String, EditText > dateList;
	
	EditText editWorkComplete;
	
	Bitmap workCompleteSignBitmap;
	Bitmap headerBitmap;
	boolean workCompleteSignDone=false;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.pagetwo, container, false);
        
        nameList = new ArrayList<EditText>();
        openDate = new ArrayList<EditText>();
        closeDate = new ArrayList<EditText>();
        
        signList = new HashMap<String, ImageView>();
        dateList = new HashMap<String, EditText>();
        clearanceDataList = new HashMap<String, ClearanceData>();
        
        editWorkComplete = (EditText) rootView.findViewById(R.id.editWorkComplete);

        setNameEditTexts();
        
        for(int i=1;i<11;i++)
        {
        	clearanceDataList.put(String.valueOf(i), new ClearanceData("",nameList.get(i-1),null, null, "n/a", "n/a", null, null, "n/a ", "n/a", false));
        }
        setDateEditTexts();
        setSignatueViews();
        
        return rootView;
    }
    
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		 try {
	            mCallback = (ActivityConnector) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
		 
		 
	}
	
	private void setSignatueViews ()
	{
		ImageView view;
		
		view= (ImageView) rootView.findViewById(R.id.form1Page2openSign1);
		clearanceDataList.get("1").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign2);
		clearanceDataList.get("2").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign3);
		clearanceDataList.get("3").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign4);
		clearanceDataList.get("4").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign5);
		clearanceDataList.get("5").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign6);
		clearanceDataList.get("6").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign7);
		clearanceDataList.get("7").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign8);
		clearanceDataList.get("8").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign9);
		clearanceDataList.get("9").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2openSign10);
		clearanceDataList.get("10").setSignCleartoOpen(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign1);
		clearanceDataList.get("1").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign2);
		clearanceDataList.get("2").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign3);
		clearanceDataList.get("3").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign4);
		clearanceDataList.get("4").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign5);
		clearanceDataList.get("5").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign6);
		clearanceDataList.get("6").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign7);
		clearanceDataList.get("7").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign8);
		clearanceDataList.get("8").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign9);
		clearanceDataList.get("9").setSignCleartoClose(view);
		signList.put(view.getTag().toString(), view);
		addTouchListener(view);
		
		view = (ImageView) rootView.findViewById(R.id.form1Page2closeSign10);
		clearanceDataList.get("10").setSignCleartoClose(view);
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
		
		for(int i=0; i<10;i++){
            addTextChangeListener(nameList.get(i));
		}
        
	}
	
	private void addTextChangeListener(EditText editText){
	
		editText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				System.out.println("AT");
				highlightOpenSigns();
			}
		});
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
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate9);
		dateList.put(view.getTag().toString(), view);
		
		view = (EditText) rootView.findViewById(R.id.form1Page2closedate10);
		dateList.put(view.getTag().toString(), view);
		
		
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
            	 workCompleteSignBitmap=b;
            	 workCompleteSignDone=true;
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
    	for (ClearanceData value : clearanceDataList.values()) {
    	    
    		if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoClose().getTag().toString())){
    			value.setBitmapSignCleartoClose(bitmap);
    			value.setBase64SignCleartoClose(Utilities.encodeTobase64(bitmap));
    	    	value.setDateClose(Utilities.getDate());
    	    	value.getSignCleartoClose().setBackgroundResource(0);
    	    	highlightCloseSigns();
    	    	
    	    }else if(Integer.parseInt(tag)==Integer.parseInt(value.getSignCleartoOpen().getTag().toString())){
    	    	value.setBitmapSignCleartoOpen(bitmap);
    			value.setBase64SignCleartoOpen(Utilities.encodeTobase64(bitmap));
    	    	value.setDateOpen(Utilities.getDate());
    	    	value.getSignCleartoOpen().setBackgroundResource(0);
    	    	highlightOpenSigns();
    	    	highlightCloseSigns();
    	    }
    	}
    	
    	
    }
    
    private void addTouchListener(ImageView view)
    {
    	view.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				ClearanceData data = getClearanceDataFromTag(v.getTag().toString());
				
				if(data!=null){
					System.out.println("name");
					System.out.println(data.getEditName().getText().toString());
					System.out.println(data.getEditName().getText().toString().length());
					if(data.getEditName().getText().toString().length()>1){
						Intent intent = new Intent(getActivity(), CaptureSignature.class);
						intent.putExtra("view_id",v.getTag().toString());
						getActivity().startActivityForResult(intent, 0);
					}
					
				}else{
				
					if(mCallback.isPageOneComplete() && editWorkComplete.getText().toString().length()>0 && isClearenceDataComplete())
					{	
						Intent intent = new Intent(getActivity(), CaptureSignature.class);
						intent.putExtra("view_id",v.getTag().toString());
						getActivity().startActivityForResult(intent, 0);
					}else{
						Utilities.showToast(getActivity().getApplicationContext(),
								"Please complete all required fields");
					}
					
				}
				
			}
		});
    }
    
    private ClearanceData getClearanceDataFromTag(String tag)
    {
    	for (ClearanceData value : clearanceDataList.values()) {
    	    
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
    
    public FragmentTwoData getData(){
    	
    	HashMap<String, TransferableClearanceData> TAclearanceDataList = new HashMap<String, TransferableClearanceData>();
		
		for (int i = 0; i < 10; i++) {
			ClearanceData data = clearanceDataList.get(String
					.valueOf(i + 1));
			
			TAclearanceDataList.put(
					String.valueOf(i+1),
					new TransferableClearanceData(nameList.get(i).getText().toString(), data
							.getBase64SignCleartoOpen(), data.getDateClose(),
							data.getBase64SignCleartoClose(), data
									.getDateOpen(),
							data.getName().length() > 0));
			
		}
		
		String base64WorkComplete = Utilities.encodeTobase64(workCompleteSignBitmap);
    	
		return new FragmentTwoData(editWorkComplete.getText().toString(), base64WorkComplete, TAclearanceDataList);
    }

    public void updateView(FragmentTwoData fData){
    	
    	HashMap<String,TransferableClearanceData> clList = fData.getClearanceDataList(); 
    	
        for (int i=0;i<clList.size();i++){
        	
        	TransferableClearanceData data = clList.get(String.valueOf(i+1));
        	ClearanceData clData = clearanceDataList.get(String.valueOf(i+1));
        	
        	if(data!=null){
        		if(data.getName()!=null) {
            		nameList.get(i).setText(data.getName());
            		clData.setName(data.getName());
            	}
            	if(data.getDateOpen()!=null){
            		int temp = 2*i;
            		temp++;
            		
            		dateList.get(String.valueOf(temp)).setText(data.getDateOpen());
            		clData.setDateOpen(data.getDateOpen());
            	} 
            	if(data.getDateClose()!=null) {
            		int temp = 2*i;
            		temp=temp+2;
            		
            		dateList.get(String.valueOf(temp)).setText(data.getDateClose());
            		clData.setDateClose(data.getDateClose());
            	}
            	
            	if(data.getBase64SignCleartoOpen()!=null) {
            		int temp = 2*i;
            		temp++;
            		
            		ImageView view = signList.get(String.valueOf(temp));
            		Bitmap bmp = Utilities.decodeBase64(data.getBase64SignCleartoOpen());
            		if(bmp!=null)
            			scaleImage(view, bmp);
            		clData.setBase64SignCleartoOpen(data.getBase64SignCleartoOpen());
            		clData.setBitmapSignCleartoOpen(bmp);
            	}
            	
            	if(data.getBase64SignCleartoClose()!=null) {
            		
            		int temp = 2*i;
            		temp=temp+2;
            		
            		ImageView view = signList.get(String.valueOf((temp)));
            		Bitmap bmp = Utilities.decodeBase64(data.getBase64SignCleartoClose());
            		if(bmp!=null)
            		scaleImage(view, bmp);
            		clData.setBase64SignCleartoClose(data.getBase64SignCleartoClose());
            		clData.setBitmapSignCleartoClose(bmp);
            	}
        	}
        	
        	clearanceDataList.put(String.valueOf(i+1), clData);
        }

        if(fData.getWorkComplete()!=null) {
        	editWorkComplete.setText(fData.getWorkComplete());
        }
        
        if(fData.getWorkCompleteSignBase64()!=null) {
    		
        	ImageView view = signList.get(String.valueOf(21));
    		Bitmap bmp = Utilities.decodeBase64(fData.getWorkCompleteSignBase64());
    		
    		if(bmp!=null)
    			scaleImage(view, bmp);
    		workCompleteSignBitmap=bmp;
    		workCompleteSignDone=true;
    	
        }
        
        highlightOpenSigns();
    	highlightCloseSigns();
    }
    
    public  boolean isComplete(){
    	
    	return workCompleteSignDone && editWorkComplete.getText().toString().length()>0 && isClearenceDataComplete();
    
    }
    
    private boolean isClearenceDataComplete(){
		
		for(int i=0; i<10;i++){
            
			ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
			
			if(nameList.get(i).getText().toString().length()>1 ){
				
				if(data.getDateOpen().length()>4 && data.getDateClose().length()>4){
					
				}else{
					
					return false;
				}
			}
		   
		}

		return true;
	}

    private void highlightOpenSigns(){
    	System.out.println("high open ");
    	for(int i=0; i<10;i++){
    		System.out.println("ind "+i);
			ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
			
			if(nameList.get(i).getText().toString().length()>1 ){
				System.out.println(data.getBase64SignCleartoOpen().length());
				if(data.getBase64SignCleartoOpen().length()==3){
					System.out.println("update border");
					data.getSignCleartoOpen().setBackgroundResource(R.drawable.borderbackground);
					removeHighlightsOpenSigns(i+1);
					removeHighlightsCloseSigns(9);
					break;
				} else if (data.getBase64SignCleartoOpen().length()>4){
					data.getSignCleartoOpen().setBackgroundResource(0);
				}
			} else if(nameList.get(i).getText().toString().length()==0){
		
				data.getSignCleartoOpen().setBackgroundResource(0);
				
			}
		   
		}
    
    }
    
    private void highlightCloseSigns(){
    	
    	if(isOpenSignsComplete()){
    		
    		for(int i=9; i>-1;i--){
        		
        		ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
        		
        		if(data.getBase64SignCleartoOpen().length()>3){
        			
        			if(data.getBase64SignCleartoClose().length()==4){
        			
        	    		data.getSignCleartoClose().setBackgroundResource(R.drawable.borderbackground);
    					removeHighlightsCloseSigns(i-1);
    					break;
    				} else if(data.getBase64SignCleartoClose().length()>4){
    					data.getSignCleartoClose().setBackgroundResource(0);
    				}
    			}
    			
    		}	
    	}
    	
    
    }

    private boolean isOpenSignsComplete(){
    	
    	for(int i=0; i<10;i++){
            
			ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
			if(nameList.get(i).getText().length()>0){
				if(data.getBase64SignCleartoOpen().length()>4){
					
				}else{
					return false;
				}
			}
			   
		}
    	return true;
    }
    
    private void removeHighlightsOpenSigns(int index){
    	
    	for(int i=index; i<10;i++){
            
			ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
			data.getSignCleartoOpen().setBackgroundResource(0);
			
			   
		}
    }
    
    private void removeHighlightsCloseSigns(int index){
    	
    	for(int i=index; i>-1;i--){
            
			ClearanceData data = clearanceDataList.get(String.valueOf(i+1));
			data.getSignCleartoClose().setBackgroundResource(0);
			
			   
		}
    }
    
    // Container Activity must implement this interface
    public interface ActivityConnector {
        public boolean isPageOneComplete();
    }
    
}
