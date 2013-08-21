package com.detroitlabs.nerdnite.activity;

import android.support.v4.view.ViewPager;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.adapter.PastEventsVPAdapter;
import com.detroitlabs.nerdnite.api.RestAPI;
import com.detroitlabs.nerdnite.api.RestCallback;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Created by jsibbold on 8/2/13.
 */
@EActivity(R.layout.activity_past_events)
public class PastEventsActivity extends BaseActivity implements RestCallback{

	public static final int RC_PREVIOUS_EVENTS = 0x5;

	@Extra City city;

	@Bean RestAPI api;

	private Event[] events;
	private PastEventsVPAdapter adapter;

	@ViewById ViewPager eventPager;

	@AfterViews
	public void getPreviousEvents(){
		api.getPreviousEvents(city.getId(), this, RC_PREVIOUS_EVENTS);
	}

	@Override
	public void handleResponse(Object response, Exception error, int requestCode){
		if (response != null){
			switch(requestCode){
				case RC_PREVIOUS_EVENTS:
					events = (Event[])response;
					adapter = new PastEventsVPAdapter(getFragmentManager(), events);
					adapter.setViewPagerWidth(eventPager.getWidth());
					eventPager.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					break;
			}
		}
		else{
			//handle error
		}
	}
}
