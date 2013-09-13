package com.detroitlabs.nerdnite.activity;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.detroitlabs.commons.display.DisplayUtils;
import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Boss;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.data.Event;
import com.detroitlabs.nerdnite.data.Presenter;
import com.detroitlabs.nerdnite.data.PreviewImage;
import com.detroitlabs.nerdnite.ui.DateMaker;
import com.detroitlabs.nerdnite.ui.FontManager;
import com.detroitlabs.nerdnite.ui.TextViewAction;
import com.detroitlabs.nerdnite.view.ImageHeaderScrollView;
import com.detroitlabs.nerdnite.view.UserPic;
import com.googlecode.androidannotations.annotations.AfterInject;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.Extra;
import com.googlecode.androidannotations.annotations.ViewById;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by jsibbold on 6/21/13.
 */
@EActivity(R.layout.activity_main_event)
public class MainEventActivity extends BaseActivity implements ImageHeaderScrollView.OverScrollObserver{

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
	@ViewById TextView aboutDescription;
	@ViewById TextView nerdnite;
	@ViewById TextView previous;
	@ViewById TextView upcoming;
	@ViewById TextView events1;
	@ViewById TextView events2;
	@ViewById TextView locationAt;
	@ViewById TextView learnMoreText;
	@ViewById TextView nextEventTab;
	@ViewById TextView meetTheBosses;

	@ViewById ImageHeaderScrollView mainScrollView;

	@ViewById ImageButton twitterButton;
	@ViewById ImageButton facebookButton;

	@ViewById LinearLayout previousEventsButton;
	@ViewById LinearLayout upcomingEventsButton;
	@ViewById LinearLayout speakerPics;
	@ViewById LinearLayout bossPics;
	@ViewById LinearLayout aboutImageHeader;

	@ViewById RelativeLayout learnMore;
	@ViewById RelativeLayout imageHeader;

	@ViewById ImageView cityImage;

	private Event nextEvent;
	private DateMaker.DateString dateString = new DateMaker.DateString();

	private int headerWidth = -1, headerHeight = -1;
	private int stretchSize = -1;
	private float totalY = 0;
	private float stretchRatio = 1, deltaRatio = 1;
	private int stretchedWidth = 0, stretchedHeight = 0;

	private static final int MAX_OVERSCROLL = 500;

	private ViewGroup.LayoutParams params;

	private MediaPlayer mpFireball, mpMushroom, mpReset;

	@AfterInject
	public void initData(){
		nextEvent = city.getNext_event();
		dateString = DateMaker.getDateString(nextEvent.getDate());
	}

	@Override
	public void onResume(){
		super.onResume();
		mpFireball = MediaPlayer.create(MainEventActivity.this, R.raw.smb_fireball);
		mpReset = MediaPlayer.create(MainEventActivity.this, R.raw.smb_powerup_appears);
		mpMushroom = MediaPlayer.create(MainEventActivity.this, R.raw.smb_powerup);
	}

	@Override
	public void onPause(){
		super.onPause();
		mpFireball.release();
		mpReset.release();
		mpMushroom.release();
	}

	@AfterViews
	public void populateViews(){
		headerTitle.setText(city.getCity());
		topicText.setText(nextEvent.getTitle());
		date.setText(city.getYear_est());
		locationText.setText(nextEvent.getVenue_name());
		dateText.setText(dateString.dateString);
		dateTextSuffix.setText(dateString.dateSuffix);
		aboutDescription.setText(nextEvent.getDescription());
		mainScrollView.setOverScrollObserver(this);
	}

	@AfterViews
	public void setFonts(){
		fm.setFont(est, FontManager.NerdNiteFont.FONT_COURIER_NEW_ITALIC);
		fm.setFont(date, FontManager.NerdNiteFont.FONT_COURIER_NEW_ITALIC);
		fm.setFont(nerdnite, FontManager.NerdNiteFont.FONT_COURIER_NEW);
		fm.setFont(new TextView[]{headerTitle, learnMoreText, previous, upcoming, events1, events2, topicText, dateText,
																 dateTextSuffix, locationText, locationAt, aboutRectangle, nextEventTab}, FontManager.NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
		fm.setFont(aboutDescription, FontManager.NerdNiteFont.FONT_PROXIMA_NOVA_REGULAR);
		fm.setFont(meetTheBosses, FontManager.NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
	}

	@AfterViews
	public void setSpeakerPics(){
		ArrayList<Presenter> presenters = nextEvent.getPresenters();
		Presenter p;
		int px = DisplayUtils.dpToPixels(50);
		int mg = DisplayUtils.dpToPixels(10);
		for (int i = 0; i < presenters.size(); i++){
			p = presenters.get(i);
			UserPic v = new UserPic(this);
			v.getPicView().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.setMargins(mg, 0, mg, 0);
			params.weight = 1;
			v.setLayoutParams(params);
			v.getPicView().setDimens(px, px);
			v.getNameView().setVisibility(View.GONE);
			Picasso.with(this).load(p.getPic()).into((Target)v.getPicView());
			speakerPics.addView(v);
		}
	}

	@AfterViews
	public void setBossPics(){
		ArrayList<Boss> bosses = city.getBosses();
		bossPics.setWeightSum(bosses.size());
		int px = DisplayUtils.dpToPixels(75);
		for (Boss boss : bosses){
			UserPic v = new UserPic(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
			params.weight = 1;
			v.setLayoutParams(params);
			v.getPicView().setDimens(px, px);
			v.getPicView().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
			Picasso.with(this).load(boss.getPic()).into((Target) v.getPicView());
			v.setName(boss.getName());
			fm.setFont(v.getNameView(), FontManager.NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
			bossPics.addView(v);
		}
	}

	@AfterViews
	public void setAboutPics(){
		ArrayList<PreviewImage> images = city.getPreview_images();
		aboutImageHeader.setWeightSum(images.size());
		for (PreviewImage image : images){
			ImageView v = new ImageView(this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
			params.weight = 1;
			v.setLayoutParams(params);
			v.setScaleType(ImageView.ScaleType.CENTER_CROP);
			Picasso.with(this).load(image.getLink()).into(v);
			aboutImageHeader.addView(v);
		}
	}

	@AfterViews
	public void setCityPic(){
		Picasso.with(this).load(city.getBanner_image()).into(cityImage);
	}

	@Click(R.id.previousEventsButton)
	public void onPastEventsClicked(View v){
		Intent intent = new Intent(this, PastEventsActivity_.class);
		intent.putExtra(City.EXTRA_CITY, city);
		startActivity(intent);
	}

	@Click(R.id.learnMore)
	public void onLearnMoreClick(View v){
		Intent intent = new Intent(this, EventDetailsActivity_.class);
		intent.putExtra(Event.EXTRA_EVENT, city.getNext_event());
		startActivity(intent);
	}



	/*************************/
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
	/*************************/


	/*egg 1*/
	private int tapCount = 0;
	private long lastTap = 0;
	private int startOffset = 0;
	private boolean inRetroMode = false;

	private static final int TAP_THRESHOLD_TIME = 1500;
	private static final int TAP_REQUIREMENT = 5;

	@AfterViews
	public void prepareEgg1(){
		nerdnite.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				if (event.getAction() == MotionEvent.ACTION_DOWN){
					if (System.currentTimeMillis() - lastTap > TAP_THRESHOLD_TIME){
						resetCount();
					}

					lastTap = System.currentTimeMillis();
					tapCount++;

					if (tapCount == TAP_REQUIREMENT){
						/*set retro mode!*/
						if (!inRetroMode){
							changeTextViews(v.getRootView(), new TextViewAction(){
								@Override
								public void changeTextView(TextView tv){
									fm.setFont(tv, FontManager.NerdNiteFont.FONT_KONG);
									tv.setTextScaleX(0.7f);
									if (tv.getAnimation() == null){
										Log.e("NN", "SETTING ANIMATION");
										ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, .5f, .5f);
										scaleAnimation.setDuration(200);
										scaleAnimation.setStartOffset(startOffset);
										scaleAnimation.setInterpolator(new OvershootInterpolator(1.5f));
										startOffset += 20;
										tv.setAnimation(scaleAnimation);
									}
									tv.animate();
								}
							});

							Toast.makeText(MainEventActivity.this, "NERD MODE ACTIVATED", Toast.LENGTH_SHORT).show();
							mpMushroom.start();
							inRetroMode = true;
							resetCount();
						}
						/*reset to normal*/
						else{
							changeTextViews(v.getRootView(), new TextViewAction(){
								@Override
								public void changeTextView(TextView tv){
									fm.setFont(tv, (String)tv.getTag());
									tv.setTextScaleX(1f);
									startOffset = 0;
								}
							});
							inRetroMode = false;
							resetCount();
							mpFireball.start();
						}
					}
					/*else{
						mpFireball.start();
					}*/
				}
				return false;
			}
		});
	}

	private void resetCount(){
		tapCount = 0;
	}

	private void changeTextViews(View v, TextViewAction action){
		if (v instanceof ViewGroup){
			for (int i = 0; i < ((ViewGroup) v).getChildCount(); i++){
				changeTextViews(((ViewGroup) v).getChildAt(i), action);
			}
		}
		else if (v instanceof TextView){
				action.changeTextView((TextView)v);
		}
	}
	/*egg 1*/
}