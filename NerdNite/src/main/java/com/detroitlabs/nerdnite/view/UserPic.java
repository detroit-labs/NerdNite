package com.detroitlabs.nerdnite.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;

/**
 * Created by jsibbold on 7/29/13.
 */
public class UserPic extends LinearLayout{


	LayoutInflater inflater;

	public UserPic(Context context){
		super(context);
		init(context);
	}

	public UserPic(Context context, AttributeSet attrs){
		super(context, attrs);
		init(context);
	}

	private void init(Context context){
		inflater = getInflater(context);
		inflater.inflate(R.layout.view_user_pic, this);
		setOrientation(VERTICAL);
	}

	private LayoutInflater getInflater(Context context){
		return (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public RoundedImageView getPicView(){
		return (RoundedImageView)findViewById(R.id.pic);
	}

	public TextView getNameView(){
		return (TextView)findViewById(R.id.name);
	}

	public void setName(String name){
		getNameView().setText(name);
	}
}
