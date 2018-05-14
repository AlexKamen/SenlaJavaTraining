package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Request;

public class RequestSortByAlphabetComparator implements Comparator<Request> {
	
	@Override
	public int compare(Request request1, Request request2) {
		return request1.getBook().getTitle().compareTo(request2.getBook().getTitle());
	}
}