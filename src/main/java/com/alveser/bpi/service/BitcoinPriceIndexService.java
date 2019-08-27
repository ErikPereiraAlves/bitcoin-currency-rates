package com.alveser.bpi.service;

import java.util.List;

import com.alveser.bpi.model.SupportedCurrencyModel;

/**
 * 
 * @author erikp
 * 
 * Interface for different types of Bitcoin prince index providers.
 *
 */
public interface BitcoinPriceIndexService {
	
	public String lastXDaysKey = "default-last-x-days";
	
	public List<SupportedCurrencyModel> getSupportedCurrency();
	
	public String currentBitcoinRate(String currency);
	
	public Double [] getLowestAndHighestBitCoinRateForPeriod(String currency,String startDate, String endDate);
	
	public boolean validateCurrency(List<SupportedCurrencyModel> supportedCurrencyList , String currency);
	
	public boolean validateTypedInCountrycode(String countryCode);
}
