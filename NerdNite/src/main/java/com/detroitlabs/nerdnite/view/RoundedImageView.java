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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Target;

/**
 * Created by jsibbold on 7/29/13.
 */
public class RoundedImageView extends ImageView implements Target{

	private Paint paint = new Paint();

	private int w = 0,
							h = 0;

	public RoundedImageView(Context context){
		super(context);
	}

	public RoundedImageView(Context context, AttributeSet attrs){
		super(context, attrs);
	}

	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
	}

	public void setDimens(int w, int h){
		this.w = w;
		this.h = h;
	}

	public void setImage(Bitmap bitmap, int width, int height){
		int w = width;
		int h = height;
		int radius = Math.min(h/2, w/2);

		Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Paint p = new Paint();
		p.setAntiAlias(true);
		Canvas c = new Canvas(output);
		p.setStyle(Paint.Style.FILL);
		c.drawCircle(w/2, h/2, radius, p);
		p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		p.setDither(true);
		p.setAntiAlias(true);
		c.drawBitmap(bitmap, null, new RectF(0, 0, w, h), p);
		p.setXfermode(null);
		setImageBitmap(output);
	}

	@Override
	public void onSuccess(Bitmap bitmap){
		setImage(bitmap, w, h);
	}

	@Override
	public void onError(){}
}
