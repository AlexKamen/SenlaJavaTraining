package com.senla.javatraining.comparators;

import com.senla.javatraining.Request;
import java.util.Comparator;

public class RequestSortByAlphabetComparator implements Comparator<Request> {
	
	@Override
	public int compare(Request request1, Request request2) {
		return request1.getBook().getTitle().compareTo(request2.getBook().getTitle());
	}
}