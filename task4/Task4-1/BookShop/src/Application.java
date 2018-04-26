/**
 * Request for books for feeling the stock
 */
import java.util.Date;

/**
 * @author alexander
 *
 */
public class Application {
	private Book book;
	
	/** Number of exemplars in application */
	private int countExemplar;
	private Date dateCreation;
	
	/** Date when books will be received */
	private Date dateCompletion;
	
	/** Int value of application status received from class constant */
	private int status;

	public Application(Book book, int countExemplars, Date dateCompletion) {
		this.book = book;
		this.countExemplar = countExemplars;
		this.dateCompletion = dateCompletion;
	}

	public Book getBook() {
		return this.book;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Date getDateCreation() {
		return this.dateCreation;
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
