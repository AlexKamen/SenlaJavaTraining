package com.senla.javatraining.ui;

public class Menu {
	private String name;
	private MenuItem[] menuItems;

	Menu() {
		
	}
	
	Menu (String name, MenuItem[] menuItems) {
		this.name = name;
		this.menuItems = menuItems;
	}

	public MenuItem[] getMenuItems() {
		return this.menuItems;
	}

	public String getName() {
		return this.name;
	}
}
