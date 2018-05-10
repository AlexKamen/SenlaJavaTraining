package com.senla.javatraining.comparators;

import com.senla.javatraining.Book;
import java.util.Comparator;

public class BookSortByCostComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return Double.compare(book1.getCost(), book2.getCost());
	}
}
