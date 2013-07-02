package com.detroitlabs.nerdnite.api;

import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://nn-server-dev.herokuapp.com/", converters = { GsonHttpMessageConverter.class})
public interface RestInterface{
	@Get("/cities")
	City[] getCities();

	@Get("/cities/{eventID}")
	Event getEvent(int eventID);
}
