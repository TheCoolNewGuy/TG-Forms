package com.tgforms.v1.form1;

import java.io.Serializable;
import java.util.HashMap;


public class FragmentTwoData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6872947505834752964L;
	String workComplete;
	String workCompleteSignBase64;
	
	HashMap<String, TransferableClearanceData> clearanceDataList;

	public FragmentTwoData(String workComplete, String workCompleteSignBase64,
			HashMap<String, TransferableClearanceData> clearanceDataList) {
		super();
		this.workComplete = workComplete;
		this.workCompleteSignBase64 = workCompleteSignBase64;
		this.clearanceDataList = clearanceDataList;
	}

	public String getWorkComplete() {
		return workComplete;
	}

	public void setWorkComplete(String workComplete) {
		this.workComplete = workComplete;
	}

	public String getWorkCompleteSignBase64() {
		return workCompleteSignBase64;
	}

	public void setWorkCompleteSignBase64(String workCompleteSignBase64) {
		this.workCompleteSignBase64 = workCompleteSignBase64;
	}

	public HashMap<String, TransferableClearanceData> getClearanceDataList() {
		return clearanceDataList;
	}

	public void setClearanceDataList(
			HashMap<String, TransferableClearanceData> clearanceDataList) {
		this.clearanceDataList = clearanceDataList;
	}
	
	

}
