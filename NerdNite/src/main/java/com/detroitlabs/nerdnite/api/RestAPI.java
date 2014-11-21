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

package com.detroitlabs.nerdnite.api;

import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import org.springframework.web.client.HttpClientErrorException;

@EBean
public class RestAPI{

	@RestService RestInterface ri;
	
	@Background
	public void getAllCities(final RestCallback callback, final int requestCode){
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

	@Background
	public void getCity(final int cityId, final RestCallback callback, final int requestCode){
		try{
			City response = ri.getCity(cityId);
			sendResponse(response, requestCode, callback);
		}
		catch(HttpClientErrorException e){
			sendError(e, requestCode, callback);
		}
		catch(Exception e){
			sendError(e, requestCode, callback);
		}
	}

	@Background
	public void getPreviousEvents(final int cityId, final RestCallback callback, final int requestCode){
		try{
			Event[] response = ri.getPastEvents(cityId);
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
	public void sendResponse(Object response, int requestCode, RestCallback callback){
		if (callback != null){
			callback.handleResponse(response, null, requestCode);
		}
	}

	@UiThread
	public void sendError(Exception error, int requestCode, RestCallback callback){
		if (callback != null){
			callback.handleResponse(null, error, requestCode);
		}
	}
}
