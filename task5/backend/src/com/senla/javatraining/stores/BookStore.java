package com.senla.javatraining.stores;

import com.senla.javatraining.models.Book;
import com.senla.training.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;

/**
 * Data storage operations
 * @author alexander
 *
 */
public class BookStore implements IBookStore{
	private static final String BOOK_FILE = "books.txt";
	private static BookStore instance;
	
	private ArrayList<Book> books;
	
	/* Last generated id */
	private int lastInsertedId;
	private final FileWorker fileWorker;

	public BookStore() {
		this.fileWorker = new TextFileWorker(BOOK_FILE);
		this.books = new ArrayList<Book>(Arrays.asList(this.readFromFile()));
	}

	public static BookStore getInstance() {
    	if (instance == null) {
            instance = new BookStore();
        }
     
        return instance;
    }

	public void addBook(Book book) {
		
		/* Fill Book object fields */
		book.setId(this.generateId());
		book.setCountExemplars(0);
		book.setDateLastReceipt(new Date());

		ArrayList<Book> books = this.getAllBooks();

		books.add(book);
	}

	public void updateBook(Book book) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == book.getId()) {
				books.set(i, book);
			}
		}
	}

	public void deleteBook(Book book) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == book.getId()) {
				books.remove(i);
			}
		}
	}

	public ArrayList<Book> getAllBooks() {
		return this.books;
	}

	public Book getBook(int id) {
		ArrayList<Book> books = this.getAllBooks();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId() == id) {
				return books.get(i);
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
			ArrayList<Book> books = this.getAllBooks();
			if (books == null || books.size() == 0) {
				return 0;
			} else {
				int maxId = 0;
				
				for (int i = 0; i < books.size(); i++) {
					if (books.get(i).getId() > maxId) {
						maxId = books.get(i).getId();
					}
				}
				return maxId;
			}
		}
		
	}
	
	public Book[] readFromFile() {
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
