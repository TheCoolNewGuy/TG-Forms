package com.tgforms.v1.connectivity;

import java.util.ArrayList;
import java.util.List;

import com.tgforms.v1.HomeActivity;
import com.tgforms.v1.form1.MainActivity;
import com.tgforms.v1.utils.Utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;

public class P2Prevceiver extends BroadcastReceiver {

	private WifiP2pManager mManager;
	private Channel mChannel;
	private MainActivity mActivity;

	PeerListListener myPeerListListener;

	private List peers = new ArrayList();

	public P2Prevceiver(WifiP2pManager manager, Channel channel,
			MainActivity activity) {
		super();
		this.mManager = manager;
		this.mChannel = channel;
		this.mActivity = activity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub

		System.out.println("rcvd");
		String action = intent.getAction();
		System.out.println(action);
		if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
			int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
			if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
				System.out.println("enabled");
			} else {
				System.out.println("enabled");
			}
		} else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

			System.out.println("peers changed");
			if (mManager != null) {
				mManager.requestPeers(mChannel, new PeerListListener() {

					@Override
					public void onPeersAvailable(WifiP2pDeviceList peerList) {
						// TODO Auto-generated method stub
						// Out with the old, in with the new.
						peers.clear();
						peers.addAll(peerList.getDeviceList());

						// If an AdapterView is backed by this data, notify it
						// of the change. For instance, if you have a ListView
						// of available
						// peers, trigger an update.

						if (peers.size() == 0) {
							System.out.println("No devices found");
							return;
						} else {
							Utilities.showToast(mActivity, "Peer found!");
						}

					}
				});
			}
		} else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION
				.equals(action)) {
			// Respond to new connection or disconnections
		} else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION
				.equals(action)) {
			// Respond to this device's wifi state changing
			
		}

	}

}
