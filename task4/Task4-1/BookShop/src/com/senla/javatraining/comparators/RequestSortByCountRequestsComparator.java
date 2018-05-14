package com.senla.javatraining.comparators;

import java.util.Comparator;

import com.senla.javatraining.models.Request;

public class RequestSortByCountRequestsComparator implements Comparator<Request> {
	
	@Override
	public int compare(Request request1, Request request2) {
		return Integer.compare(request1.getCountRequests(), request2.getCountRequests());
	}
}