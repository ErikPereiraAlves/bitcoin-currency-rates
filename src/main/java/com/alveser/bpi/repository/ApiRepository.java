package com.alveser.bpi.repository;

public interface ApiRepository {


	public default String findCurrentBitCoinRate(String currency) {
		return null;
	};

	public default String findHistorical(String currency,String startDate, String endDate){
		return null;
	};

	public default String loadSupportedCurrencies(){
		return null;
	};
}
