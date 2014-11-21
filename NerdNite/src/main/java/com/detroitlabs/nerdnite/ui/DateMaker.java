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

package com.detroitlabs.nerdnite.ui;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by jsibbold on 7/16/13.
 */
public class DateMaker{

	public static class DateString{
		public String dateString = "";
		public String dateSuffix = "";
	}

	private static Calendar cal = GregorianCalendar.getInstance();

	public static DateString getDateString(String input){
		String[] split = input.split("-");
		int month = Integer.parseInt(split[1]) - 1;
		int day = Integer.parseInt(split[2]);
		DateString dateString = new DateString();
		dateString.dateString = DateFormatSymbols.getInstance().getMonths()[month] + " " + day;
		dateString.dateSuffix = getSuffix(day);
		return dateString;
	}

	public static String getSuffix(final int day){
		switch (day){
			case 1:
			case 21:
			case 31:
				return "ST";
			case 2:
			case 22:
				return "ND";
			case 3:
			case 23:
				return "RD";
			default:
				return "TH";
		}
	}
}
