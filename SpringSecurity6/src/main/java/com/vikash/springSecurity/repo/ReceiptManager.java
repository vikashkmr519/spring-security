package com.vikash.springSecurity.repo;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class ReceiptManager {
	// this will manage the authorization token generated after success key matching
	
	private Set<String> receiptNoList;
	
	public ReceiptManager() {
		receiptNoList = new HashSet<>();
	}
	
	public void add(String receipt) {
		receiptNoList.add(receipt);
	}
	
	public boolean contains(String receipt) {
		return receiptNoList.contains(receipt);
	}

}
