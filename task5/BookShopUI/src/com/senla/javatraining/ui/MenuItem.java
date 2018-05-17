package com.senla.javatraining.ui;

import com.senla.javatraining.ui.actions.IAction; 

public class MenuItem {
	private String title;
	private IAction action;
	private Menu nextMenu;

	MenuItem() {
		
	}

	MenuItem(String title, Menu nextMenu) {
		this.title = title;
		this.action = null;
		this.nextMenu = nextMenu;
	}
	
	MenuItem(String title, IAction action) {
		this.title = title;
		this.action = action;
		this.nextMenu = null;
	}

	public IAction getAction() {
		return this.action;
	}

	public Menu getNextMenu() {
		return this.nextMenu;
	}

	public String getTitle() {
		return this.title;
	}
}
