package com.tgforms.v1.connectivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.tgforms.v1.form1.StoreData;
import com.tgforms.v1.pojo.TransferableObject;

import android.os.AsyncTask;

public class SocketServer {
    
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	private static InputStreamReader inputStreamReader;
	private static BufferedReader bufferedReader;
	private static String message;
    private int port;
    
    public SocketServer(int port) {
        this.port = port;
    }
    
    public void start() throws IOException {
    	Object Void = null;
    	new serverAsyncTask().execute((String[]) Void);
        
    }
    
    
    class serverAsyncTask extends AsyncTask<String, Void, TransferableObject>{

		@Override
		protected TransferableObject doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			
			try {
				serverSocket = new ServerSocket(4444); // Server socket
				
				
			} catch (IOException e) {
				System.out.println("Could not listen on port: 4444");
			}
	 
			
	 
			while (true) {
				try {
					System.out.println( StoreData.editLocation);
					System.out.println( StoreData.editPermitManager);
					
					clientSocket = serverSocket.accept(); // accept the client connection
					ObjectInputStream in  = new ObjectInputStream(clientSocket.getInputStream());
                                        com.tgforms.v1.pojo.TransferableObject messageObject = (com.tgforms.v1.pojo.TransferableObject) in.readObject();
                                        
					clientSocket.close();
					return messageObject;
	 
				} catch (IOException ex) {
					System.out.println("Problem in message reading");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		@Override
		protected void onPostExecute(TransferableObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			updateView(result);
		}
    	
    }
    private void updateView(TransferableObject messageObject ){
    	String messageText = messageObject.getLocation();
        System.out.println(messageText);
        
        StoreData.editLocation.setText(messageText);
        StoreData.editPermitManager.setText(messageObject.getPermit_manager());
    }
        
}