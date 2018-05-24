package com.senla.javatraining.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Scan {
	public static final Logger logger = Logger.getLogger(Scan.class.getName());
	
	private static Scan instance;
	
	private Scanner scanner;

	public Scan() {
		this.scanner = new Scanner(System.in);
	}
	
	public static Scan getInstance() {
    	if (instance == null) {
            instance = new Scan();
        }
     
        return instance;
    }

	public int getIntValue() {
		int value;
		while (true) {
			try {
				System.out.println("Enter number");
				value = this.scanner.nextInt();
				this.scanner.nextLine();
				break;
			} catch(InputMismatchException  e) {
				System.out.println("You entered an incorrect value! Please, retry.");
				logger.warn("User entered incorrect value");
				this.scanner.nextLine();
				continue;
			}
		}
		
		return value;
	}

	public String getStringValue() {
		String value;
		System.out.println("Enter string");
		value = this.scanner.nextLine();
		
		return value;
		
	}

	public Date getDateValue() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		
		Date resDate = new Date();
		String value;
		
		while (true) {
			try {
				System.out.println("Enter date in format dd.mm.yyyy");
				value = this.scanner.nextLine();
				resDate = dateFormat.parse(value);
				break;
			} catch(ParseException  e) {
				System.out.println("Wrong date format! Please, retry.");
				logger.warn("User entered incorrect value");
				this.scanner = new Scanner(System.in);
				continue;
			}
		}
		
		return resDate;
	}

	public double getDoubleValue() {
		double value;
		while (true) {
			try {
				System.out.println("Enter double value");
				value = this.scanner.nextDouble();
				this.scanner.nextLine();
				break;
			} catch(InputMismatchException  e) {
				System.out.println("You entered an incorrect value! Please, retry.");
				logger.warn("User entered incorrect value");
				this.scanner.nextLine();
				continue;
			}
		}
		
		return value;
	}
}