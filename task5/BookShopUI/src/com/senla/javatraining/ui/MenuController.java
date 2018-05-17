package com.senla.javatraining.ui;

import java.util.ArrayList;
import java.util.Arrays;

public class MenuController {
	private static MenuController instance;
	
	private Builder builder;
	private Navigator navigator;
	private Scan scanner;
	
	private MenuController() {
		this.builder = new Builder();
		this.navigator = new Navigator();
		this.scanner = new Scan();
    }
 
    public static MenuController getInstance() {
    	if (instance == null) {
            instance = new MenuController();
        }
     
        return instance;
    }
	
	public void run() {
		this.builder.buildMenu();
		this.navigator.setRootMenu(this.builder.getRootMenu());
		this.navigator.setCurrentMenu(this.builder.getRootMenu());
		this.navigator.printMenu();
		while (true) {
			int index = this.scanner.getIntValue();
			if (!this.navigator.navigate(index)) {
				break;
			}
		}
	}
}
