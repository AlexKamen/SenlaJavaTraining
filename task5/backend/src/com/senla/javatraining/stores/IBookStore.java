package com.senla.javatraining.stores;

import com.senla.javatraining.models.Book;

public interface IBookStore {
	public void addBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book);
	
	public Book[] getAllBooks();
	
	public Book getBook(int id);
	
}