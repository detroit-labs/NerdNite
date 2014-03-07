package com.detroitlabs.nerdnite.activity;

import android.content.Intent;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Timer;
import java.util.TimerTask;



@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity{

	@ViewById TextView splashTitle;

	@AfterViews
	public void runTimer(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				startActivity(new Intent(SplashActivity.this, LocationListActivity_.class));
				finish();
			}
		}, 3000);
	}
}
