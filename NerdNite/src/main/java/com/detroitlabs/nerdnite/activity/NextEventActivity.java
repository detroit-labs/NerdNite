package com.detroitlabs.nerdnite.activity;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.data.Presenter;
import com.detroitlabs.nerdnite.ui.DateMaker;
import com.detroitlabs.nerdnite.ui.FontManager;
import com.detroitlabs.nerdnite.view.ImageHeaderScrollView;
import com.detroitlabs.nerdnite.volley.LruBitmapCache;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * Created by jsibbold on 6/21/13.
 */
@EActivity(R.layout.activity_next_event)
public class NextEventActivity extends BaseActivity implements ImageHeaderScrollView.OverScrollObserver{

	@Bean FontManager fm;

	@Extra City city;

	@ViewById TextView headerTitle;
	@ViewById TextView topicText;
	@ViewById TextView dateText;
	@ViewById TextView dateTextSuffix;
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

	@ViewById ImageView cityImage;
	@ViewById ImageView aboutImageHeader;

	private Event nextEvent;
	private DateMaker.DateString dateString = new DateMaker.DateString();

	private int headerWidth = -1, headerHeight = -1;
	private int stretchSize = -1;
	private float totalY = 0;
	private float stretchRatio = 1, deltaRatio = 1;
	private int stretchedWidth = 0, stretchedHeight = 0;

	private ImageLoader imageLoader;
	private ImageLoader.ImageListener imageListener;

	private static final int MAX_OVERSCROLL = 500;

	private ViewGroup.LayoutParams params;

	@AfterInject
	public void initData(){
		nextEvent = city.getNext_event();
		dateString = DateMaker.getDateString(nextEvent.getDate());
	}

	@AfterViews
	public void populateViews(){
		headerTitle.setText(city.getCity());
		topicText.setText(nextEvent.getTitle());
		date.setText(city.getYear_est());
		locationText.setText(nextEvent.getVenue_name());
		dateText.setText(dateString.dateString);
		dateTextSuffix.setText(dateString.dateSuffix);
		mainScrollView.setOverScrollObserver(this);
	}

	@AfterViews
	public void setFonts(){

	}


	@AfterInject
	public void initImageLoader(){
		imageLoader = new ImageLoader(Volley.newRequestQueue(this), LruBitmapCache.instance(3 * 1024 * 1024));
	}

	@AfterViews
	public void setSpeakerPics(){
		ArrayList<Presenter> presenters = nextEvent.getPresenters();
		Presenter p;
		for (int i = 0; i < presenters.size(); i++){
			p = presenters.get(i);
			ImageView v = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			v.setLayoutParams(params);
			imageLoader.get(p.getPic(), getListener(v));
			speakerPics.addView(v);
		}
	}

	@AfterViews
	public void setCityPic(){
		imageLoader.get(city.getBanner_image(), getListener(cityImage));
	}

	private ImageLoader.ImageListener getListener(ImageView v){
		return ImageLoader.getImageListener(v, 0, 0);
	}

	/*Header Image Overscroll*/
	@Override
	public void overScrolledBy(int deltaX, int deltaY){

		refreshParams();

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

			params.width = stretchedWidth;
			params.height = stretchedHeight;

			imageHeader.setLayoutParams(params);
			mainScrollView.invalidate();
		}

	}

	@Override
	public void onScrollingStopped(){
		refreshParams();
		PropertyValuesHolder w = PropertyValuesHolder.ofInt("width", imageHeader.getMeasuredWidth(), headerWidth);
		PropertyValuesHolder h = PropertyValuesHolder.ofInt("height", imageHeader.getMeasuredHeight(), headerHeight);
		ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(w, h);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
			@Override
			public void onAnimationUpdate(ValueAnimator animation){
				params.height = (Integer)animation.getAnimatedValue("height");
				params.width = (Integer)animation.getAnimatedValue("width");
				imageHeader.setLayoutParams(params);
			}
		});
		animator.setDuration(250).start();
		totalY = 0;
	}

	private void refreshParams(){
		if (params == null)
			params = imageHeader.getLayoutParams();

		if (headerHeight == -1){
			headerHeight = imageHeader.getHeight();
			headerWidth = imageHeader.getWidth();
		}
	}
	/*Header Image Overscroll*/
}
