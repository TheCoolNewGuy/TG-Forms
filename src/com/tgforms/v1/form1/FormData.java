package com.tgforms.v1.form1;

import java.io.Serializable;

public class FormData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7464044690396822466L;
	FragmentOneData fragmentOneData;
	FragmentTwoData fragmentTwoData;
	
	public FormData(FragmentOneData fragmentOneData,
			FragmentTwoData fragmentTwoData) {
		super();
		this.fragmentOneData = fragmentOneData;
		this.fragmentTwoData = fragmentTwoData;
	}

	public FragmentOneData getFragmentOneData() {
		return fragmentOneData;
	}

	public void setFragmentOneData(FragmentOneData fragmentOneData) {
		this.fragmentOneData = fragmentOneData;
	}

	public FragmentTwoData getFragmentTwoData() {
		return fragmentTwoData;
	}

	public void setFragmentTwoData(FragmentTwoData fragmentTwoData) {
		this.fragmentTwoData = fragmentTwoData;
	}
	
	
}
