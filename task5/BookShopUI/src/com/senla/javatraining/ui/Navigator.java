package com.senla.javatraining.ui;

public class Navigator {
	private Menu rootMenu;
	private Menu currentMenu;
	
	Navigator() {
		
	}

	public void printMenu() {
		System.out.println("");
		System.out.println("---------- MENU ----------");
		
		if (this.currentMenu == this.rootMenu) {
			System.out.println("0 - exit");
		}
		else {
			System.out.println("0 - to start");
		}

		for (int i = 1; i <= this.currentMenu.getMenuItems().length; i++) {
			System.out.println(Integer.toString(i) + " - " + this.currentMenu.getMenuItems()[i-1].getTitle());
		}
	}
	
	public boolean navigate(int index) {
		if (index == 0) {
			
			/* Program exit */
			if (this.currentMenu == this.rootMenu) {
				return false;
			}
			
			this.setCurrentMenu(this.rootMenu);
			this.printMenu();
		}
		else {
			try {
				MenuItem[] menuItems = this.currentMenu.getMenuItems();
				if (menuItems[index-1].getAction() == null) {
					this.setCurrentMenu(menuItems[index-1].getNextMenu());
					this.printMenu();
				}
				else {
					menuItems[index-1].getAction().execute();
					this.printMenu();
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("Wrong value! Please, retry!");
				this.printMenu();
			}
		}
		
		return true;
	}

	public void setCurrentMenu(Menu menu) {
		this.currentMenu = menu;
	}
	
	public void setRootMenu(Menu menu) {
		this.rootMenu = menu;
	}
}