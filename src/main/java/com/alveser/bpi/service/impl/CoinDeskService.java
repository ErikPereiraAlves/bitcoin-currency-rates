package com.alveser.bpi.service.impl;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alveser.bpi.model.CurrencyModel;
import com.alveser.bpi.model.SupportedCurrencyModel;
import com.alveser.bpi.repository.ApiRepository;
import com.alveser.bpi.repository.impl.CoinDeskApiRepository;
import com.alveser.bpi.service.BitcoinPriceIndexService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


/**
 * 
 * @author erikp
 * 
 * Class for CoinDesk company that provides APIs for Bitcoin price index.
 *
 */
public class CoinDeskService implements BitcoinPriceIndexService {

	public String prefix="coindesk";

	public String lastXDaysKey = prefix+"-last-x-days";

	public static List<SupportedCurrencyModel> supportedCurrencyModel = new ArrayList <>();

	public ApiRepository apiRepository= new CoinDeskApiRepository();
	
	private List<SupportedCurrencyModel> supportedCurrencyList = new ArrayList <> ();
	
	private Gson gson = new Gson();


	@Override
	public String currentBitcoinRate(String currency) {
		
		String response = apiRepository.findCurrentBitCoinRate(currency);
		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
    	Gson gson = new Gson();
		CurrencyModel foreignCurrency = gson.fromJson(jsonObject.get("bpi").getAsJsonObject().get(currency), CurrencyModel.class);
		
		return foreignCurrency.getRate();


	}

	@Override
	public Double [] getLowestAndHighestBitCoinRateForPeriod(String currency,String startDate, String endDate) {

		Double [] results = new Double[2];	

		String response = apiRepository.findHistorical(currency, startDate, endDate);

		JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
		Gson gson = new Gson();
		JsonElement jsonElement = jsonObject.get("bpi");

		Type type = new TypeToken<Map<Date, Double>>() {}.getType();
		Map<Date, Double> map = gson.fromJson(jsonElement.toString(), type);

		System.out.println("============ List of Date & Rates ============");
		map.entrySet().stream().forEach(e -> System.out.println(e.getKey()+" - "+ e.getValue()));
		System.out.println("==============================================");

		Double min = Collections.min(map.values());
		//System.out.println(min); // 0.1

		Double max  = Collections.max(map.values());

		results[0]= min;

		results [1] = max;

		return results;
	}



	@Override
	public List<SupportedCurrencyModel> getSupportedCurrency() {
		
		String response = apiRepository.loadSupportedCurrencies();
				
		if( null!= response && response.length()>0 && 0 == supportedCurrencyList.size()) {
			supportedCurrencyList = gson.fromJson(response, new TypeToken<List<SupportedCurrencyModel>>(){}.getType());
              
		}
		
		return supportedCurrencyList;
	
	}

	@Override
	public boolean validateCurrency(List<SupportedCurrencyModel> supportedCurrencyList , String currency) {

		SupportedCurrencyModel result = supportedCurrencyList.stream()
				.filter(supportedCurrencyModel -> currency.equals(supportedCurrencyModel.getCurrency()))
				.findAny()
				.orElse(null);

		return result != null? true:false;

	}

	@Override
	public boolean validateTypedInCountrycode(String countryCode) {
		if(null == countryCode && "".equals(countryCode)) {
			System.out.println("You did not type a valid Country code.");
			return false;
		}
		else {
			return true;
		}
	}

}
