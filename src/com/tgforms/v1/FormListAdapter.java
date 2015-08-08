package com.tgforms.v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tgforms.v1.R;

public class FormListAdapter extends BaseAdapter{
	
	Context conext;
	LayoutInflater inflater;
	TextView formName;
	
	public FormListAdapter(Context context) {
        this.conext = context;
        inflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		 
		if(convertView == null) 
		{
	            convertView = inflater.inflate(R.layout.formlistrow, null);
	            formName = (TextView) convertView.findViewById(R.id.formName);
	            formName.setText("LOTO Form");
	      
	    } 
		
	    return convertView;
	}

}
