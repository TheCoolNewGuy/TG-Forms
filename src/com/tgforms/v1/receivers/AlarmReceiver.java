package com.tgforms.v1.receivers;

import com.tgforms.v1.ftpclient.FtpSync;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context con, Intent intent) {
		// TODO Auto-generated method stub
		FtpSync.getInstance().startSyncing(con);
	}

}
