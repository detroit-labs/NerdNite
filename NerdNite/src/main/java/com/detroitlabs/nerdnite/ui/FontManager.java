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
