package com.senla.javatraining.ui.actions;

import java.util.Date;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.ui.Scan;

public class EarnedMoney implements IAction {
	private Scan scanner;
	private IBookShop bookShop;
	
	public EarnedMoney() {
		this.scanner = new Scan();
		this.bookShop = new BookShop();
	}
	
	@Override
	public void execute() {
		Date dateStart = null;
		Date dateEnd = null;
		
		System.out.println("Enter period start date");
		dateStart = this.scanner.getDateValue();
		
		if (dateStart != null) {
			System.out.println("Enter period end date");
			dateEnd = this.scanner.getDateValue();
		}

		System.out.println("Earned money: "
						   + this.bookShop.getTotalOrdersPrice(dateStart, dateEnd));

	}

}
