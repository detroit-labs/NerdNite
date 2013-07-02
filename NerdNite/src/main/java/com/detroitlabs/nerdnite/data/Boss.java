package com.detroitlabs.nerdnite.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jsibbold on 6/28/13.
 */
public class Boss implements Parcelable{
	private int	id,
				city_id;

	private String 	name,
					pic,
					link;

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

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
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

		public Boss(Parcel in){
			id = in.readInt();
			city_id = in.readInt();
			name = in.readString();
			pic = in.readString();
			link = in.readString();
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			dest.writeInt(id);
			dest.writeInt(city_id);
			dest.writeString(name);
			dest.writeString(pic);
			dest.writeString(link);
		}

		public static final Parcelable.Creator<Boss> CREATOR = new Parcelable.Creator<Boss>(){
			public Boss createFromParcel(Parcel in){
				return new Boss(in);
			}

			@Override
			public Boss[] newArray(int size){
				return new Boss[size];
			}
		};
		/**********************PARCELABLE SECTION**************************/
}
