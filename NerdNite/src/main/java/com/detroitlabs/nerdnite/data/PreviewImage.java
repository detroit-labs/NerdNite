package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jsibbold on 6/28/13.
 */
public class PreviewImage implements Parcelable{

	private int	id = -1,
				city_id = -1;

	private String link = "";

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getCity_id(){
		return city_id;
	}

	public void setCity_id(int city_id){
		this.city_id = city_id;
	}

	public String getLink(){
		return link;
	}

	public void setLink(String link){
		this.link = link;
	}

	/**********************PARCELABLE SECTION**************************/
		@Override
		public int describeContents() {
			return 0;
		}

		public PreviewImage(Parcel in){
			id = in.readInt();
			city_id = in.readInt();
			link = in.readString();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(id);
			dest.writeInt(city_id);
			dest.writeString(link);
		}

		public static final Creator<PreviewImage> CREATOR = new Creator<PreviewImage>(){
			public PreviewImage createFromParcel(Parcel in){
				return new PreviewImage(in);
			}

			@Override
			public PreviewImage[] newArray(int size){
				return new PreviewImage[size];
			}
		};
		/**********************PARCELABLE SECTION**************************/
}
