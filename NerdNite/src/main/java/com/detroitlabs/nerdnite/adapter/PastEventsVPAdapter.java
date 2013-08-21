package com.detroitlabs.nerdnite.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.fragment.PastEventFragment;
import com.detroitlabs.nerdnite.fragment.PastEventFragment_;

/**
 * Created by jsibbold on 8/2/13.
 */
public class PastEventsVPAdapter extends FragmentStatePagerAdapter{

	private Event[] events;
	private int viewPagerWidth = 0;

	public PastEventsVPAdapter(FragmentManager fm, Event[] events){
		super(fm);
		this.events = events;
	}

	@Override
	public Fragment getItem(int pos){
		PastEventFragment frag = new PastEventFragment_();
		frag.setEvent(events[pos]);

		if (pos == events.length - 1){
			frag.setIsLastFragment(true);
			frag.setRightPadding((int)(viewPagerWidth * 0.2f + 0.5f));
		}

		return frag;
	}

	public void setViewPagerWidth(int w){
		viewPagerWidth = w;
	}

	@Override
	public int getCount(){
		return events.length;
	}

	@Override
	public float getPageWidth(int position){
		if (position != getCount() - 1)
			return 0.8f;
		else
			return 1f;
	}
}
