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
			v.setBackgroundResource(R.drawable.selector_location_list);

		}
		
		LocationRowHolder holder = (LocationRowHolder)v.getTag();
		holder.city.setText(getItem(position).city);
		fm.setFont(holder.city, NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
		holder.state.setText(getItem(position).state);
		fm.setFont(holder.city, NerdNiteFont.FONT_PROXIMA_NOVA_BOLD);
		
		return v;
	}
	
	private class LocationRowHolder{
		public TextView city;
		public TextView state;
	}

}
