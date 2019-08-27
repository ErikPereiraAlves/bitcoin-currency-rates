package com.alveser.bpi.repository.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alveser.bpi.configuration.ConfigUtility;
import com.alveser.bpi.util.ServiceProviderHttpsRequest;
import com.alveser.bpi.util.Utils;

public class CoinDeskApiRepositoryTest {

	private ServiceProviderHttpsRequest apiCall = new ServiceProviderHttpsRequest();

	private String[] currencies = { "BRL", "USD", "EUR" };

	@Test
	public void loadSupportedCurrencies() {

		String api = ConfigUtility.getPropertyValue("coindesk-supported-currencies");

		assertTrue(api.length() > 0);

		String response = apiCall.doGet(api);

		assertTrue(response != null && response.length() > 0);

	};

	@Test
	public void findCurrentBitCoinRate() {

		for (String currency : currencies) {
			String api = ConfigUtility.getPropertyValue("coindesk-current");
			api = api.replace("<CODE>", currency);

			assertTrue(api.length() > 0);

			String response = apiCall.doGet(api);

			assertTrue(response != null && response.length() > 0);

		}

	}

	@Test
	public void findHistorical() {

		String endDate = Utils.todayYYYYmmDD();

		String startDate = Utils.thirtyDaysAgoYYYYmmDD();

		for (String currency : currencies) {

			String api = ConfigUtility.getPropertyValue("coindesk-historical");

			api = api.replace("<STARTDATE>", startDate).replace("<ENDDATE>", endDate).replace("<CURRENCY>", currency);

			assertTrue(api.length() > 0);

			String response = apiCall.doGet(api);

			assertTrue(response != null && response.length() > 0);

		}

	};

}
