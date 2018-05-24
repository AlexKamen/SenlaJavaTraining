package com.senla.javatraining.ui.actions;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.senla.javatraining.BookShop;
import com.senla.javatraining.IBookShop;
import com.senla.javatraining.models.Book;
import com.senla.javatraining.models.Order;
import com.senla.javatraining.models.OrderItem;
import com.senla.javatraining.ui.Scan;

public class CopyOrder implements IAction {	
	public static final Logger logger = Logger.getLogger(CopyOrder.class.getName());
	private Scan scanner;
	private IBookShop bookShop;
	
	public CopyOrder() {
		this.scanner = Scan.getInstance();
		this.bookShop = BookShop.getInstance();
	}
	
	@Override
	public void execute() {
		System.out.println("Select order to copy (enter the ID)");
		ArrayList<Order> orders = this.bookShop.getOrders(null, null,
				BookShop.ORDERBY_STATUS, false);
		
		for (Order order : orders) {
			System.out.print(order.getId() + " - ");
			for (OrderItem item : order.getItems()) {
				System.out.print(item.getBook().getTitle() + ", ");
			}
			System.out.println();
		}
		
		int orderId = this.scanner.getIntValue();
		
		try {
			Order order = this.bookShop.getOrder(orderId);
			Order newOrder = this.bookShop.copyOrder(order);
			
			while (true) {
				System.out.println("Change books in order");
				System.out.println("0 - finish of items change");
				System.out.println("1 - delete items");
				System.out.println("2 - add items");
				
				int operation = this.scanner.getIntValue();
				if (operation == 0) {
					break;
				} else {
					/* remove elements */
					if (operation == 1) {
						ArrayList<OrderItem> orderItems = newOrder.getItems();
						for (OrderItem item : orderItems) {
							System.out.println(item.getBook().getId()
									+ " - " + item.getBook().getAuthor() + ". "
									+ item.getBook().getTitle());
						}
						
						int delBookId = this.scanner.getIntValue();
						
						for (int i = 0; i < orderItems.size(); i++) {
							if (orderItems.get(i).getBook().getId() == delBookId) {
								orderItems.remove(i);
							}
						}
					} else {
						/* add elements */
						ArrayList<Book> books = this.bookShop.getBooks(BookShop.ORDERBY_ALPHABET);
						
						for (Book book : books) {
							System.out.println(book.getId() + " - " + book.getAuthor() + ". "
									+ book.getTitle());
						}
						int bookId = this.scanner.getIntValue();
						
						System.out.println("Enter count exemplars");
						int countExemplars = this.scanner.getIntValue();
						
						OrderItem orderItem = new OrderItem(this.bookShop.getBook(bookId),
								countExemplars);
						try {
							/* Check book existance */
							orderItem.getBook().getId();
							newOrder.getItems().add(orderItem);
						} catch(NullPointerException e) {
							System.out.println("Book not found");
							logger.error("User entered incorrect book number", e);
						}
					}
				}
			}
			
			this.bookShop.addOrderWithCheckBookExistance(newOrder);
		} catch(NullPointerException e) {
			System.out.println("Order not found");
			logger.error("User entered incorrect order number", e);
		}

		
	}

}
