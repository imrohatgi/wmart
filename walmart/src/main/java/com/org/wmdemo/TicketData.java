package com.org.wmdemo;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class TicketData {
	
	private  static TicketData tData = new TicketData();
	
	private Map<String, CopyOnWriteArrayList<Integer>>  availableSeats = new ConcurrentHashMap<String, CopyOnWriteArrayList<Integer>> ();
	
	private Map<Integer, SeatHold> engagedSeats = new ConcurrentHashMap<Integer, SeatHold>();
	
	private Map<Integer, SeatHold> reservedSeats = new ConcurrentHashMap<Integer, SeatHold>();
	
	
	public Map<String, CopyOnWriteArrayList<Integer>> getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Map<String, CopyOnWriteArrayList<Integer>> availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Map<Integer, SeatHold> getEngagedSeats() {
		return engagedSeats;
	}

	public void setEngagedSeats(Map<Integer, SeatHold> engagedSeats) {
		this.engagedSeats = engagedSeats;
	}

	public Map<Integer, SeatHold> getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(Map<Integer, SeatHold> reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	private TicketData() {		
		for (int i=0;i<Constants.seatRow.length; i++) {
			CopyOnWriteArrayList<Integer> seatNumbers = new CopyOnWriteArrayList<Integer>();
			for (int j=1;j<=Constants.rowLimit; j++) {
				seatNumbers.add(j);
			}
			Constants.totalSeats+=Constants.rowLimit;
			availableSeats.put(String.valueOf(Constants.seatRow[i]), seatNumbers);
		}
	}
	
	public static TicketData getInstance() {
		return tData;
	}
	
	public void getView() {
		System.out.println("*****************"+new Date()+"********************");

		System.out.println("Total Holding::");
		for(SeatHold seatHold: tData.engagedSeats.values()) {
			System.out.println(seatHold.toString());
		}
		System.out.println("Available Seats::");
		for(String col: tData.availableSeats.keySet()) {
			System.out.println("Row:"+col+"::"+tData.availableSeats.get(col));
		}
		System.out.println("Reserved::");
		for(SeatHold seatHold: tData.reservedSeats.values()) {
			System.out.println(seatHold.toString());
		}
		System.out.println("*************************************");
		
}

}
