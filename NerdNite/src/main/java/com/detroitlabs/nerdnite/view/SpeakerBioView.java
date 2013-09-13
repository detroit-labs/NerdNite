package com.detroitlabs.nerdnite.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.Presenter;
import com.squareup.picasso.Picasso;

/**
 * Created by jsibbold on 9/6/13.
 */
public class SpeakerBioView extends RelativeLayout{

	public ImageView speakerImage;
	public TextView speakerName, talkDetails, aboutSpeakerTitle, aboutSpeakerDetails;

	private Presenter presenter;

	public SpeakerBioView(Context context, Presenter presenter){
		this(context);
		setPresenter(presenter);
	}

	public SpeakerBioView(Context context){
		super(context);
		init(context);
	}

	public SpeakerBioView(Context context, AttributeSet attrs){
		super(context, attrs);
		init(context);
	}

	public SpeakerBioView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_speaker_bio, this);
		speakerImage = (ImageView)findViewById(R.id.speakerImage);
		speakerName = (TextView)findViewById(R.id.speakerName);
		talkDetails = (TextView)findViewById(R.id.talkDetails);
		aboutSpeakerTitle = (TextView)findViewById(R.id.aboutSpeakerTitle);
		aboutSpeakerDetails = (TextView)findViewById(R.id.aboutSpeakerDetails);
	}

	public void setPresenter(Presenter presenter){
		this.presenter = presenter;
		Picasso.with(getContext()).load(presenter.getPic()).into(speakerImage);
		speakerName.setText(presenter.getName());
		talkDetails.setText(presenter.getBlurb());
		aboutSpeakerTitle.setText(presenter.getName());
		aboutSpeakerDetails.setText(presenter.getBio());
	}
}
