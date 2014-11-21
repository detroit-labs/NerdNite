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

package com.detroitlabs.nerdnite.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.detroitlabs.nerdnite.R;
import com.detroitlabs.nerdnite.data.City;
import com.detroitlabs.nerdnite.ui.FontManager;
import com.detroitlabs.nerdnite.ui.FontManager.NerdNiteFont;

import java.util.List;

public class LocationListAdapter extends ArrayAdapter<City>{
	
	FontManager fm;
	
	public LocationListAdapter(Context context, int vid, List<City> data){
		super(context, vid, data);
		fm = new FontManager(context);
	}
	
	@Override
	public View getView(int position, View v, ViewGroup parent){
		Context context = getContext();
		
		if (v == null){
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.row_location_list_item, null);
			LocationRowHolder holder = new LocationRowHolder();
			holder.city = (TextView)v.findViewById(R.id.city);
			holder.state = (TextView)v.findViewById(R.id.state);
			v.setTag(holder);
		}
		
		LocationRowHolder holder = (LocationRowHolder)v.getTag();
		holder.city.setText(getItem(position).getCity());
		fm.setFont(holder.city, NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
		holder.state.setText(getItem(position).getState());
		fm.setFont(holder.city, NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
		
		return v;
	}
	
	private class LocationRowHolder{
		public TextView city;
		public TextView state;
	}

}
