package com.detroitlabs.nerdnite.activity;

import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.adapter.PastEventsVPAdapter;
import com.detroitlabs.nerdnite.api.RestAPI;
import com.detroitlabs.nerdnite.api.RestCallback;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.view.PreviousEventsPagerIndicator;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

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
	@ViewById FrameLayout viewPagerCover;
	@ViewById PreviousEventsPagerIndicator pagerIndicator;

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
					setUpPagerIndicator();
					adapter = new PastEventsVPAdapter(getFragmentManager(), events);
					adapter.setViewPagerWidth(eventPager.getWidth());
					ViewGroup.LayoutParams coverParams = viewPagerCover.getLayoutParams();
					coverParams.width = (int)(eventPager.getWidth() * 0.2f);
					viewPagerCover.setLayoutParams(coverParams);
					eventPager.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					break;
			}
		}
		else{
			//todo: handle network error for past events
		}
	}

	private void setUpPagerIndicator(){
		pagerIndicator.setPageCount(events.length);
		eventPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
			@Override
			public void onPageScrolled(int i, float v, int i2){

			}

			@Override
			public void onPageSelected(int i){
				pagerIndicator.setPage(i);
			}

			@Override
			public void onPageScrollStateChanged(int i){

			}
		});
	}
}
