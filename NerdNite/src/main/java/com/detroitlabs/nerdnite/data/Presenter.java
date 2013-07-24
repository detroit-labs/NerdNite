package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jsibbold on 7/15/13.
 */
public class Presenter implements Parcelable{

	private int id, event_id;

	private String	name = "",
									bio = "",
									blurb = "",
									topic = "",
									twitter = "",
									pic = "",
									link = "";

	//Constructor
	public Presenter(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getEvent_id(){
		return event_id;
	}

	public void setEvent_id(int event_id){
		this.event_id = event_id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getBio(){
		return bio;
	}

	public void setBio(String bio){
		this.bio = bio;
	}

	public String getBlurb(){
		return blurb;
	}

	public void setBlurb(String blurb){
		this.blurb = blurb;
	}

	public String getTopic(){
		return topic;
	}

	public void setTopic(String topic){
		this.topic = topic;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getPic(){
		return pic;
	}

	public void setPic(String pic){
		this.pic = pic;
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

		public Presenter(Parcel in){
			id = in.readInt();
			event_id = in.readInt();
			name = in.readString();
			bio = in.readString();
			blurb = in.readString();
			topic = in.readString();
			twitter = in.readString();
			pic = in.readString();
			link = in.readString();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(id);
			dest.writeInt(event_id);
			dest.writeString(name);
			dest.writeString(bio);
			dest.writeString(blurb);
			dest.writeString(topic);
			dest.writeString(twitter);
			dest.writeString(pic);
			dest.writeString(link);
		}

		public static final Creator<Presenter> CREATOR = new Creator<Presenter>(){
			public Presenter createFromParcel(Parcel in){
				return new Presenter(in);
			}

			@Override
			public Presenter[] newArray(int size){
				return new Presenter[size];
			}
		};
		/**********************PARCELABLE SECTION**************************/
}
