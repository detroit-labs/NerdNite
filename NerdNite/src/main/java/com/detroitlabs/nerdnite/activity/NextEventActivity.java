package com.detroitlabs.nerdnite.activity;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.ui.FontManager;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Created by jsibbold on 6/21/13.
 */
@EActivity(R.layout.activity_next_event)
public class NextEventActivity extends BaseActivity{

	@Bean FontManager fm;

	@Extra City city;

	@ViewById TextView headerTitle;
	@ViewById TextView topicText;
	@ViewById TextView dateText;
	@ViewById TextView locationText;
	@ViewById TextView est;
	@ViewById TextView date;
	@ViewById TextView aboutRectangle;

	@ViewById ImageButton twitterButton;
	@ViewById ImageButton facebookButton;

	@ViewById LinearLayout previousEventsButton;
	@ViewById LinearLayout upcomingEventsButton;
	@ViewById LinearLayout speakerPics;

	@ViewById RelativeLayout learnMore;

	@ViewById ImageView titleImage;
	@ViewById ImageView aboutImageHeader;

	@AfterViews
	public void populateViews(){
		headerTitle.setText(city.getCity());
		est.setText(city.getYear_est());


	}
}
