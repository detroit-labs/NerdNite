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
