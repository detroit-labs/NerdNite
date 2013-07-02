package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable{
	public static final String EXTRA_CITY = "city";

	protected int id = -1;
	protected String city = "";
	protected String state = "";
	protected String description = "";
	protected String twitter = "";
	protected String facebook = "";
	protected String hashtag = "";
	protected String banner_image = "";
	protected String year_est = "";

	public City(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getState(){
		return state;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getFacebook(){
		return facebook;
	}

	public void setFacebook(String facebook){
		this.facebook = facebook;
	}

	public String getHashtag(){
		return hashtag;
	}

	public void setHashtag(String hashtag){
		this.hashtag = hashtag;
	}

	public String getBanner_image(){
		return banner_image;
	}

	public void setBanner_image(String banner_image){
		this.banner_image = banner_image;
	}

	public String getYear_est(){
		return year_est;
	}

	public void setYear_est(String year_est){
		this.year_est = year_est;
	}

	/**********************PARCELABLE SECTION**************************/
		@Override
		public int describeContents() {
			return 0;
		}
		
		public City(Parcel in){
			id = in.readInt();
			city = in.readString();
			state = in.readString();
			description = in.readString();
			twitter = in.readString();
			facebook = in.readString();
			hashtag = in.readString();
			banner_image = in.readString();
			year_est = in.readString();
		}
	
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(id);
			dest.writeString(city);
			dest.writeString(state);
			dest.writeString(description);
			dest.writeString(twitter);
			dest.writeString(facebook);
			dest.writeString(hashtag);
			dest.writeString(banner_image);
			dest.writeString(year_est);
		}
		
		public static final Creator<City> CREATOR = new Creator<City>(){
			public City createFromParcel(Parcel in){
				return new City(in);
			}
	
			@Override
			public City[] newArray(int size){
				return new City[size];
			}
		};
		/**********************PARCELABLE SECTION**************************/
}
