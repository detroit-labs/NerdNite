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
