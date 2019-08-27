package com.alveser.bpi.repository.impl;

import com.alveser.bpi.configuration.ConfigUtility;
import com.alveser.bpi.repository.ApiRepository;
import com.alveser.bpi.util.ServiceProviderHttpsRequest;

public class CoinDeskApiRepository implements ApiRepository {
	
	private ServiceProviderHttpsRequest apiCall = new ServiceProviderHttpsRequest();
	
	
	
	public String loadSupportedCurrencies(){
		
		String api = ConfigUtility.getPropertyValue("coindesk-supported-currencies");
		return apiCall.doGet(api);
		
	};
	
	@Override
    public String findCurrentBitCoinRate(String currency) {
    	
    	String api = ConfigUtility.getPropertyValue("coindesk-current");
    	api = api.replace("<CODE>", currency);
		
		return apiCall.doGet(api);
    	
	}
	
	
	@Override
	public String findHistorical(String currency,String startDate, String endDate){
		
		String api = ConfigUtility.getPropertyValue("coindesk-historical");
		
		api = api.replace("<STARTDATE>", startDate).replace("<ENDDATE>", endDate).replace("<CURRENCY>", currency);
		
		return apiCall.doGet(api);
		
	};

}
