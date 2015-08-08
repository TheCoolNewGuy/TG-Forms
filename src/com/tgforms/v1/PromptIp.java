package com.tgforms.v1;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tgforms.v1.utils.Utilities;

public class PromptIp extends Activity implements OnClickListener{

	EditText otherIp;
	TextView ownIp;
	Button saveIp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prompt_ip);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.prompt_ip, menu);
		
		otherIp = (EditText) findViewById(R.id.editIp);
		ownIp = (TextView) findViewById(R.id.showIpSelf);
		saveIp = (Button) findViewById(R.id.saveIp);
		
		String deviceIp = getIpAddress();
		saveIp.setOnClickListener(this);
		ownIp.setText(deviceIp!=null?deviceIp:"Not found");
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void SaveIp (String ipaddrs){
		
		if(ipaddrs.length()>0){
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
			  SharedPreferences.Editor editor = preferences.edit();
			  editor.putString("Ip",ipaddrs);
			  editor.apply();
			  Utilities.showToast(getApplicationContext(), "IP saved");
			  finish();
		}else{
			Utilities.showToast(getApplicationContext(), "Invalid IP");
		}
		 
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SaveIp(otherIp.getText().toString());
	}
	
	public static String getIpAddress() { 
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = (NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        String ipAddress=inetAddress.getHostAddress().toString();
                        Log.e("IP address",""+ipAddress);
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("Socket exception in GetIP Address of Utilities", ex.toString());
        }
        return null; 
}
}
