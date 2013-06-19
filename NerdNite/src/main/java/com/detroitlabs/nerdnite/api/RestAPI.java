package com.detroitlabs.nerdnite.api;

import com.detroitlabs.nerdnite.data.City;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EBean;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.rest.RestService;

import org.springframework.web.client.HttpClientErrorException;

@EBean
public class RestAPI{

	@RestService RestInterface ri;
	
	@Background
	public void getAllCities(RestAPICallback callback, int requestCode){
		try{
			City[] response = ri.getCities();
			sendResponse(response, requestCode, callback);
		}
		catch(HttpClientErrorException e){
			sendError(e, requestCode, callback);
		}
		catch(Exception e){
			sendError(e, requestCode, callback);
		}
	}
	
	
	@UiThread
	public void sendResponse(Object response, int requestCode, RestAPICallback callback){
		if (callback != null){
			callback.handleResponse(response, null, requestCode);
		}
	}
	
	@UiThread
	public void sendError(Exception error, int requestCode, RestAPICallback callback){
		if (callback != null){
			callback.handleResponse(null, error, requestCode);
		}
	}
}
