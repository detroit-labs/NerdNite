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
		if (observer != null)
			observer.overScrolledBy(deltaX, deltaY);

		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}

	@Override
	public void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY){
		Log.d("NN", "" + scrollX + ", " + scrollY);
		super.onOverScrolled(scrollX,scrollY,clampedX,clampedY);
	}
}
