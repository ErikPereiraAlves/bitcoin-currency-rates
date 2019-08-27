package com.alveser.bpi.service.factory;

import com.alveser.bpi.service.BitcoinPriceIndexService;
import com.alveser.bpi.service.impl.CoinDeskService;

/**
 * 
 * @author erikp
 * 
 * In Factory pattern, we create object without exposing the creation logic to the client and refer to newly created object using a common interface.
 *
 */
public class BitcoinPriceIndexFactory {
	
	   public BitcoinPriceIndexService getService(String service){
	      if(service == null){
	         return null;
	      }		
	      if(service.equals("CoinDesk")){
	         return new CoinDeskService();
	         
	      } 
	      // ... more BPI service providers in the future ...
	      
	      return null;
	   }

}
