package com.tgforms.v1.form1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class viewPagerAdapter extends FragmentPagerAdapter {
	
	final int PAGE_COUNT = 2;
	final String [] title  = {"Page 1" , "Page 2"};
	
	public viewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
    	switch (position) {
        case 0:
        	FragementOne f = new FragementOne();
        	Bundle args = new Bundle();
            args.putString("TAG", "1");
            f.setArguments(args);
            return f;
        case 1:
        	FragmentTwo f2 = new FragmentTwo();
        	Bundle args2 = new Bundle();
            args2.putString("TAG", "2");
            f2.setArguments(args2);
            return f2;
        default:
            throw new IllegalArgumentException("The item position should be less or equal to:" + PAGE_COUNT);
      }
    }
    
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
