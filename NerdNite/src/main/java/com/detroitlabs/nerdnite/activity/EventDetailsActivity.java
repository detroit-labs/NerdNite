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
