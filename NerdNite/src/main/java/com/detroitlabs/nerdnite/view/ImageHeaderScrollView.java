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
import android.util.Log;
import android.view.MotionEvent;

import com.detroitlabs.commons.views.ObservableScrollView;

/**
 * Created by jsibbold on 7/11/13.
 */
public class ImageHeaderScrollView extends ObservableScrollView{

	private int deltaX = 0, deltaY = 0;

	public interface OverScrollObserver{
		public void overScrolledBy(int deltaX, int deltaY);

		public void onScrollingStopped();
	}

	private OverScrollObserver observer;

	public ImageHeaderScrollView(Context context){
		super(context);
	}

	public ImageHeaderScrollView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public void setOverScrollObserver(final OverScrollObserver observer){
		this.observer = observer;
	}

	@Override
	public boolean onTouchEvent(MotionEvent e){
		switch(e.getAction()){
			case MotionEvent.ACTION_UP:
				if (observer != null)
					observer.onScrollingStopped();
		}

		return super.onTouchEvent(e);
	}

	@Override
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent){
		this.deltaX = deltaX;
		this.deltaY = deltaY;
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}

	@Override
	public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY){
		Log.d("NN", "" + scrollX + ", " + scrollY);

		if (observer != null && scrollY == 0)
			observer.overScrolledBy(deltaX, deltaY);

		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
	}
}
