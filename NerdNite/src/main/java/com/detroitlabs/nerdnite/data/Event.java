package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by jsibbold on 6/28/13.
 */
public class Event implements Parcelable{

	public static final String EXTRA_EVENT = "event";

	private int id;
	private String 	title = "",
					venue_name = "",
					address = "",
					description = "",
					date = "",
					price = "",
					tickets_link = "",
					event_link = "";

	private ArrayList<Presenter> presenters;

	//Constructor
	public Event(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getVenue_name(){
		return venue_name;
	}

	public void setVenue_name(String venue_name){
		this.venue_name = venue_name;
	}

	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDate(){
		return date;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getPrice(){
		return price;
	}

	public void setPrice(String price){
		this.price = price;
	}

	public String getTickets_link(){
		return tickets_link;
	}

	public void setTickets_link(String tickets_link){
		this.tickets_link = tickets_link;
	}

	public String getEvent_link(){
		return event_link;
	}

	public void setEvent_link(String event_link){
		this.event_link = event_link;
	}

	public ArrayList<Presenter> getPresenters(){
		return presenters;
	}

	public void setPresenters(ArrayList<Presenter> presenters){
		this.presenters = presenters;
	}

	/**********************PARCELABLE SECTION**************************/
		@Override
		public int describeContents() {
			return 0;
		}

		public Event(Parcel in){
			id = in.readInt();
			title = in.readString();
			venue_name = in.readString();
			address = in.readString();
			description = in.readString();
			date = in.readString();
			price = in.readString();
			tickets_link = in.readString();
			event_link = in.readString();
			presenters = in.readArrayList(Presenter.class.getClassLoader());
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(id);
			dest.writeString(title);
			dest.writeString(venue_name);
			dest.writeString(address);
			dest.writeString(description);
			dest.writeString(date);
			dest.writeString(price);
			dest.writeString(tickets_link);
			dest.writeString(event_link);
			dest.writeList(presenters);
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
