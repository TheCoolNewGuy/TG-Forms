package com.tgforms.v1.ftpclient;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.tgforms.v1.R;
import com.tgforms.v1.receivers.AlarmReceiver;
import com.tgforms.v1.utils.Constants;
import com.tgforms.v1.utils.Utilities;

public class FtpClient extends Activity implements OnClickListener {

	Button upload, download, save, schedule;
	EditText ftpIp, ftpUser, ftpPassword, ftpPort;
	Context con = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ftp_client);
		con = this;

		upload = (Button) findViewById(R.id.uploadFiles);
		download = (Button) findViewById(R.id.downloadFiles);
		save = (Button) findViewById(R.id.saveFtpDetails);
		schedule = (Button) findViewById(R.id.editSchedule);

		ftpIp = (EditText) findViewById(R.id.editFtpIp);
		ftpPort = (EditText) findViewById(R.id.editFtpPort);
		ftpUser = (EditText) findViewById(R.id.editFtpUser);
		ftpPassword = (EditText) findViewById(R.id.editFtpPass);

		ftpIp.setText(Utilities.getFtpIp(this));
		ftpPort.setText(Utilities.getFtpPort(this));
		ftpUser.setText(Utilities.getFtpUser(this));
		ftpPassword.setText(Utilities.getFtpPass(this));

		upload.setOnClickListener(this);
		download.setOnClickListener(this);
		save.setOnClickListener(this);
		schedule.setOnClickListener(this);
		schedule.setText(getScheduleTimeString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ftp_client, menu);
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

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {

		case R.id.uploadFiles:
			FtpSync.getInstance().uploadFiles(con);
			break;

		case R.id.downloadFiles:
			FtpSync.getInstance().downloadConfigurationFile(con);
			break;

		case R.id.saveFtpDetails:
			saveFtpDetails();
			break;

		case R.id.editSchedule:
			openTimePickerDialog();
			break;
		}
	}

	private void saveFtpDetails() {

		String ip = ftpIp.getText().toString();
		String port = ftpPort.getText().toString();
		String user = ftpUser.getText().toString();
		String password = ftpPassword.getText().toString();

		if (ip.length() > 0 && user.length() > 0 && password.length() > 0) {
			SharedPreferences preferences = PreferenceManager
					.getDefaultSharedPreferences(this);

			SharedPreferences.Editor editor = preferences.edit();
			editor.putString(Constants.KEY_FTP_IP, ip);
			editor.putString(Constants.KEY_FTP_PORT, port);
			editor.putString(Constants.KEY_FTP_USER, user);
			editor.putString(Constants.KEY_FTP_PASS, password);
			editor.apply();
			Utilities.showToast(getApplicationContext(),
					"FTP server credentials saved");
			// finish();

		} else {
			Utilities.showToast(getApplicationContext(),
					"Please fill in all fields");
		}
	}

	private void openTimePickerDialog() {
		Calendar calendar = Calendar.getInstance();

		TimePickerDialog timePickerDialog = new TimePickerDialog(
				FtpClient.this, onTimeSetListener,
				calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), false);
		timePickerDialog.setTitle("Set Sync Time");

		timePickerDialog.show();

	}

	OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

			if (view.isShown()) {

				Calendar calNow = Calendar.getInstance();
				Calendar calSet = (Calendar) calNow.clone();

				calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
				calSet.set(Calendar.MINUTE, minute);
				calSet.set(Calendar.SECOND, 0);
				calSet.set(Calendar.MILLISECOND, 0);

				if (calSet.compareTo(calNow) <= 0) {
					// Today Set time passed, count to tomorrow
					calSet.add(Calendar.DATE, 1);
				}

				String am_pm = null;
				if (calSet.get(Calendar.AM_PM) == Calendar.AM)
					am_pm = "AM";
				else if (calSet.get(Calendar.AM_PM) == Calendar.PM)
					am_pm = "PM";

				String strHrsToShow = (calSet.get(Calendar.HOUR) == 0) ? "12"
						: Integer.toString(calSet.get(Calendar.HOUR));
				schedule.setText(strHrsToShow + " : " + minute + " " + am_pm);
				storeScheduleTimeString(strHrsToShow + " : " + minute + " "
						+ am_pm);
				setAlarm(calSet);
				
			}

		}
	};

	private void setAlarm(Calendar targetCal) {

		Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				getBaseContext(), 0, intent, 0);
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
				pendingIntent);

		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
				targetCal.getTimeInMillis(), TimeUnit.MINUTES.toMillis(5),
				pendingIntent);
		
	}

	private void storeScheduleTimeString(String time) {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(Constants.KEY_FTP_SCHEDULE, time);
		editor.apply();
	}

	private String getScheduleTimeString() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String time = preferences.getString(Constants.KEY_FTP_SCHEDULE,
				"Touch to set time");

		return time;
	}

}
