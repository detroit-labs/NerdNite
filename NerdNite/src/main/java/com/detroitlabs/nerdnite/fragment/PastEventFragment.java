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

package com.detroitlabs.nerdnite.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.ui.DateMaker;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * Created by jsibbold on 8/2/13.
 */
@EFragment(R.layout.fragment_previous_event)
public class PastEventFragment extends Fragment{

	private Event event;
	private boolean isLastFragment = false;
	private int     rightPadding   = 0;

	@ViewById TextView topicText, dateText, dateSuffix, locationText, description, voteUpText, viewPicsText;

	@ViewById RelativeLayout voteUpButton, viewPicsButton;

	public void setEvent(final Event event){
		this.event = event;
	}

	public void setIsLastFragment(boolean isLastFragment){
		this.isLastFragment = isLastFragment;
	}

	public void setRightPadding(int rightPadding){
		this.rightPadding = rightPadding;
	}

	@Override
	public void onViewCreated(View v, Bundle savedInstanceState){
		if (isLastFragment){
			v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), rightPadding, v.getPaddingBottom());
		}
	}


	@AfterViews
	public void populate(){
		if (event != null){
			DateMaker.DateString dateString = DateMaker.getDateString(event.getDate());
			topicText.setText(event.getTitle());
			dateText.setText(dateString.dateString);
			dateSuffix.setText(dateString.dateSuffix);
			locationText.setText("at " + event.getVenue_name());
			description.setText(event.getDescription());
		}
	}
}
