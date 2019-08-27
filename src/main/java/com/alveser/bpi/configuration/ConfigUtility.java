package com.alveser.bpi.configuration;
import org.apache.commons.lang3.StringUtils;

import com.alveser.bpi.exception.ApplicationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


/**
 * 
 * @author erikp
 * 
 * Singleton Utility class responsible for fetching configuration values in application.properties file.
 *
 */
public class ConfigUtility {
	
	
	/** private constructor to prevent others from instantiating this class */
    private ConfigUtility() {}

    /** Create an instance of the class at the time of class loading */
    private static final ConfigUtility instance = new ConfigUtility();

    /** Provide a global point of access to the instance */
    public static ConfigUtility getInstance() {
        return instance;
    }
	
	
	public static Properties prop = new Properties();
	
	public static String getPropertyValue(String key) {
		 
		 Properties props = new Properties();
		
		String value = null;
		try {
			 props.load(ClassLoader.getSystemResourceAsStream("config.properties"));
            value = props.getProperty(key);

            if (!StringUtils.isEmpty(value)) {
                //System.out.println("Value issued : "+value);
            }
            else {
            	throw new ApplicationException("Key not found in properties file : "+key+". Please double check config.properties.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
		return value;
		
	}
	

}
