package com.tgforms.v1.form1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.itextpdf.xmp.impl.Utils;
import com.tgforms.v1.PromptIp;
import com.tgforms.v1.R;
import com.tgforms.v1.viewPagerAdapter;
import com.tgforms.v1.connectivity.P2Prevceiver;

import com.tgforms.v1.connectivity.SocketServer;
import com.tgforms.v1.pojo.ClearanceData;
import com.tgforms.v1.pojo.TransferableClearanceData;
import com.tgforms.v1.pojo.TransferableObject;
import com.tgforms.v1.utils.PdfMaker;
import com.tgforms.v1.utils.Utilities;

public class MainActivity extends FragmentActivity {

	ViewPager viewPager;
	com.tgforms.v1.form1.StoreData storedata = new StoreData();

	WifiP2pManager mManager;
	Channel mChannel;
	BroadcastReceiver mReceiver;

	IntentFilter intentFilter;

	SocketServer server;
	private Socket client;
	private PrintWriter printwriter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		server = new SocketServer(9990);

		// mManager = (WifiP2pManager)
		// getSystemService(Context.WIFI_P2P_SERVICE);
		// mChannel = mManager.initialize(this, getMainLooper(), null);
		// mReceiver = new P2Prevceiver(mManager, mChannel, this);
		//
		// intentFilter = new IntentFilter();
		// intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
		// intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
		// intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
		// intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPagerAdapter adapter = new viewPagerAdapter(
				getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
		// WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		// int ipAddress = wifiInfo.getIpAddress();

	}

	public com.tgforms.v1.form1.StoreData getStoreDataObject() {

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
			// discoverPeers();
			SendMessage sendMessageTask = new SendMessage();
			sendMessageTask.execute();

			return true;
		case R.id.menubtnsave:
			if (Utilities.isFormOneComplete() && storedata.workCompleteSignDone)
				new PdfMaker(getApplicationContext());
			else
				Utilities.showToast(getApplicationContext(),
						"Please complete all required fields");
			return true;

		case R.id.menubtnrcv:
			initiateServer();
			return true;
		case R.id.action_settings:
			Intent intent = new Intent(getApplicationContext(), PromptIp.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void discoverPeers() {
		// TODO Auto-generated method stub
		mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {

			@Override
			public void onSuccess() {
				System.out.println("Peer found");

			}

			@Override
			public void onFailure(int reasonCode) {
				System.out.println(reasonCode);
				System.out.println("Peer not found");
			}
		});
	}

	public void initiateServer() {
		try {
			server.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		System.out.println("result");
		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (Fragment each : fragments) {
			each.onActivityResult(requestCode, resultCode, data);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("resumed");
		// registerReceiver(mReceiver, intentFilter);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("paused");
		// unregisterReceiver(mReceiver);
	}

	private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {

				String Ip = getIP();
				System.out.println(Ip);
				if (Ip.length() > 0) {
					TransferableObject data = getTransferableData();
					System.out.println(data.getLocation());
					System.out.println(data.getPermit_manager());
					client = new Socket(Ip, 4444); // connect to the server
					ObjectOutputStream out = new ObjectOutputStream(
							client.getOutputStream());
					out.writeObject(data);
					client.close();
				} else {
					Utilities.showToast(getApplicationContext(), "Invalid IP");
				}

			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

	}

	private TransferableObject getTransferableData() {

		HashMap<String, TransferableClearanceData> TAclearanceDataList = new HashMap<String, TransferableClearanceData>();
		;

		for (int i = 0; i < 10; i++) {
			ClearanceData data = StoreData.clearanceDataList.get(String
					.valueOf(i + 1));
			TAclearanceDataList.put(
					String.valueOf(i) + 1,
					new TransferableClearanceData(data.getName(), data
							.getBase64SignCleartoOpen(), data.getDateOpen(),
							data.getBase64SignCleartoClose(), data
									.getDateClose(),
							data.getName().length() > 0));
		}

		return new TransferableObject(storedata.FormDate, storedata.Location,
				storedata.Permit_manager, storedata.Equipment,
				storedata.workComplete, storedata.lotoSteps,
				TAclearanceDataList, "");
	}

	private String getIP() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String ipAdrs = preferences.getString("Ip", "");

		return ipAdrs;
	}

}
