package com.tgforms.v1.ftpclient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.tgforms.v1.utils.Constants;
import com.tgforms.v1.utils.Utilities;

public class FtpSync {
	
	private static FtpSync   _instance;
	private String ip,user,pass;
	int port;
	Context context;

    private FtpSync()
    {

    }

    public static FtpSync getInstance()
    {
        if (_instance == null)
        {
            _instance = new FtpSync();
        }
        return _instance;
    }

	private class uploadFilesToFtpServer extends AsyncTask <Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			ArrayList fileNames = Utilities.getFileNamesFromRoot();
			FTPClient con = null;

	        try
	        {
	            con = new FTPClient();
	            con.connect(ip,port);
	            
	            if (con.login(user, pass))
	            {	
	            	con.enterLocalPassiveMode(); // important!
	                con.setFileType(FTP.BINARY_FILE_TYPE);
	                
	                for (int i=0;i<fileNames.size();i++) {
	                	
	                	String fileName = (String) fileNames.get(i);
	        			File sdCardRoot = Environment.getExternalStorageDirectory();
		        		String data = Constants.loto_path_saved+fileName;
		                FileInputStream in = new FileInputStream(new File(sdCardRoot,data));
		                boolean result = con.storeFile("/"+fileName, in);
		                in.close();
		                if (result) Log.v("upload result", "uploaded "+fileName);
		                moveFile(Constants.loto_path_saved,fileName,Constants.loto_path_uploaded);
		                
	        		}
	                
	                con.logout();
	                con.disconnect();
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }

			return null;
		}
		
	}
	
	private class downloadFilesFromFtpServer extends AsyncTask <Void, Void, Void>{

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			FTPClient con = null;

		    try
		    {
		        con = new FTPClient();
		        con.connect(ip,port);

		        if (con.login(user, pass))
		        {
		            con.enterLocalPassiveMode(); // important!
		            con.setFileType(FTP.BINARY_FILE_TYPE);
		            String data = Constants.rootPath+"configuration.json";
		            File sdCardRoot = Environment.getExternalStorageDirectory();
	        		
		            OutputStream out = new FileOutputStream(new File(sdCardRoot,data));
		            boolean result = con.retrieveFile("configuration.json", out);
		            out.close();
		            if (result) Log.v("download result", "succeeded");
		            con.logout();
		            con.disconnect();
		            //moveFile(Constants.rootPath,"configuration.json",Constants.loto_path_config_remote);
		        }
		    }
		    catch (Exception e)
		    {
		        Log.v("download result","failed");
		        e.printStackTrace();
		    }
		    
			return null;
		}
		
	}
	
	public void uploadFiles(Context con)
	{
		context = con;
		ip = Utilities.getFtpIp(con);
		port = Integer.parseInt(Utilities.getFtpPort(con));
		user = Utilities.getFtpUser(con);
		pass = Utilities.getFtpPass(con);
		
		Void[] Void = null;
		new uploadFilesToFtpServer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,(java.lang.Void[]) Void);

	}
	
	public void downloadConfigurationFile(Context con)
	{
		context = con;
		ip = Utilities.getFtpIp(con);
		port = Integer.parseInt(Utilities.getFtpPort(con));
		user = Utilities.getFtpUser(con);
		pass = Utilities.getFtpPass(con);
		
		Void[] Void = null;
		new downloadFilesFromFtpServer().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,(java.lang.Void[]) Void);

	}
	
	private void moveFile(String inputPath, String inputFile, String outputPath) {

	    InputStream in = null;
	    OutputStream out = null;
	    File sdCardRoot = Environment.getExternalStorageDirectory();
		
	    try {

	        //create output directory if it doesn't exist
	        File dir = new File (sdCardRoot,outputPath); 
	        if (!dir.exists())
	        {
	            dir.mkdirs();
	        }


	        in = new FileInputStream(new File(sdCardRoot,inputPath + inputFile));        
	        out = new FileOutputStream(new File(sdCardRoot,outputPath + inputFile));

	        byte[] buffer = new byte[1024];
	        int read;
	        while ((read = in.read(buffer)) != -1) {
	            out.write(buffer, 0, read);
	        }
	        in.close();
	        in = null;

	            // write the output file
	            out.flush();
	        out.close();
	        out = null;

	        // delete the original file
	        new File(sdCardRoot,inputPath + inputFile).delete();  


	    } 

	         catch (FileNotFoundException fnfe1) {
	        Log.e("tag", fnfe1.getMessage());
	    }
	          catch (Exception e) {
	        Log.e("tag", e.getMessage());
	    }

	}
}
