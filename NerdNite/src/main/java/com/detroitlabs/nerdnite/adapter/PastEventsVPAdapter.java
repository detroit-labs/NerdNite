/*
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Detroit Labs, LLC.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
