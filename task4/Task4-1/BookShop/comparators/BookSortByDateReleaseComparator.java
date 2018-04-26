import java.util.Comparator;

public class BookSortByDateReleaseComparator implements Comparator<Book> {
	
	@Override
	public int compare(Book book1, Book book2) {
		return book1.getDateRelease().compareTo(book2.getDateRelease());
	}
}
