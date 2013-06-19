package com.detroitlabs.nerdnite.activity;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity{

	protected void stoast(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	
	protected void ltoast(String message){
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
