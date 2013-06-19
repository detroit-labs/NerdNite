package com.detroitlabs.nerdnite.activity;

import android.widget.ListView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.adapter.LocationListAdapter;
import com.detroitlabs.nerdnite.api.RestAPI;
import com.detroitlabs.nerdnite.api.RestAPICallback;
import com.detroitlabs.nerdnite.data.City;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Collections;


@EActivity(R.layout.activity_location_list)
public class LocationListActivity extends BaseActivity implements RestAPICallback{

	@ViewById ListView locationList;
	
	@Bean RestAPI api;
	
	@AfterViews
	public void getLocationList(){
		api.getAllCities(this, 1);
	}

	@Override
	public void handleResponse(Object response, Exception error, int requestCode){
		if (response != null){
			City[] cityListResponse = (City[])response;
			ArrayList<City> lst = new ArrayList<City>();
			Collections.addAll(lst, cityListResponse);
			LocationListAdapter lsa = new LocationListAdapter(this, 0, lst);
			locationList.setAdapter(lsa);
			lsa.notifyDataSetChanged();
		}
		else{
			
		}
	}
}
