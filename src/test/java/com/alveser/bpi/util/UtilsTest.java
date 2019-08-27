package com.alveser.bpi.util;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;

public class UtilsTest {

	@Test
	public void test() {
		
		assertTrue(Utils.todayYYYYmmDD() != null);
		
		assertTrue(Utils.thirtyDaysAgoYYYYmmDD() != null);
		
	}

}
