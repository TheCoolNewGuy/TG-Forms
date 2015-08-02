package com.tgforms.v1.ftpclient;

public class FtpSyncManager {

	private static FtpSyncManager   _instance;

    private FtpSyncManager()
    {

    }

    public static FtpSyncManager getInstance()
    {
        if (_instance == null)
        {
            _instance = new FtpSyncManager();
        }
        return _instance;
    }
      
}
