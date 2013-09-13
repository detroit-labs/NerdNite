package com.detroitlabs.nerdnite.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.ui.DateMaker;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Created by jsibbold on 8/2/13.
 */
@EFragment(R.layout.fragment_previous_event)
public class PastEventFragment extends Fragment{

	private Event event;
	private boolean isLastFragment = false;
	private int rightPadding = 0;

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
			v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), rightPadding,v.getPaddingBottom());
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
