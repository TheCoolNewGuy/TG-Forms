package com.tgforms.v1;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import com.tgforms.v1.R;
import com.tgforms.v1.form1.MainActivity;
import com.tgforms.v1.form1.StoreData;
import com.tgforms.v1.signaturecapture.CaptureSignature;
import com.tgforms.v1.utils.PdfMaker;

public class SendActivity extends Activity {

	private int year;
    private int month;
    private int day;
    
    ImageView image;
    EditText editDate1,editDate2,editSignedfor;
    Button btn;
    
    MainActivity mActivity;
    View rootView;
    
    StringBuilder mailBody;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
		
		addWatchers();
        
      	image= (ImageView) findViewById(R.id.imageView1);
        image.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				
				
				Intent intent = new Intent(SendActivity.this, CaptureSignature.class);
				startActivityForResult(intent, 0);
				
			}
		});
        
        
        editDate1 =(EditText) findViewById(R.id.editdate1);
        editDate2 =(EditText) findViewById(R.id.editdate2);
        editDate1.setInputType(InputType.TYPE_NULL);
        editDate2.setInputType(InputType.TYPE_NULL);
        
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);

        btn = (Button) findViewById(R.id.send);
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mailBody=StoreData.getData();
				sendMail(mailBody);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.send, menu);
		return true;
	}
	
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
        if (resultCode == 1) {
            Bitmap b = BitmapFactory.decodeByteArray(
                    data.getByteArrayExtra("byteArray"), 0,
                    data.getByteArrayExtra("byteArray").length);
            image.setImageBitmap(b);
            
            //SAVING IN SD-CARD
            
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/saved_images");    
            myDir.mkdirs();
            
            String fname = "sign" +".png";
            File file = new File (myDir, fname);
            if (file.exists ()) file.delete (); 
            try {
                   FileOutputStream out = new FileOutputStream(file);
                   b.compress(Bitmap.CompressFormat.PNG, 90, out);
                   out.flush();
                   out.close();

            } catch (Exception e) {
                   e.printStackTrace();
            }
            
//            store.image_url=myDir.toString()+"/"+fname;
//            System.out.println(store.image_url);
        }
    }
    
    private void openDatePicker (){
        DatePickerDialog dpdFromDate = new DatePickerDialog(this,datePickerListener , year, month,
                  day);
        dpdFromDate.show();
      }
      
      private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

    	  public void onDateSet(DatePicker view, int selectedYear,
                  int selectedMonth, int selectedDay) {
              year = selectedYear;
              month = selectedMonth;
              day = selectedDay;

              editDate1.setText(new StringBuilder().append(month + 1)
                      .append("-").append(day).append("-").append(year)
                      .append(" "));
              editDate2.setText(new StringBuilder().append(month + 1)
                      .append("-").append(day).append("-").append(year)
                      .append(" "));
//              store.Date2="Date : "+editDate1.getText().toString();
//              store.Date3="Date : "+editDate1.getText().toString();

          }
      };
      
      private void addWatchers(){
    	   	 
    	  editSignedfor = (EditText) findViewById(R.id.editsignedfor);
    	  editSignedfor.addTextChangedListener(new TextWatcher() {
    	   		 
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
//				store.Signed_for_Client = " Signed for Client : "+editSignedfor.getText().toString();
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
    }
      
    private void sendMail(StringBuilder text){
    	
    	System.out.println("send mail");
    	String[] receiver = {"tofael.raju@gmail.com"};
    	String subject  = "Site Inspection Report";
    	String body     = text.toString();
    	
    	System.out.println(body);
//    	File file = new File(store.image_url);
//    	Uri imageUri = Uri.fromFile(file);
//    	Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
//
//    	
//    	emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,receiver);
//    	emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);
//    	emailIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
//    	emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);
//    	emailIntent.setType("image/png");
//    	startActivity(Intent.createChooser(emailIntent, "Sending Mail"));
    }

}
