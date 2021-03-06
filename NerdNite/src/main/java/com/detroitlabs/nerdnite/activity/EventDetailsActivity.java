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

package com.detroitlabs.nerdnite.activity;

import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.detroitlabs.commons.display.DisplayUtils;
import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.data.Presenter;
import com.detroitlabs.nerdnite.view.SpeakerBioView;
import com.google.android.gms.maps.SupportMapFragment;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_event_details)
public class EventDetailsActivity extends FragmentActivity{

	@FragmentById SupportMapFragment mapFragment;

	@ViewById LinearLayout presenterContainer;

	@Extra Event event;

	@AfterViews
	public void addSpeakerBios(){
		int pd = DisplayUtils.dpToPixels(10);
		for (Presenter presenter : event.getPresenters()){
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			SpeakerBioView v = new SpeakerBioView(this, presenter);
			v.setPadding(pd,pd,pd,pd);
			presenterContainer.addView(v);
		}
	}
    
}
