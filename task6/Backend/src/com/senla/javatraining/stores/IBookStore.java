package com.senla.javatraining.stores;

import java.util.ArrayList;

import com.senla.javatraining.models.Book;

public interface IBookStore {
	public void setBooks(ArrayList<Book> books);
	
	public void addBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book);
	
	public ArrayList<Book> getAllBooks();
	
	public Book getBook(int id);	
}