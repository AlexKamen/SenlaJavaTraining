/**
 * Data processing operations
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author alexander
 *
 */
public class BookService {
	public static final int ORDERBY_ALPHABET = 1;
	public static final int ORDERBY_DATE_RELEASE = 2;
	public static final int ORDERBY_COST = 3;
	public static final int ORDERBY_COUNT_EXEMPLARS = 4;
	public static final int ORDERBY_COUNT_REQUESTS = 5;
	
	private final BookStore bookStore = new BookStore();
	
	public ArrayList<Book> getAllBooks(int orderby) {
		ArrayList<Book> books = new ArrayList<Book>(Arrays.asList(bookStore.getAllBooks()));
		ArrayList<Book> sortedBooks = books;
		switch(orderby) {
			case ORDERBY_ALPHABET:
				System.out.println("Sorted by alphabet");
				Collections.sort(sortedBooks, new BookSortByAlphabetComparator());
				break;
			case ORDERBY_DATE_RELEASE:
				System.out.println("Sorted by date release");
				Collections.sort(sortedBooks, new BookSortByDateReleaseComparator());
				break;
			case ORDERBY_COST:
				System.out.println("Sorted by cost");
				Collections.sort(sortedBooks, new BookSortByCostComparator());
				break;
			case ORDERBY_COUNT_EXEMPLARS:
				System.out.println("Sorted by count exemplars");
				Collections.sort(sortedBooks, new BookSortByCountExemplarsComparator());
				break;
			default:
				break;
		}
		
		return sortedBooks;
	}

	public Book getBook(int idBook) {
		return this.bookStore.getBook(idBook);
	}

	public void addBookToStock(Book book) {
		
	}

	public void deleteBookFromStock(Book book) {
		
	}

	/*public ArrayList<Request> getBooksRequests(orderby: int) {
		
	}

	public ArrayList<Book> getOldBooks(orderby: int) {
		
	}*/
}
