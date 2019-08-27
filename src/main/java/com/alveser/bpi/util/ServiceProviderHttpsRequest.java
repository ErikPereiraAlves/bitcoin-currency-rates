package com.alveser.bpi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alveser.bpi.exception.ApplicationException;


public class ServiceProviderHttpsRequest {


	
	public String doGet(String url) {
		
		return  doGet( url, null);
	}
	
	
	public String doGet(String url, String body) {

		//long totalTime = System.currentTimeMillis();

		StringBuilder response = new StringBuilder();

		try {
			URL netUrl = new URL (url);
			HttpURLConnection con = (HttpURLConnection)netUrl.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			if(null!= body) {
				try(OutputStream os = con.getOutputStream()){
					byte[] input = body.getBytes("utf-8");
					os.write(input, 0, input.length);			
				}
			}

			//int code = con.getResponseCode();

			try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){

				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}

			}

		}catch (Exception e) {
			throw new ApplicationException("Unable to HTTPS request for url "+url);
		}
		
	/*	System.out.println("[ServiceProviderHttpsRequest] [HTTPS request] Total time taken (seconds): "
				+ (((System.currentTimeMillis() - totalTime) / 1000)));*/
		
		
		return response.toString();
	}

}
