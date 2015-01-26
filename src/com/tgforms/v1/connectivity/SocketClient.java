package com.tgforms.v1.connectivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;

public class SocketClient {

    private String hostname;
    private int port;
    Socket socketClient;

    public SocketClient(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
    }

    
    public void readResponse() throws IOException{
        String userInput;
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

        System.out.println("Response from server:");
        while ((userInput = stdIn.readLine()) != null) {
            System.out.println(userInput);
        }
    }

    public void connectServer(){
    	
    	System.out.println("start asynctask");
    	Object Void = null;
    	new ClientAsyncTask().execute((String[]) Void);
    	
    }
    
    class ClientAsyncTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {
			System.out.println("Trying11 to connect");
			// TODO Auto-generated method stub
			SocketClient client = new SocketClient (hostname,port);
	    	
	        try {
	        	System.out.println("Trying to connect");
	            client.readResponse();

	        } catch (UnknownHostException e) {
	            System.err.println("Host unknown. Cannot establish connection");
	        } catch (IOException e) {
	            System.err.println("Cannot establish connection. Server may not be up."+e.getMessage());
	        }

			return null;
		}
    	
    }
    
}
