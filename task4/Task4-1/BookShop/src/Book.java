import java.util.Date;

/**
 * Book Model
 * @author Alexander
 *
 */
public class Book {
	private int idBook;
	private String title;
	private String author;
	private Date dateRelease;
	private double cost;
	
	/** Number of exemplars at the moment in stock */
	private int countExemplars;
	
	/** Date of last receipt of books in stock */
	private Date dateLastReceipt;
	
	public Book(String title, String author, Date dateRelease, double cost) {
		this.title = title;
		this.author = author;
		this.dateRelease = dateRelease;
		this.cost = cost;
	}

	public void setId(int newId) {
		this.idBook = newId;
	}

	public int getId() {
		return this.idBook;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAuthor() {
		return this.author;
	}

	public Date getDateRelease() {
		return this.dateRelease;
	}

	public void setCost(int newCost) {
		this.cost = newCost;
	}
	
	public double getCost() {
		return this.cost;
	}

	public void setCountExemplars(int newCount) {
		this.countExemplars = newCount;
	}

	public int getCountExemplars() {
		return this.countExemplars;
	}

	public void setDateLastReceipt(Date dateReceipt) {
		this.dateLastReceipt = dateReceipt;
	}

	public Date getDateLastReceipt() {
		return this.dateLastReceipt;
	}
	
}
