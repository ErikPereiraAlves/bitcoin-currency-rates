package com.alveser.bpi.controller;

import java.util.Scanner;

import com.alveser.bpi.exception.ApplicationException;
import com.alveser.bpi.service.BitcoinPriceIndexService;
import com.alveser.bpi.util.Utils;

/**
 * 
 * @author erikp
 * 
 * This is the application main controller.
 *
 */
public class ApplicationController {

	private BitcoinPriceIndexService  bitcoinPriceIndexService;


	public void start(BitcoinPriceIndexService service) {

		bitcoinPriceIndexService = service;

		System.out.println("Type Currency Code : ");
		Scanner scanner = new Scanner(System.in);
		String systemInCurrencyCode = scanner.next();
		scanner.close();

		if(!bitcoinPriceIndexService.validateTypedInCountrycode(systemInCurrencyCode)){
			throw new ApplicationException("You did not type a valid Country code. Please re-run application.");
		}

		systemInCurrencyCode = systemInCurrencyCode.toUpperCase();

		System.out.println("You chose the following currency code : "+systemInCurrencyCode+" \n");
		

		if(!bitcoinPriceIndexService.validateCurrency(bitcoinPriceIndexService.getSupportedCurrency(),systemInCurrencyCode)) { 
			throw new ApplicationException("You typed a currency that is not supported.");
		}

		String rate = bitcoinPriceIndexService.currentBitcoinRate(systemInCurrencyCode);
		System.out.println("The current Bitcoin rate, in the requested currency: "+rate);


		String today = Utils.todayYYYYmmDD();
		String thirtyDaysAgo = Utils.thirtyDaysAgoYYYYmmDD();

		Double [] results = bitcoinPriceIndexService.getLowestAndHighestBitCoinRateForPeriod(systemInCurrencyCode,thirtyDaysAgo,today);

		System.out.println("The lowest Bitcoin rate in the last 30 days, in the requested currency : "+results[0]);


		System.out.println("The highest Bitcoin rate in the last 30 days, in the requested currency :"+results[1]);

	}




}
