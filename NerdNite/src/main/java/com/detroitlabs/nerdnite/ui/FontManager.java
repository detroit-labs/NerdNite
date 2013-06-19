package com.detroitlabs.nerdnite.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class FontManager{
	
	
	public enum NerdNiteFont{
		FONT_PROXIMA_NOVA_REGULAR,
		FONT_PROXIMA_NOVA_BOLD,
		FONT_LOUIS_CONDENSED_DEMI;
		
		@Override
		public String toString(){
			switch(this){
				case FONT_PROXIMA_NOVA_REGULAR:
					return "ProximaNova-Regular.otf";
				case FONT_PROXIMA_NOVA_BOLD:
					return "ProximaNova-Bold.otf";
				case FONT_LOUIS_CONDENSED_DEMI:
					return "LouisCondensedDemi.otf";
				default:
					return "ProximaNova-Regular.otf";
			}
		}
	}
	
	Context context = null;
	
	public FontManager(Context context){
		this.context = context;
	}
	
	public void setFont(TextView tv, NerdNiteFont nnFont){
		Typeface tf = Typeface.createFromAsset(context.getAssets(), nnFont.toString());
		tv.setTypeface(tf);
	}
}
