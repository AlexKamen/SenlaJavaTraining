/**
 * Request for books for feeling the stock
 */
package com.senla.javatraining.models;

import java.io.Serializable;
import java.util.Date;

/**
 * @author alexander
 *
 */
public class Application implements Serializable {
	private static final long serialVersionUID = 2436815846943783247L;
	
	public static final int STATUS_CREATE = 1;
	public static final int STATUS_COMPLETE = 2;
	
	private int idApplication;

	private Book book;
	
	/** Number of exemplars in application */
	private int countExemplar;

	private Date dateCreation;
	
	/** Date when books will be received */
	private Date dateCompletion;
	
	/** Int value of application status received from class constant */
	private int status;

	public Application() {
		
	}
	
	public Application(Book book, int countExemplars, Date dateCompletion) {
		this.book = book;
		this.countExemplar = countExemplars;
		this.dateCompletion = dateCompletion;
		this.status = STATUS_CREATE;
	}

	public Application(Book book, int countExemplars, Date dateCompletion, int status) {
		this.book = book;
		this.countExemplar = countExemplars;
		this.dateCompletion = dateCompletion;
		this.status = status;
	}

	public void setId(int id) {
		this.idApplication = id;
	}
	
	public int getId() {
		return this.idApplication;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public Book getBook() {
		return this.book;
	}

	public void setCountExemplar(int countExemplar) {
		this.countExemplar = countExemplar;
	}

	public int getCountExemplar() {
		return countExemplar;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCompletion(Date dateCompletion) {
		this.dateCompletion = dateCompletion;
	}
	
	public Date getDateCompletion() {
		return this.dateCompletion;
	}

	public void setStatus(int newStatus) {
		this.status = newStatus;
	}

	public int getStatus() {
		return this.status;
	}
}
