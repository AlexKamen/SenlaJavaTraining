package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Book;

public class BookSortByAlphabetComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return book1.getTitle().compareTo(book2.getTitle());
	}
}
