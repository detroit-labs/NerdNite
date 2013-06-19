package com.detroitlabs.nerdnite.api;

public interface RestAPICallback{

	public void handleResponse(Object response, Exception error, int requestCode);
}
