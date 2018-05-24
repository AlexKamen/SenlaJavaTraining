package com.senla.javatraining;

import com.senla.javatraining.comparators.*;
import com.senla.javatraining.models.*;
import com.senla.javatraining.stores.*;
import com.senla.javatraining.configuration.Config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

public class BookShop implements IBookShop {
	public static final int ORDERBY_ALPHABET = 1;
	public static final int ORDERBY_DATE_RELEASE = 2;
	public static final int ORDERBY_COST = 3;
	public static final int ORDERBY_DATE_LAST_RECEIPT = 4;
	public static final int ORDERBY_COUNT_EXEMPLARS = 5;
	public static final int ORDERBY_COUNT_REQUESTS = 6;
	public static final int ORDERBY_DATE_CREATION = 7;
	public static final int ORDERBY_DATE_COMPLETION = 8;
	public static final int ORDERBY_TOTAL_PRICE = 9;
	public static final int ORDERBY_STATUS = 10;
	
	private static BookShop instance;
	
	/** Additional number of exemplars in the application */
	public static final int ADDITIONAL_EXEMPLARS = 5;

	private IBookStore bookStore;
	private IOrderStore orderStore;
	private IApplicationStore applicationStore;

	public BookShop() {
		Config config = Config.getInstance();
		try {
			DtoStores dto = this.getDataFromFile();
			
			this.bookStore = BookStore.getInstance();
			this.bookStore.setBooks(dto.getBooks());
			
			this.orderStore = OrderStore.getInstance();
			this.orderStore.setOrders(dto.getOrders());
			
			this.applicationStore = ApplicationStore.getInstance();
			this.applicationStore.setApplications(dto.getApplications());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static BookShop getInstance() {
    	if (instance == null) {
            instance = new BookShop();
        }
     
        return instance;
    }
	
	@Override
	public void addBook(Book book) {
		this.bookStore.addBook(book);
	}

	@Override
	public void updateBook(Book book) {
		this.bookStore.updateBook(book);
	}

	@Override
	public void deleteBook(Book book) {
		this.bookStore.deleteBook(book);
	}

	@Override
	public ArrayList<Book> getBooks(int orderby) {
		ArrayList<Book> books = bookStore.getAllBooks();
		ArrayList<Book> sortedBooks = (ArrayList<Book>) books.clone();
		switch(orderby) {
			case ORDERBY_ALPHABET:
				Collections.sort(sortedBooks, new BookSortByAlphabetComparator());
				break;
			case ORDERBY_DATE_RELEASE:
				Collections.sort(sortedBooks, new BookSortByDateReleaseComparator());
				break;
			case ORDERBY_COST:
				Collections.sort(sortedBooks, new BookSortByCostComparator());
				break;
			case ORDERBY_COUNT_EXEMPLARS:
				Collections.sort(sortedBooks, new BookSortByCountExemplarsComparator());
				break;
			default:
				break;
		}
		
		return sortedBooks;
	}

	@Override
	public Book getBook(int id) {
		return this.bookStore.getBook(id);
	}

	@Override
	public ArrayList<Book> getOldBooks(int orderby) {
		Date nowDate = new Date();
		Calendar nowCal = Calendar.getInstance();
		nowCal.setTime(nowDate);
		
		Calendar bookCal = Calendar.getInstance();
		ArrayList<Book> books = this.getBooks(ORDERBY_ALPHABET);
		ArrayList<Book> oldBooks = new ArrayList<Book>();
		for (Book book : books) {
			bookCal.setTime(book.getDateLastReceipt());
			if ((nowCal.get(Calendar.YEAR) - bookCal.get(Calendar.YEAR)) * 12
					+ (nowCal.get(Calendar.MONTH) - bookCal.get(Calendar.MONTH))
					>= Config.OLD_BOOKS_MONTHS_NUMBER) {
				oldBooks.add(book);
			}
		}
		
		ArrayList<Book> sortedOldBooks = (ArrayList<Book>) oldBooks.clone();
		switch(orderby) {
			case ORDERBY_DATE_LAST_RECEIPT:
				Collections.sort(sortedOldBooks, new BookSortByDateLastReceiptComparator());
				break;
			case ORDERBY_COST:
				Collections.sort(sortedOldBooks, new BookSortByCostComparator());
				break;
			default:
				break;
		}
		return sortedOldBooks;
	}

	@Override
	public void addBookToStock(Book book, int countExemplars) {
		book.setCountExemplars(book.getCountExemplars() + countExemplars);
		bookStore.updateBook(book);
		
		/* Complete the applications */
		if (Config.AUTO_APPLICATION_COMPLETE) {
			ArrayList<Application> applications = this.applicationStore.getAllApplications();  
			for (int i = 0; i < applications.size(); i++) {
				if (applications.get(i).getBook().getId() == book.getId()) {
					applications.get(i).setStatus(Application.STATUS_COMPLETE);
				}
			}
		}
	}

	@Override
	public void deleteBookFromStock(Book book) {
		book.setCountExemplars(0);
		bookStore.updateBook(book);
	}

	@Override
	public ArrayList<Request> getBooksRequests(int orderby) {
		ArrayList<Book> books = this.bookStore.getAllBooks();
		ArrayList<Request> requests = new ArrayList<Request>();
		for (Book book : books) {
			requests.add(new Request(book));
		}

		ArrayList<Request> sortedRequests = (ArrayList<Request>) requests.clone();

		switch (orderby) {
			case ORDERBY_COUNT_REQUESTS:
				Collections.sort(sortedRequests, new RequestSortByCountRequestsComparator());
				break;
			case ORDERBY_ALPHABET:
				Collections.sort(sortedRequests, new RequestSortByAlphabetComparator());
				break;
			default:
				break;
		}

		return sortedRequests;
	}

	@Override
	public void addOrderWithCheckBookExistance(Order order) {
		
		/* Set up default order completion date */
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, Order.MIN_DAYS_TO_COMPLETE);
		Date orderCompletionDate = cal.getTime();
		
		for (OrderItem item : order.getItems()) {
			if (item.getBook().getCountExemplars() < item.getCount()) {
				Application application = generateApplication(item.getBook(), item.getCount());
				order.setStatus(Order.STATUS_NOT_STAFF);
				if (application.getDateCompletion().getTime() > orderCompletionDate.getTime()) {
					orderCompletionDate = application.getDateCompletion();
				}
			}
		}
		
		order.setDateCompletion(orderCompletionDate);
		this.orderStore.addOrder(order);
	}

	private Application generateApplication(Book book, int countExemplars) {
		
		/* Set up application completion date */
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Random random = new Random();
		
		/* The completion time for the application is a random number from 0 to 9 */
		cal.add(Calendar.DATE, random.nextInt(10));
		date = cal.getTime();
		
		Application application = new Application(book,
				countExemplars + ADDITIONAL_EXEMPLARS, date);
		
		this.addApplication(application);

		return application;
	}
	
	@Override
	public void updateOrder(Order order) {
		this.orderStore.updateOrder(order);
	}

	@Override
	public void deleteOrder(Order order) {
		this.orderStore.deleteOrder(order);
	}

	@Override
	public ArrayList<Order> getOrders(Date dateStart, Date dateEnd, int orderby,
			boolean completeOnly) {
		
		ArrayList<Order> allOrders = this.orderStore.getAllOrders();
		ArrayList<Order> neededOrders = new ArrayList<Order>();
		
		/* Get orders selection by date */
		for (Order order : allOrders) {
			if (dateStart == null || dateEnd == null) {
				neededOrders.add(order);
			}
			else {
				if ((order.getDateCreation().getTime() > dateStart.getTime())
					 && (order.getDateCreation().getTime() < dateEnd.getTime())) {
					neededOrders.add(order);
				}
			}
		}

		
		/* Get orders selection by completion */
		if (completeOnly == true) {
			for (int i = 0; i < neededOrders.size(); i++) {
				if (neededOrders.get(i).getStatus() != Order.STATUS_COMPLETE) {
					neededOrders.remove(i);
				}
			}
		}

		/* Sort orders */
		switch (orderby) {
		case ORDERBY_DATE_COMPLETION:
			Collections.sort(neededOrders, new OrderSortByDateCompletionComparator());
			break;
		case ORDERBY_TOTAL_PRICE:
			Collections.sort(neededOrders, new OrderSortByTotalPriceComparator());
			break;
		case ORDERBY_STATUS:
			Collections.sort(neededOrders, new OrderSortByStatusComparator());
			break;
		default:
			break;
	}
		
		return neededOrders;
	}
	
	@Override
	public Order getOrder(int id) {
		return this.orderStore.getOrder(id);
	}

	@Override
	public void completeTheOrder(Order order) {
		order.setStatus(Order.STATUS_COMPLETE);
		this.updateOrder(order);
	}

	@Override
	public void cancelTheOrder(Order order) {
		order.setCanceled(true);
		this.updateOrder(order);
	}

	@Override
	public double getTotalOrdersPrice(Date dateStart, Date dateEnd) {
		ArrayList<Order> orders = getOrders(dateStart, dateEnd, ORDERBY_DATE_COMPLETION, true);
		double totalOrdersPrice = 0;
		for (Order order : orders) {
			for (OrderItem item : order.getItems()) {
				totalOrdersPrice += item.getBook().getCost() * item.getCount();
			}
		}
		
		return totalOrdersPrice;
	}

	@Override
	public int getCompleteOrdersCount(Date dateStart, Date dateEnd) {
		return this.getOrders(dateStart, dateEnd, ORDERBY_DATE_COMPLETION, true).size();
	}

	@Override
	public void addApplication(Application application) {
		this.applicationStore.addApplication(application);
	}

	@Override
	public void updateApplication(Application application) {
		this.applicationStore.updateApplication(application);
	}

	@Override
	public void deleteApplication(Application application) {
		this.applicationStore.deleteApplication(application);
	}
	
	@Override
	public ArrayList<Application> getApplications(boolean actualOnly) {		
		ArrayList<Application> applications = this.applicationStore.getAllApplications();
		
		ArrayList<Application> neededApplications = applications; 
		for (int i = 0; i < applications.size(); i++) {
			if ((actualOnly == true) && (applications.get(i).getStatus() == Application.STATUS_COMPLETE)) {
				neededApplications.remove(i);
			}
		}
		
		return neededApplications;
	}

	@Override
	public Application getApplication(int id) {
		this.applicationStore.getApplication(id);
		return null;
	}

	@Override
	public void completeTheApplication(Application application) {
		application.setStatus(Application.STATUS_COMPLETE);
		this.applicationStore.updateApplication(application);
	}

	@Override
	public void checkOrdersCompletion() {
		ArrayList<Order> orders = this.getOrders(null, null, ORDERBY_DATE_COMPLETION, false);
		Date nowDate = new Date();
		for (Order order : orders) {
			if (order.getDateCompletion().getTime() < nowDate.getTime()) {
				this.completeTheOrder(order);
			}
		}
	}

	@Override
	public void checkApplicationsCompletion() {
		ArrayList<Application> applications = this.getApplications(true);
		Date nowDate = new Date();
		for (Application application : applications) {
			if (application.getDateCompletion().getTime() < nowDate.getTime()) {
				this.completeTheApplication(application);
			}
		}
	}

	@Override
	public DtoStores getDataFromFile() throws IOException {
		FileInputStream fis = new FileInputStream(Config.STORE_FILE_PATH);
		ObjectInputStream oin = new ObjectInputStream(fis);
		try {
			DtoStores dtoStores = (DtoStores) oin.readObject();
			return dtoStores;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			fis.close();
			oin.close();
		}
		
		return null;
	}
	
	@Override
	public void saveDataToFile() throws IOException {
		DtoStores stores = new DtoStores(this.bookStore.getAllBooks(),
				this.orderStore.getAllOrders(),
				this.applicationStore.getAllApplications());
		
		FileOutputStream fos = new FileOutputStream(Config.STORE_FILE_PATH);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(stores);
		oos.flush();
		fos.close();
		oos.close();
	}

	@Override
	public Order copyOrder(Order order) {
		Order newOrder = order.clone();
		OrderItem newOrderItem;
		for (int i = 0; i < order.getItems().size(); i++) {
			newOrderItem = order.getItems().get(i).clone();
			newOrderItem.setBook(order.getItems().get(i).getBook().clone());
			newOrder.getItems().set(i, newOrderItem);
		}
		
		return newOrder;
	}
}
