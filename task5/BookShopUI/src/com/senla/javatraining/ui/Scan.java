package com.senla.javatraining.ui;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Scan {
	private Scanner scanner;

	public Scan() {
		this.scanner = new Scanner(System.in);
	}

	public int getIntValue() {
		int value;
		while (true) {
			try {
				System.out.println("Enter number");
				value = this.scanner.nextInt();
				break;
			} catch(InputMismatchException  e) {
				System.out.println("You entered an incorrect value! Please, retry.");
				this.scanner = new Scanner(System.in);
				continue;
			}
		}
		
		return value;
	}

	public String getStringValue() {
		String value;
		System.out.println("Enter string");
		value = this.scanner.next();
		
		return value;
		
	}

	public Date getDateValue() {
		return null;
	}
}