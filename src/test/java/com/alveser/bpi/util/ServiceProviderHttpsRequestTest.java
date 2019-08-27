package com.alveser.bpi.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ServiceProviderHttpsRequestTest {
	
	
	ServiceProviderHttpsRequest  serviceProviderHttpsRequest  = new ServiceProviderHttpsRequest();

	@Test
	public void test() {
		
		String response = serviceProviderHttpsRequest.doGet("https://api.coindesk.com/v1/bpi/supported-currencies.json ");
		
		assertTrue(null != response);
		
		
	}

}
