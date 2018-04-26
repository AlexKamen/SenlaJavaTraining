/**
 * 
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Alexander
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
		
		/* Stores */
		BookStore bookStore = new BookStore();
		OrderStore orderStore = new OrderStore();
		ApplicationStore applicationStore = new ApplicationStore();
		
		/* Services */
		BookService bookService = new BookService();
		OrderService orderService = new OrderService();
		ApplicationService applicationService = new ApplicationService();
		
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
		
		/* BOOKS */
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

		// Add book
		bookStore.addBook(book1);
		bookStore.addBook(book2);
		bookStore.addBook(book3);
		bookStore.addBook(book4);
		bookStore.addBook(book5);
		// Update book
		Book bookTmp = bookStore.getBook(4);
		bookTmp.setCountExemplars(50);
		bookStore.updateBook(bookTmp);
		// Delete book
		bookTmp = bookStore.getBook(5);
		bookStore.deleteBook(bookTmp);
		
		/*ORDERS*/
		// Initialize orders
		Order order1 = new Order(order1DateCompletion);
		order1.addItem(new OrderItem(bookStore.getBook(1), 2));
		order1.addItem(new OrderItem(bookStore.getBook(2), 1));
		order1.addItem(new OrderItem(bookStore.getBook(3), 5));

		Order order2 = new Order(order2DateCompletion);
		order2.addItem(new OrderItem(bookStore.getBook(3), 1));
		order2.addItem(new OrderItem(bookStore.getBook(2), 1));
		order2.addItem(new OrderItem(bookStore.getBook(4), 1));
		
		Order order3 = new Order(order3DateCompletion);
		order3.addItem(new OrderItem(bookStore.getBook(1), 30));
		
		Order order4 = new Order(order4DateCompletion);
		order4.addItem(new OrderItem(bookStore.getBook(2), 2));

		// Add orders
		orderStore.addOrder(order1);
		orderStore.addOrder(order2);
		orderStore.addOrder(order3);
		orderStore.addOrder(order4);

		Order orderTmp = orderStore.getOrder(3);
		orderTmp.setCanceled(true);
		orderStore.updateOrder(orderTmp);
		
		orderTmp = orderStore.getOrder(4);
		orderStore.deleteOrder(orderTmp);

		/* COMPARATORS DEMONSTRATION */
		ArrayList<Book> sortedBooks = bookService.getAllBooks(bookService.ORDERBY_COST);
		for(Book book: sortedBooks) {
			System.out.println(book.getCost());
		}

		sortedBooks = bookService.getAllBooks(bookService.ORDERBY_ALPHABET);
		for(Book book: sortedBooks) {
			System.out.println(book.getTitle());
		}
	}
}