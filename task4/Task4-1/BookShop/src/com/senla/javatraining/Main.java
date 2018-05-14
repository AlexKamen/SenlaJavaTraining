/**
 * 
 */
package com.senla.javatraining;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.senla.javatraining.models.Book;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.models.Request;

/**
 * @author Alexander
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		/* Create BookShop object */
		IBookShop bookShop = new BookShop();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
		
		// Prepare dates 
		Date book1Date = new Date();
		Date book2Date = new Date();
		Date book3Date = new Date();
		Date book4Date = new Date();
		Date book5Date = new Date();
		Date order1DateCompletion = new Date();
		Date order2DateCompletion = new Date();
		Date order3DateCompletion = new Date();
		Date order4DateCompletion = new Date();
		
		try {
			book1Date = dateFormat.parse("01.01.2017");
			book2Date = dateFormat.parse("01.01.2018");
			book3Date = dateFormat.parse("01.01.2017");
			book4Date = dateFormat.parse("01.01.2012");
			book5Date = dateFormat.parse("01.01.2018");
			order1DateCompletion = dateFormat.parse("18.05.2018");
			order2DateCompletion = dateFormat.parse("21.05.2018");
			order3DateCompletion = dateFormat.parse("01.06.2018");
			order4DateCompletion = dateFormat.parse("01.05.2018");
	    } catch (ParseException e) { 
	    	e.printStackTrace();
	    }
		
		/*---------- START BOOKS ----------*/
		// Create books objects
		Book book1 = new Book("Аэропорт", "Артур Хейли", 
							  book1Date, 317.00);
		Book book2 = new Book("Один из нас лжет", "Карен Макманус", 
							  book2Date, 317.00);
		Book book3 = new Book("Весь этот мир", "Никола Юн", 
				  			  book3Date, 324.00);
		Book book4 = new Book("English Grammar in use. Fourth Edition", "Raymond Murphy", 
	  			  			  book4Date, 1489.00);
		Book book5 = new Book("PRO шашлык", "Сталик Ханкишиев", 
	  			  			  book5Date, 391.00);
		
		// Add books
		bookShop.addBook(book1);
		bookShop.addBook(book2);
		bookShop.addBook(book3);
		bookShop.addBook(book4);
		bookShop.addBook(book5);
		
		// Add books to stock
		bookShop.addBookToStock(book1, 5);
		bookShop.addBookToStock(book2, 2);
		bookShop.addBookToStock(book3, 3);
		bookShop.addBookToStock(book4, 1);
		bookShop.addBookToStock(book5, 6);
		
		// Update book
		Book bookTmp = bookShop.getBook(4);
		bookTmp.setCountExemplars(50);
		bookShop.updateBook(bookTmp);
		
		// Delete book
		bookTmp = bookShop.getBook(5);
		bookShop.deleteBook(bookTmp);
		/*---------- END BOOKS ----------*/

		
		
		/*---------- START ORDERS ----------*/
		// Initialize orders
		Order order1 = new Order(order1DateCompletion);
		order1.addItem(new OrderItem(bookShop.getBook(1), 2));
		order1.addItem(new OrderItem(bookShop.getBook(2), 1));
		order1.addItem(new OrderItem(bookShop.getBook(3), 5));

		Order order2 = new Order(order2DateCompletion);
		order2.addItem(new OrderItem(bookShop.getBook(3), 1));
		order2.addItem(new OrderItem(bookShop.getBook(2), 1));
		order2.addItem(new OrderItem(bookShop.getBook(4), 1));
		
		Order order3 = new Order(order3DateCompletion);
		order3.addItem(new OrderItem(bookShop.getBook(1), 30));
		
		Order order4 = new Order(order4DateCompletion);
		order4.addItem(new OrderItem(bookShop.getBook(2), 2));
		
		// Add orders
		bookShop.addOrderWithCheckBookExistance(order1);
		bookShop.addOrderWithCheckBookExistance(order2);
		bookShop.addOrderWithCheckBookExistance(order3);
		bookShop.addOrderWithCheckBookExistance(order4);
		
		// Update order
		Order orderTmp = bookShop.getOrder(3);
		orderTmp.addItem(new OrderItem(book2, 10));
		bookShop.updateOrder(orderTmp);
		/*---------- END ORDERS ----------*/

		
		/*---------- SOME OPERATIONS FOR CHECK ---------- */
		System.out.println("Sorted books list");
		
		ArrayList<Book> sortedBooks = bookShop.getBooks(BookShop.ORDERBY_COST);
		for(Book book: sortedBooks) {
			System.out.println("Title: " + book.getTitle() + ". Cost: " + book.getCost());
		}
		System.out.println("---------------------------------------------------------");
		sortedBooks = bookShop.getBooks(BookShop.ORDERBY_ALPHABET);
		for(Book book: sortedBooks) {
			System.out.println("Title: " + book.getTitle() + ". Author: " + book.getAuthor());
		}

		System.out.println("---------------------------------------------------------");
		System.out.println("Sorted book requests");
		
		ArrayList<Request> sortedRequests = bookShop.getBooksRequests(BookShop.ORDERBY_COUNT_EXEMPLARS);
		for(Request request: sortedRequests) {
			System.out.println("Title: " + request.getBook().getTitle() + ". Count requests: " + request.getCountRequests());
		}
		
	}
}