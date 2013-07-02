package com.detroitlabs.nerdnite.api;

public interface RestCallback{

	public void handleResponse(Object response, Exception error, int requestCode);
}
