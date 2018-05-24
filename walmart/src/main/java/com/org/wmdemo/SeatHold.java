package com.org.wmdemo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SeatHold implements Serializable {
	
	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;

	private int seatHoldId;
	
	private String customerEmail;
	
	private List<String> bookedSeatList = new ArrayList<String>();
	
	private Date bookedTime;

	public int getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<String> getBookedSeatList() {
		return bookedSeatList;
	}

	public void setBookedSeatList(List<String> bookedSeatList) {
		this.bookedSeatList = bookedSeatList;
	}

	public Date getBookedTime() {
		return bookedTime;
	}

	public void setBookedTime(Date bookedTime) {
		this.bookedTime = bookedTime;
	}
	
	@Override
	public String toString() {
		String result = null;
		result = "HoldId:"+seatHoldId+" Seats::"+this.bookedSeatList.toString();
		return result;
	}

}
