package com.tgforms.v1.pojo;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ClearanceData {

	String Name;
	ImageView SignCleartoOpen;
	Bitmap bitmapSignCleartoOpen;
	String base64SignCleartoOpen;
	String DateClose;
	ImageView SignCleartoClose;
	Bitmap bitmapSignCleartoClose;
	String base64SignCleartoClose;
	String DateOpen;
	Boolean HasValue;
	
	
	public ClearanceData(String name, ImageView signCleartoOpen,
			String dateClose, ImageView signCleartoClose, String dateOpen,Boolean hasValue) {
		
		super();
		
		Name = name;
		SignCleartoOpen = signCleartoOpen;
		DateClose = dateClose;
		SignCleartoClose = signCleartoClose;
		DateOpen = dateOpen;
		HasValue = hasValue;
		
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public ImageView getSignCleartoOpen() {
		return SignCleartoOpen;
	}
	public void setSignCleartoOpen(ImageView signCleartoOpen) {
		SignCleartoOpen = signCleartoOpen;
	}
	public String getDateClose() {
		return DateClose;
	}
	public void setDateClose(String dateClose) {
		DateClose = dateClose;
	}
	public ImageView getSignCleartoClose() {
		return SignCleartoClose;
	}
	public void setSignCleartoClose(ImageView signCleartoClose) {
		SignCleartoClose = signCleartoClose;
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

	public Bitmap getBitmapSignCleartoOpen() {
		return bitmapSignCleartoOpen;
	}

	public void setBitmapSignCleartoOpen(Bitmap bitmapSignCleartoOpen) {
		this.bitmapSignCleartoOpen = bitmapSignCleartoOpen;
	}

	public String getBase64SignCleartoOpen() {
		return base64SignCleartoOpen;
	}

	public void setBase64SignCleartoOpen(String base64SignCleartoOpen) {
		this.base64SignCleartoOpen = base64SignCleartoOpen;
	}

	public Bitmap getBitmapSignCleartoClose() {
		return bitmapSignCleartoClose;
	}

	public void setBitmapSignCleartoClose(Bitmap bitmapSignCleartoClose) {
		this.bitmapSignCleartoClose = bitmapSignCleartoClose;
	}

	public String getBase64SignCleartoClose() {
		return base64SignCleartoClose;
	}

	public void setBase64SignCleartoClose(String base64SignCleartoClose) {
		this.base64SignCleartoClose = base64SignCleartoClose;
	}
	
	
	
}
