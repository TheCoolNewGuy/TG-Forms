package com.tgforms.v1.pojo;

import java.io.Serializable;

public class TransferableClearanceData implements Serializable{

	String Name;
	String base64SignCleartoOpen;
	String DateClose;
	String base64SignCleartoClose;
	String DateOpen;
	Boolean HasValue;
	public TransferableClearanceData(String name, String base64SignCleartoOpen,
			String dateClose, String base64SignCleartoClose, String dateOpen,
			Boolean hasValue) {
		super();
		Name = name;
		this.base64SignCleartoOpen = base64SignCleartoOpen;
		DateClose = dateClose;
		this.base64SignCleartoClose = base64SignCleartoClose;
		DateOpen = dateOpen;
		HasValue = hasValue;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBase64SignCleartoOpen() {
		return base64SignCleartoOpen;
	}
	public void setBase64SignCleartoOpen(String base64SignCleartoOpen) {
		this.base64SignCleartoOpen = base64SignCleartoOpen;
	}
	public String getDateClose() {
		return DateClose;
	}
	public void setDateClose(String dateClose) {
		DateClose = dateClose;
	}
	public String getBase64SignCleartoClose() {
		return base64SignCleartoClose;
	}
	public void setBase64SignCleartoClose(String base64SignCleartoClose) {
		this.base64SignCleartoClose = base64SignCleartoClose;
	}
	public String getDateOpen() {
		return DateOpen;
	}
	public void setDateOpen(String dateOpen) {
		DateOpen = dateOpen;
	}
	public Boolean getHasValue() {
		return HasValue;
	}
	public void setHasValue(Boolean hasValue) {
		HasValue = hasValue;
	}
	
	
}
