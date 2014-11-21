/*
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Detroit Labs, LLC.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

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
