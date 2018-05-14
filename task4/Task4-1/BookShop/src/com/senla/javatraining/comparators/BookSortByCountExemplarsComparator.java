package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Book;

public class BookSortByCountExemplarsComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return Integer.compare(book1.getCountExemplars(), book2.getCountExemplars());
	}
}
