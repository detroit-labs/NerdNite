package com.detroitlabs.nerdnite.volley;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;

public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {

	private static LruBitmapCache mCache;

	public static LruBitmapCache instance(int maxSize){
		if (mCache == null){
			mCache = new LruBitmapCache(maxSize);
		}

		return mCache;
	}

	private LruBitmapCache(int maxSize) {
		super(maxSize);
	}
	
	@Override
	protected int sizeOf(String key, Bitmap value) {
		return value.getRowBytes() * value.getHeight();
	}
	
	@Override
	public Bitmap getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		put(url, bitmap);
	}

}
