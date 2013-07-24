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
