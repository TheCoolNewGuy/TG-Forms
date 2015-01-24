package com.tgforms.v1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tgforms.v1.form1.FragementOne;
import com.tgforms.v1.form1.FragmentTwo;

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
            return new FragementOne();
        case 1:
            return new FragmentTwo();
        default:
            throw new IllegalArgumentException("The item position should be less or equal to:" + PAGE_COUNT);
      }
    }
    
    public CharSequence getPageTitle(int position) {
        return title[position];
    }

}
