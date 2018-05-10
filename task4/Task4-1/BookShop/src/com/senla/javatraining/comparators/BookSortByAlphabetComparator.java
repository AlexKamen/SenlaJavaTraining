package com.senla.javatraining.comparators;

import com.senla.javatraining.Book;
import java.util.Comparator;

public class BookSortByAlphabetComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return book1.getTitle().compareTo(book2.getTitle());
	}
}
