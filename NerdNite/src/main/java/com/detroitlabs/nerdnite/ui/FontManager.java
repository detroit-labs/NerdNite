package com.detroitlabs.nerdnite.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;

@EBean
public class FontManager{
	
	
	public enum NerdNiteFont{
		FONT_PROXIMA_NOVA_REGULAR,
		FONT_PROXIMA_NOVA_BOLD,
		FONT_LOUIS_CONDENSED_DEMI,
		FONT_COURIER_NEW,
		FONT_COURIER_NEW_ITALIC,
		FONT_KONG;
		
		@Override
		public String toString(){
			switch(this){
				case FONT_PROXIMA_NOVA_REGULAR:
					return "ProximaNova-Regular.otf";
				case FONT_PROXIMA_NOVA_BOLD:
					return "ProximaNova-Bold.otf";
				case FONT_LOUIS_CONDENSED_DEMI:
					return "LouisCondensedDemi.otf";
				case FONT_COURIER_NEW:
					return "CourierNewBold.ttf";
				case FONT_COURIER_NEW_ITALIC:
					return "CourierNewBoldItalic.ttf";
				case FONT_KONG:
					return "kongtext.ttf";
				default:
					return "ProximaNova-Regular.otf";
			}
		}
	}
	
	Context context = null;
	
	public FontManager(Context context){
		this.context = context;
	}

	private void set(TextView tv, Typeface tf, String tag){
		tv.setTypeface(tf);
		if (tag != null && tv.getTag() == null)
			tv.setTag(tag);
	}

	public void setFont(TextView tv, String fontPath){
		Typeface tf = Typeface.createFromAsset(context.getAssets(), fontPath);
		set(tv, tf, fontPath);
	}
	
	public void setFont(TextView tv, NerdNiteFont nnFont){
		Typeface tf = Typeface.createFromAsset(context.getAssets(), nnFont.toString());
		set(tv, tf, nnFont.toString());
	}

	public void setFont(TextView[] tvs, NerdNiteFont nnFont){
		Typeface tf = Typeface.createFromAsset(context.getAssets(), nnFont.toString());
		for (TextView tv : tvs){
			set(tv, tf, nnFont.toString());
		}
	}
}
