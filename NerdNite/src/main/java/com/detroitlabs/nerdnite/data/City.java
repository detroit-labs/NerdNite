package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

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

	private ArrayList<Boss> bosses;

	private ArrayList<PreviewImage> preview_images;

	private Event next_event;

	//Constructor
	public City(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Event getNext_event(){
		return next_event;
	}

	public void setNext_event(Event next_event){
		this.next_event = next_event;
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
		bosses = in.readArrayList(Boss.class.getClassLoader());
		preview_images = in.readArrayList(PreviewImage.class.getClassLoader());
		next_event = in.readParcelable(Event.class.getClassLoader());
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
			dest.writeList(bosses);
			dest.writeList(preview_images);
			dest.writeParcelable(next_event, 0);
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
