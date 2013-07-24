package com.detroitlabs.nerdnite.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.adapter.LocationListAdapter;
import com.detroitlabs.nerdnite.api.RestAPI;
import com.detroitlabs.nerdnite.api.RestCallback;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;


@EActivity(R.layout.activity_location_list)
public class LocationListActivity extends BaseActivity implements RestCallback{

	public static final int RC_ALL_CITIES = 0x1;
	public static final int RC_EVENT = 0x2;

	@ViewById ListView locationList;
	@ViewById ProgressBar progress;
	
	@Bean RestAPI api;

	@AfterViews
	public void init(){
		locationList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id){
				City city = (City)adapterView.getItemAtPosition(pos);
				api.getCity(city.getId(), LocationListActivity.this, RC_EVENT);
				progress.setVisibility(View.VISIBLE);
			}
		});
	}
	
	@AfterViews
	public void getLocationList(){
		progress.setVisibility(View.VISIBLE);
		api.getAllCities(this, 1);
	}

	@Override
	public void handleResponse(Object response, Exception error, int requestCode){
		progress.setVisibility(View.GONE);
		if (response != null){
			handleValidResponse(response, requestCode);
		}
		else{
			//TODO: handle network errors for LocationListActivity
		}
	}

	private void handleValidResponse(Object response, int requestCode){
		switch (requestCode){
			case RC_ALL_CITIES:
				City[] cityListResponse = (City[])response;
				ArrayList<City> lst = new ArrayList<City>();
				Collections.addAll(lst, cityListResponse);
				LocationListAdapter lsa = new LocationListAdapter(this, 0, lst);
				locationList.setAdapter(lsa);
				lsa.notifyDataSetChanged();
				break;
			case RC_EVENT:
				City city = (City)response;
				Intent nextEventIntent = new Intent(LocationListActivity.this, NextEventActivity_.class);
				nextEventIntent.putExtra(City.EXTRA_CITY, city);
				startActivity(nextEventIntent);
				break;
		}
	}
}
