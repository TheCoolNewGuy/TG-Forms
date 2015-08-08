package com.tgforms.v1.form1;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentOneData implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6721588515979864850L;
	String FormDate;
	String Location;
	String Permit_manager;
	String Equipment; 
	String WorkDetail;
	String LotoType;
	
	ArrayList<String> lotoSteps;

	public FragmentOneData(String formDate, String location,
			String permit_manager, String equipment,String workDetail,String lotoType, ArrayList<String> lotoSteps) {
		super();
		FormDate = formDate;
		Location = location;
		Permit_manager = permit_manager;
		Equipment = equipment;
		WorkDetail = workDetail;
		LotoType = lotoType;
		this.lotoSteps = lotoSteps;
	}

	public String getWorkDetail() {
		return WorkDetail;
	}

	public void setWorkDetail(String workDetail) {
		WorkDetail = workDetail;
	}

	public String getLotoType() {
		return LotoType;
	}

	public void setLotoType(String lotoType) {
		LotoType = lotoType;
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

	public ArrayList<String> getLotoSteps() {
		return lotoSteps;
	}

	public void setLotoSteps(ArrayList<String> lotoSteps) {
		this.lotoSteps = lotoSteps;
	}
	
	
	
}
