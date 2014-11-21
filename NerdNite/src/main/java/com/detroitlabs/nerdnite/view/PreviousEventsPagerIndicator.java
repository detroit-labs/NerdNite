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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.detroitlabs.commons.display.DisplayUtils;
import com.detroitlabs.nerdnite.R;

import java.util.ArrayList;

/**
 * Created by jsibbold on 8/23/13.
 */
public class PreviousEventsPagerIndicator extends RelativeLayout{

	private int circleSize = DisplayUtils.dpToPixels(10);
	private int circleMargin = DisplayUtils.dpToPixels(10);

	private int currentPage = 0;
	private int pageCount = 0;

	private ArrayList<ImageView> dots = new ArrayList<ImageView>();

	private LinearLayout indicatorContainer;

	public PreviousEventsPagerIndicator(Context context){
		super(context);
		init(context);
	}

	public PreviousEventsPagerIndicator(Context context, AttributeSet attrs){
		super(context, attrs);
		init(context);
	}

	public PreviousEventsPagerIndicator(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context){
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_previous_events_pager_indicator, this);
		indicatorContainer = (LinearLayout)findViewById(R.id.indicatorContainer);
	}

	public PreviousEventsPagerIndicator setCircleSize(int dp){
		circleSize = DisplayUtils.dpToPixels(dp);
		return this;
	}

	public PreviousEventsPagerIndicator setCircleMargin(int dp){
		circleMargin = DisplayUtils.dpToPixels(dp);
		return this;
	}

	public PreviousEventsPagerIndicator setPageCount(int pageCount){
		this.pageCount = pageCount;
		addIndicatorsForPageCount(pageCount);
		return this;
	}

	private void addIndicatorsForPageCount(int pageCount){
		indicatorContainer.removeAllViews();
		dots.clear();
		for (int i = 0; i < pageCount; i++){
			ImageView v = new ImageView(getContext());
			v.setImageResource(R.drawable.shape_pager_circle_yellow);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(circleSize, circleSize);
			if (pageCount > 1 && i < pageCount - 1){
				params.setMargins(0, 0, circleMargin, 0);
			}
			v.setLayoutParams(params);
			indicatorContainer.addView(v);
			dots.add(v);
		}
		setPage(currentPage);
	}

	public void setPage(int page){
		if (dots.size() > 0){
			dots.get(currentPage).setImageResource(R.drawable.shape_pager_circle_yellow);
			dots.get(page).setImageResource(R.drawable.shape_pager_circle_white);
			currentPage = page;
		}
	}
}
