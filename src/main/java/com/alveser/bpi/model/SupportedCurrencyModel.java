package com.alveser.bpi.model;



/**
 * 
 * @author erikp
 * 
 * Model for supported currencies.
 * https://api.coindesk.com/v1/bpi/supported-currencies.json
 *
 */
public class SupportedCurrencyModel implements Model {
	
	private String currency;
	
	private String country;

	public SupportedCurrencyModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SupportedCurrencyModel(String currency, String country) {
		super();
		this.currency = currency;
		this.country = country;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "SupportedCurrencyModel [currency=" + currency + ", country=" + country + "]";
	}
	
	

}
