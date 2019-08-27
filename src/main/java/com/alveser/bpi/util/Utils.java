package com.alveser.bpi.util;

import java.time.LocalDate;
import java.time.Period;

/**
 * 
 * @author erikp
 * 
 * Utility class for generic things, such as Date formatting, etc.
 *
 */
public class Utils {
	
	public static String todayYYYYmmDD () {
		LocalDate localDate = LocalDate.now();
		//System.out.println("TODAY IS "+localDate.toString());
		return localDate.toString();
		
	}
	public static String thirtyDaysAgoYYYYmmDD() {
		LocalDate localDate = LocalDate.now();
		LocalDate localDateMinusThirtyDays = localDate.minus(Period.ofDays(30));
		//System.out.println("30 days ago is IS "+localDateMinusThirtyDays.toString());
		return localDateMinusThirtyDays.toString();
	}
}
