package com.senla.javatraining.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	/** Number of months for books which are considered old */
	public static int OLD_BOOKS_MONTHS_NUMBER = 6;
	
	/** Path to data store file */
	public static String STORE_FILE_PATH = "store.ser";
	
	/** Whether or not automatic completion of applications after add book to stock */
	public static boolean AUTO_APPLICATION_COMPLETE = false;
	
	private static Config instance;
	private Properties properties;
	
	public Config() {
		this.properties = new Properties();
		try {
			this.properties.load(new FileInputStream(new File("../Configuration/bs_config.properties")));
			OLD_BOOKS_MONTHS_NUMBER = Integer.valueOf(this.properties.getProperty("old_books_months_number"));
			STORE_FILE_PATH = this.properties.getProperty("data_file_location") + "/"
					+ this.properties.getProperty("data_file_name");
			AUTO_APPLICATION_COMPLETE = Boolean.valueOf(this.properties.getProperty("auto_application_completion"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Config getInstance() {
		if (instance == null) {
            instance = new Config();
        }
     
        return instance;
	}
}
