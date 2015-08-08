package com.tgforms.v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tgforms.v1.form1.MainActivity;
import com.tgforms.v1.ftpclient.FtpClient;

public class HomeActivity extends Activity {

	ListView listview;
	FormListAdapter adapter;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		listview = (ListView) findViewById(R.id.formList);
        adapter = new FormListAdapter(this);
        listview.setAdapter(adapter);
        
        listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				goToForm();
			}
        	
		});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent intent = new Intent(getApplicationContext(), PromptIp.class);
			startActivity(intent);
			return true;
		} else if(id == R.id.menubtnftp){
			Intent intent = new Intent(getApplicationContext(),FtpClient.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void goToForm()
	{
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
	
	
}
