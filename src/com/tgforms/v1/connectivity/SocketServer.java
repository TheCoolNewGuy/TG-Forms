package com.tgforms.v1.connectivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
    
    
    class serverAsyncTask extends AsyncTask<String, Void, Void>{

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			System.out.println("Server started. Listening to the port 4444");
			try {
				serverSocket = new ServerSocket(4445); // Server socket
				System.out.println("Server started. Listening to the port 4445");
				
			} catch (IOException e) {
				System.out.println("Could not listen on port: 4444");
			}
	 
			
	 
			while (true) {
				try {
	 
					clientSocket = serverSocket.accept(); // accept the client connection
					ObjectInputStream in  = new ObjectInputStream(clientSocket.getInputStream());
                                        com.tgforms.v1.pojo.TransferableObject messageObject = (com.tgforms.v1.pojo.TransferableObject) in.readObject();
                                        String messageText = messageObject.getLocation();
                                        System.out.println(messageText);
					clientSocket.close();
	 
				} catch (IOException ex) {
					System.out.println("Problem in message reading");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
    	
    }
        
}