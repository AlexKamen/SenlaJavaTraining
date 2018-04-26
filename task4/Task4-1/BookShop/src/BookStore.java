import com.senla.training.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class BookStore {
	private static final String BOOK_FILE = "books.txt";
	
	private Book[] books;
	
	/* Last generated id */
	private int lastInsertedId;
	private final FileWorker fileWorker;

	public BookStore() {
		fileWorker = new TextFileWorker(BOOK_FILE);
	}

	public void addBook(Book book) {
		
		/* Fill Book object fields */
		book.setId(this.generateId());
		book.setCountExemplars(0);
		book.setDateLastReceipt(new Date());
		
		/* Some manipulations for add new book to existance */
		Book[] books = this.getAllBooks();
		ArrayList<Book> result = new ArrayList<Book>();
		if (!(books == null || books.length == 0)) {
			result = new ArrayList(Arrays.asList(books));
		}

		result.add(book);
		this.writeToFile(result.toArray(new Book[result.size()]));
	}

	public void updateBook(Book book) {
		Book[] books = this.getAllBooks();
		for (int i = 0; i < books.length; i++) {
			if (books[i].getId() == book.getId()) {
				books[i] = book;
			}
		}

		this.writeToFile(books);
	}

	public void deleteBook(Book book) {
		Book[] books = this.getAllBooks();
		ArrayList<Book> result = new ArrayList<Book>(Arrays.asList(books));
		for (int i = 0; i < books.length; i++) {
			if (books[i].getId() == book.getId()) {
				result.remove(i);
			}
		}

		this.writeToFile(result.toArray(new Book[result.size()]));
	}

	public Book[] getAllBooks() {
		Book[] books = this.readFromFile();
		return books;
	}

	public Book getBook(int id) {
		Book[] books = this.getAllBooks();
		for (int i = 0; i < books.length; i++) {
			if (books[i].getId() == id) {
				return books[i];
			}
		}

		return null;
	}

	private int generateId() {
		this.lastInsertedId = this.getLastId() + 1;
		return this.lastInsertedId;
	}

	private int getLastId() {
		if (this.lastInsertedId != 0) {
			return this.lastInsertedId;
		}
		else {
			Book[] books = this.getAllBooks();
			if (books == null || books.length == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < books.length; i++) {
					if (books[i].getId() > maxId) {
						maxId = books[i].getId();
					}
				}
				return maxId;
			}
		}
		
	}
	
	private Book[] readFromFile() {
		final String[] lines = fileWorker.readFromFile();
		
		if (lines == null || lines.length == 0) {
			return null;
		}
		
		final Book[] result = new Book[lines.length];
		
		for (int i = 0; i < lines.length; i++) {
			result[i] = parseFileLine(lines[i]);
		}
		
		return result;
	}

	public void writeToFile(final Book[] values) {
		if (values == null || values.length == 0) {
			throw new IllegalArgumentException();
		}
		final String[] lines = new String[values.length];
		for (int i = 0; i < values.length; i++) {
			lines[i] = collectFileLine(values[i]);
		}
		fileWorker.writeToFile(lines);
	}

	private String collectFileLine(final Book book) {
		if (book == null) {
			throw new IllegalArgumentException();
		}

		final String[] objectFields = new String[] { 
				String.valueOf(book.getId()), 
				book.getTitle(),
				book.getAuthor(),
				String.valueOf(book.getDateRelease().getTime()),
				String.valueOf(book.getCost()),
				String.valueOf(book.getCountExemplars()),
				String.valueOf(book.getDateLastReceipt().getTime())
			};
		return String.join(";", objectFields);
	}
	
	private Book parseFileLine(final String line) {
		if (line == null || line.isEmpty()) {
			throw new IllegalArgumentException();
		}

		final String[] parts = line.split(";");

		final Book book = new Book(
				parts[1],
				parts[2],
				new Date(Long.valueOf(parts[3])),
				Double.valueOf(parts[4])
			);
		book.setId(Integer.valueOf(parts[0]));
		book.setCountExemplars(Integer.valueOf(parts[5]));
		book.setDateLastReceipt(new Date(Long.valueOf(parts[6])));
		return book;
	}
}
