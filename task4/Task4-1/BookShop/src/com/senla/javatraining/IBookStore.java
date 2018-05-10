package com.senla.javatraining;

public interface IBookStore {
	public void addBook(Book book);
	
	public void updateBook(Book book);
	
	public void deleteBook(Book book);
	
	public Book[] getAllBooks();
	
	public Book getBook(int id);
	
}