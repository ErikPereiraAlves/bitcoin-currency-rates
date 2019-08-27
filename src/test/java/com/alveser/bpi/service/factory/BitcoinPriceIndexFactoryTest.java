package com.alveser.bpi.service.factory;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitcoinPriceIndexFactoryTest {
	
	
	BitcoinPriceIndexFactory  factory = new BitcoinPriceIndexFactory();

	@Test
	public void test() {
		assertTrue(null!= factory.getService("CoinDesk"));
		
		assertTrue(null == factory.getService("Ecma"));
	}

}
