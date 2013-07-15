package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jsibbold on 6/28/13.
 */
public class Event extends City implements Parcelable{

	public static final String EXTRA_EVENT = "event";

	public Event(){
		super();
	}

	private ArrayList<Boss> bosses;

	private ArrayList<PreviewImage> preview_images;

	public ArrayList<Boss> getBosses(){
		return bosses;
	}

	public void setBosses(ArrayList<Boss> bosses){
		this.bosses = bosses;
	}

	public ArrayList<PreviewImage> getPreview_images(){
		return preview_images;
	}

	public void setPreview_images(ArrayList<PreviewImage> preview_images){
		this.preview_images = preview_images;
	}

	/**********************PARCELABLE SECTION**************************/
		@Override
		public int describeContents() {
			return 0;
		}

		public Event(Parcel in){
			super(in);
			preview_images = (ArrayList<PreviewImage>)in.readArrayList(PreviewImage.class.getClassLoader());
			bosses = (ArrayList<Boss>)in.readArrayList(Boss.class.getClassLoader());
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			super.writeToParcel(dest, flags);
			dest.writeList(preview_images);
			dest.writeList(bosses);
		}

		public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>(){
			public Event createFromParcel(Parcel in){
				return new Event(in);
			}

			@Override
			public Event[] newArray(int size){
				return new Event[size];
			}
		};
		/**********************PARCELABLE SECTION**************************/
}
