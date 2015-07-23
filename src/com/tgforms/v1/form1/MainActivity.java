package com.tgforms.v1.form1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.tgforms.v1.PromptIp;
import com.tgforms.v1.R;
import com.tgforms.v1.form1.FragmentTwo.ActivityConnector;
import com.tgforms.v1.utils.Utilities;
import com.tgforms.v1.utils.Utilities.MyCallback;

public class MainActivity extends FragmentActivity implements ActivityConnector {

	ViewPager viewPager;

	private Socket client;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	serverAsyncTask serverAsyncTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPagerAdapter adapter = new viewPagerAdapter(
				getSupportFragmentManager());
		viewPager.setAdapter(adapter);

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
			System.out.println("send");	
			SendMessage sendMessageTask = new SendMessage();
			Object Void = null;
			sendMessageTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,(java.lang.Void[]) Void);
			return true;

		case R.id.menubtnsave:

			validateAndSave();
			return true;

		case R.id.menubtnrcv:

			try {
				initiateServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return true;

		case R.id.action_settings:

			Intent intent = new Intent(getApplicationContext(), PromptIp.class);
			startActivity(intent);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Utilities.dismissLoadingDialog();
		
	}

	private void initiateServer() throws IOException {
		
		Object Void = null;
		serverAsyncTask = new serverAsyncTask();
		serverAsyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (String[]) Void);

		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);
		
		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (Fragment each : fragments) {
			each.onActivityResult(requestCode, resultCode, data);
		}

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
	}

	private class SendMessage extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {

				String Ip = getIP();
				System.out.println("send to "+Ip);
				if (Ip.length() > 0) {
					showToastFromBackground("Sending to "+Ip);
					FormData data = getData();
					client = new Socket(Ip, 9999); // connect to the server
					ObjectOutputStream out = new ObjectOutputStream(
							client.getOutputStream());
					out.writeObject(data);
					client.close();
					Handler handler = new Handler(Looper.getMainLooper());

					handler.post(new Runnable() {

						@Override
						public void run() {
							// Your UI code here
							Utilities.showToast(getApplicationContext(),
									"Data sent");
						}
					});
					return null;

				} else {
					showToastFromBackground("Invalid IP,Please update.");
					this.cancel(true);	
				}

			} catch (UnknownHostException e) {
				showToastFromBackground("No device listening at specified IP ");
				e.printStackTrace();
				this.cancel(true);	
			} catch (IOException e) {
				showToastFromBackground("IO exception.");
				e.printStackTrace();
				this.cancel(true);	
			}
			return null;
		}

	}

	private String getIP() {
		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		String ipAdrs = preferences.getString("Ip", "");

		return ipAdrs;
	}

	private void showToastFromBackground(final String message) {
		Handler handler = new Handler(Looper.getMainLooper());

		handler.post(new Runnable() {

			@Override
			public void run() {
				// Your UI code here
				Utilities.showToast(getApplicationContext(), message);
			}
		});
	}

	private void showInputDialgoe(final FragmentOneData f1Data,
			final FragmentTwoData f2Data) {

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Enter file name :");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Editable value = input.getText();
				new PdfMaker(getApplicationContext(), value.toString(), f1Data,
						f2Data);
				finish();
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();
	}

	private FormData getData() {

		FragementOne f1 = null;
		FragmentTwo f2 = null;

		FragmentOneData f1Data = null;
		FragmentTwoData f2Data = null;

		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (Fragment each : fragments) {
			String tag = each.getArguments().getString("TAG");

			if (tag == "1") {
				f1 = (FragementOne) each;
				f1Data = f1.getData();
			} else if (tag == "2") {
				f2 = (FragmentTwo) each;
				f2Data = f2.getData();
			}
		}

		return new FormData(f1Data, f2Data);
	}

	private void validateAndSave() {

		FragementOne f1 = null;
		FragmentTwo f2 = null;

		FragmentOneData f1Data = null;
		FragmentTwoData f2Data = null;

		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (Fragment each : fragments) {
			String tag = each.getArguments().getString("TAG");

			if (tag == "1") {
				f1 = (FragementOne) each;
				f1Data = f1.getData();
			} else if (tag == "2") {
				f2 = (FragmentTwo) each;
				f2Data = f2.getData();
			}
		}

		System.out.println(f1.isComplete());
		System.out.println(f2.isComplete());

		if (f1.isComplete() && f2.isComplete())
			showInputDialgoe(f1Data, f2Data);
		else
			Utilities.showToast(getApplicationContext(),
					"Please complete all required fields");

	}

	private Context getProgressBarContext() {
		Context con;

		if (getParent() != null)
			con = getParent();
		else
			con = this;

		return con;
	}

	@Override
	public boolean isPageOneComplete() {
		// TODO Auto-generated method stub

		List<Fragment> fragments = getSupportFragmentManager().getFragments();

		for (Fragment each : fragments) {
			String tag = each.getArguments().getString("TAG");

			if (tag == "1") {

				FragementOne f1 = (FragementOne) each;
				return f1.isComplete();
			}
		}
		return false;
	}

	class serverAsyncTask extends AsyncTask<String, Void, FormData> implements MyCallback {

		private volatile boolean keepListening = true;
		private ProgressDialog pdia;
		serverAsyncTask task = this; 
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pdia = new ProgressDialog(getProgressBarContext());
			pdia.setTitle("Please wait");
			pdia.setMessage("Waiting.....");
			pdia.setCancelable(true);
			pdia.setOnCancelListener(new OnCancelListener() {
				
				@Override
				public void onCancel(DialogInterface dialog) {
					// TODO Auto-generated method stub
					keepListening=false;
					System.out.println("canceling as task");
					task.cancel(true);
					if(clientSocket!=null)
						try {
							clientSocket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if(serverSocket!=null)
						try {
							serverSocket.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
			if (!isFinishing())
				pdia.show();  
		}

		@Override
		protected FormData doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			System.out.println("New asyncatask");
			try {
				serverSocket = new ServerSocket(9999); // Server socket
				

			} catch (IOException e) {
				System.out.println(e.getMessage());
				showToastFromBackground("Could not listen on port: 9999.");
				System.out.println("Could not listen on port: 9999");
			}

			while (keepListening) {
					
				try {
					System.out.println(keepListening);	
					clientSocket = serverSocket.accept(); // accept the client
															// connection
					ObjectInputStream in = new ObjectInputStream(
							clientSocket.getInputStream());
					FormData messageObject = (FormData) in.readObject();
					clientSocket.close();
					serverSocket.close();
					return messageObject;

				} catch (IOException ex) {
					Utilities.dismissLoadingDialog();
					System.out.println("Problem in message reading");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					showToastFromBackground("ClassNotFoundException.");
					Utilities.dismissLoadingDialog();
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(FormData result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pdia.dismiss();
			
			if(result!=null){
			
				FragementOne f1 = null;
				FragmentTwo f2 = null;

				List<Fragment> fragments = getSupportFragmentManager()
						.getFragments();

				for (Fragment each : fragments) {
					String tag = each.getArguments().getString("TAG");

					if (tag == "1") {
						f1 = (FragementOne) each;
						f1.updateView(result.getFragmentOneData());
					} else if (tag == "2") {
						f2 = (FragmentTwo) each;
						f2.updateView(result.getFragmentTwoData());
					}
				}

			}
			
		}

		@Override
		public void onProgressBarCancel() {
			// TODO Auto-generated method stub
			keepListening=false;
		}

	}

	private void cancelAsyncTask(){
		if(serverAsyncTask!=null){
			System.out.println("canceling asynctask");
			serverAsyncTask.cancel(true);
			serverAsyncTask=null;
		} 
	}

}
