package com.detroitlabs.nerdnite.api;

import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;

import org.springframework.http.converter.json.GsonHttpMessageConverter;

@Rest(rootUrl = "http://nn-server-dev.herokuapp.com/", converters = { GsonHttpMessageConverter.class})
public interface RestInterface{
	@Get("/cities")
	City[] getCities();

	@Get("/cities/{eventID}")
	City getCity(int eventID);

	@Get("/past-events/{cityId}")
	Event[] getPastEvents(int cityId);
}
