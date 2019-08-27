package com.alveser.bpi.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.alveser.bpi.model.CurrencyModel;
import com.alveser.bpi.model.SupportedCurrencyModel;
import com.alveser.bpi.repository.ApiRepository;
import com.alveser.bpi.repository.impl.CoinDeskApiRepository;
import com.alveser.bpi.service.BitcoinPriceIndexService;
import com.alveser.bpi.util.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class CoinDeskServiceTest {

	public String prefix = "coindesk";

	public String lastXDaysKey = prefix + "-last-x-days";

	public static List<SupportedCurrencyModel> supportedCurrencyModel = new ArrayList<>();

	public ApiRepository apiRepository = new CoinDeskApiRepository();

	private List<SupportedCurrencyModel> supportedCurrencyList = new ArrayList<>();

	private Gson gson = new Gson();

	private String[] currencies = { "BRL", "USD", "EUR" };

	private String[] currenciesFake = { "ABC", "DEF", "XXX" };

	private BitcoinPriceIndexService coinDeskService = new CoinDeskService();

	@Before
	public void getSupportedCurrencies() {

		supportedCurrencyList = coinDeskService.getSupportedCurrency();

	}

	@Test
	public void currentBitcoinRate() {

		for (String currency : currencies) {

			String response = apiRepository.findCurrentBitCoinRate(currency);
			JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
			Gson gson = new Gson();
			CurrencyModel foreignCurrency = gson.fromJson(jsonObject.get("bpi").getAsJsonObject().get(currency),
					CurrencyModel.class);

			Assert.assertFalse(null == foreignCurrency);

			Assert.assertFalse(foreignCurrency.getRate_float() == 0);
		}
	}

	@Test
	public void getLowestAndHighestBitCoinRateForPeriod() {

		String endDate = Utils.todayYYYYmmDD();

		String startDate = Utils.thirtyDaysAgoYYYYmmDD();

		for (String currency : currencies) {

			String response = apiRepository.findHistorical(currency, startDate, endDate);

			JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
			Gson gson = new Gson();
			JsonElement jsonElement = jsonObject.get("bpi");

			Assert.assertFalse(null == jsonElement);

			Type type = new TypeToken<Map<Date, Double>>() {
			}.getType();
			Map<Date, Double> map = gson.fromJson(jsonElement.toString(), type);

			Assert.assertFalse(null == map);
			Assert.assertTrue(map.size() > 0);

			Double min = Collections.min(map.values());

			Assert.assertTrue(null != min && min > 0);

			Double max = Collections.max(map.values());

			Assert.assertTrue(null != max && max > 0);

		}

	}

	@Test
	public void getSupportedCurrency() {

		String response = apiRepository.loadSupportedCurrencies();

		if (null != response && response.length() > 0 && 0 == supportedCurrencyList.size()) {
			supportedCurrencyList = gson.fromJson(response, new TypeToken<List<SupportedCurrencyModel>>() {
			}.getType());

		}

	}

	@Test
	public void validateCurrency() {

		boolean success = false;
		for (String currency : currencies) {

			SupportedCurrencyModel result = supportedCurrencyList.stream()
					.filter(supportedCurrencyModel -> currency.equals(supportedCurrencyModel.getCurrency())).findAny()
					.orElse(null);

			success = result != null ? true : false;

			Assert.assertTrue(success == true);

		}

		for (String currency : currenciesFake) {

			SupportedCurrencyModel result = supportedCurrencyList.stream()
					.filter(supportedCurrencyModel -> currency.equals(supportedCurrencyModel.getCurrency())).findAny()
					.orElse(null);

			success = result != null ? true : false;

			Assert.assertTrue(success == false);

		}

	}

}
