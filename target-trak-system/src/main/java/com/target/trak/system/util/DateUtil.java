package com.target.trak.system.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

	private final static String EMPTY_STR = "";
	
	private DateUtil(){}
	
	public static String convertDateToIso8601(final Calendar calendar) {
		if (calendar == null) {
			return EMPTY_STR;
		}

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.S'Z'");
		String iso8601Date = df.format(new Date(calendar.getTimeInMillis()));
		return iso8601Date;
	}
}
