package com.tgforms.v1.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TransferableObject implements Serializable{

	String FormDate="Date : ";
	String Location ="Location : ";
	String Permit_manager="Permit Manager : ";
	String Equipment ="Equipment : ";
	String workComplete="Work Complete(Print) : ";
	
	ArrayList<String> lotoSteps = new ArrayList<String>();
	
	HashMap<String, TransferableClearanceData> clearanceDataList;
	
	String workCompleteSignBase64;

	public TransferableObject(String formDate, String location,
			String permit_manager, String equipment, String workComplete,
			ArrayList<String> lotoSteps,
			HashMap<String, TransferableClearanceData> clearanceDataList,
			String workCompleteSignBase64) {
		super();
		FormDate = formDate;
		Location = location;
		Permit_manager = permit_manager;
		Equipment = equipment;
		this.workComplete = workComplete;
		this.lotoSteps = lotoSteps;
		this.clearanceDataList = clearanceDataList;
		this.workCompleteSignBase64 = workCompleteSignBase64;
	}

	public String getFormDate() {
		return FormDate;
	}

	public void setFormDate(String formDate) {
		FormDate = formDate;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getPermit_manager() {
		return Permit_manager;
	}

	public void setPermit_manager(String permit_manager) {
		Permit_manager = permit_manager;
	}

	public String getEquipment() {
		return Equipment;
	}

	public void setEquipment(String equipment) {
		Equipment = equipment;
	}

	public String getWorkComplete() {
		return workComplete;
	}

	public void setWorkComplete(String workComplete) {
		this.workComplete = workComplete;
	}

	public ArrayList<String> getLotoSteps() {
		return lotoSteps;
	}

	public void setLotoSteps(ArrayList<String> lotoSteps) {
		this.lotoSteps = lotoSteps;
	}

	public HashMap<String, TransferableClearanceData> getClearanceDataList() {
		return clearanceDataList;
	}

	public void setClearanceDataList(
			HashMap<String, TransferableClearanceData> clearanceDataList) {
		this.clearanceDataList = clearanceDataList;
	}

	public String getWorkCompleteSignBase64() {
		return workCompleteSignBase64;
	}

	public void setWorkCompleteSignBase64(String workCompleteSignBase64) {
		this.workCompleteSignBase64 = workCompleteSignBase64;
	}
	
	
}
