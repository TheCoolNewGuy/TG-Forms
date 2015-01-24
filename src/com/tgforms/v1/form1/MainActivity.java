package com.tgforms.v1.form1;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.tgforms.v1.R;
import com.tgforms.v1.SendActivity;
import com.tgforms.v1.viewPagerAdapter;
import com.tgforms.v1.utils.PdfMaker;


public class MainActivity extends FragmentActivity {
	
	ViewPager viewPager;
	com.tgforms.v1.form1.StoreData storedata = new StoreData();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		
	}
	
	public com.tgforms.v1.form1.StoreData getStoreDataObject(){
		
	   return storedata;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
        
	    case R.id.menubtnsend:
	    	new PdfMaker();
        	Intent intent = new Intent(this, SendActivity.class);
        	startActivity(intent);
        	
        	return true;
        default:
            return super.onOptionsItemSelected(item);
     }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		List<Fragment> fragments =  getSupportFragmentManager().getFragments();
	    
		for(Fragment each: fragments){
			each.onActivityResult(requestCode, resultCode, data);
		}
		
	}
}
