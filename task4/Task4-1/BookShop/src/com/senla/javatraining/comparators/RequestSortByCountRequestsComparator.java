package com.senla.javatraining.comparators;

import com.senla.javatraining.Request;
import java.util.Comparator;

public class RequestSortByCountRequestsComparator implements Comparator<Request> {
	
	@Override
	public int compare(Request request1, Request request2) {
		return Integer.compare(request1.getCountRequests(), request2.getCountRequests());
	}
}