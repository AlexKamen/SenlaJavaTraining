package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Book;

public class BookSortByCostComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return Double.compare(book1.getCost(), book2.getCost());
	}
}
