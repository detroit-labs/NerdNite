package com.detroitlabs.nerdnite.activity;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.ui.FontManager;
import com.detroitlabs.nerdnite.view.ImageHeaderScrollView;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

/**
 * Created by jsibbold on 6/21/13.
 */
@EActivity(R.layout.activity_next_event)
public class NextEventActivity extends BaseActivity implements ImageHeaderScrollView.OverScrollObserver{

	@Bean FontManager fm;

	@Extra Event event;

	@ViewById TextView headerTitle;
	@ViewById TextView topicText;
	@ViewById TextView dateText;
	@ViewById TextView locationText;
	@ViewById TextView est;
	@ViewById TextView date;
	@ViewById TextView aboutRectangle;

	@ViewById ImageHeaderScrollView mainScrollView;

	@ViewById ImageButton twitterButton;
	@ViewById ImageButton facebookButton;

	@ViewById LinearLayout previousEventsButton;
	@ViewById LinearLayout upcomingEventsButton;
	@ViewById LinearLayout speakerPics;

	@ViewById RelativeLayout learnMore;
	@ViewById RelativeLayout imageHeader;

	@ViewById ImageView titleImage;
	@ViewById ImageView aboutImageHeader;

	private int headerWidth = -1, headerHeight = -1;
	private int stretchSize = -1;
	private float totalY = 0;
	private float stretchRatio = 1, deltaRatio = 1;
	private int stretchedWidth = 0, stretchedHeight = 0;

	private static final int MAX_OVERSCROLL = 500;

	private ViewGroup.LayoutParams params;

	@AfterViews
	public void populateViews(){
		headerTitle.setText(event.getCity());
		topicText.setText("Topic Text: ADD TO API");
		est.setText(event.getYear_est());
		date.setText("Date Text: ADD TO API");
		locationText.setText("Location Text: ADD TO API");

		mainScrollView.setOverScrollObserver(this);
	}

	@AfterViews
	public void setFonts(){

	}

	@Override
	public void overScrolledBy(int deltaX, int deltaY){

		if (params == null)
			params = imageHeader.getLayoutParams();

		if (headerHeight == -1){
			headerHeight = imageHeader.getHeight();
			headerWidth = imageHeader.getWidth();
		}

		if (deltaY < 0){
			deltaY = -deltaY;

			stretchRatio = ((totalY + (float)deltaY) / (float)MAX_OVERSCROLL);
			deltaRatio = 1 - stretchRatio;
			deltaY *= deltaRatio;

			totalY += deltaY;

			stretchSize = (int)totalY;

			Log.d("NN", "width:" + imageHeader.getWidth() + ", height:" + imageHeader.getHeight() + ", ratio:" + stretchRatio + ", deltaY:" + deltaY + ", totalY:" + totalY + ", stretchSize:" + stretchSize);

			if (stretchSize < MAX_OVERSCROLL){
				stretchedHeight = headerHeight + stretchSize;
				stretchedWidth = headerWidth + stretchSize;
			}
			else{
				stretchedHeight = headerHeight + MAX_OVERSCROLL;
				stretchedWidth = headerWidth + MAX_OVERSCROLL;
			}

			params.width = stretchedWidth;//(int)(2 * stretchRatio);
			params.height = stretchedHeight;//(int)(2 * stretchRatio);

			imageHeader.setLayoutParams(params);
		}

	}

	@Override
	public void onScrollingStopped(){
		if (params == null){
			params = imageHeader.getLayoutParams();
		}
		params.height = headerHeight;
		params.width = headerWidth;
		imageHeader.setLayoutParams(params);
		totalY = 0;
	}
}
