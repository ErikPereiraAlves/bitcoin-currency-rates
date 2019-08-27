package com.alveser.bpi.application;

import com.alveser.bpi.configuration.ConfigUtility;
import com.alveser.bpi.controller.ApplicationController;
import com.alveser.bpi.exception.ApplicationException;
import com.alveser.bpi.service.impl.CoinDeskService;



/**
 * 
 * @author erikp
 * 
 * Application entry point.
 *
 */
public class App {


	//private static boolean hault = false; // TODO: improve app, have user run it until one types "exit"

	public static void main( String[] args )
	{
		System.out.println(ConfigUtility.getPropertyValue("application"));

		ApplicationController applicationController = new ApplicationController();

		//TODO: for now we only have CoinDesk as the only available service, therefore not asking user which service to use. Coindesk is DEFAULT.
		
		try {

		applicationController.start(new CoinDeskService());
		
		}
		catch (ApplicationException a) {
			System.out.println("Application Exception : "+a.getMessage());
		}




	}
}
