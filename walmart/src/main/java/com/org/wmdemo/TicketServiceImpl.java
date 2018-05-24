package com.org.wmdemo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class TicketServiceImpl implements TicketService{
	
	private TicketData tData = TicketData.getInstance();
	
	private Map<Integer,Integer> seatHoldIds = new ConcurrentHashMap<Integer,Integer>();

	public int numSeatsAvailable() {
		int result = 0;
		for (List<Integer> list : tData.getAvailableSeats().values()) {
			result+=list.size();
		}
		return result;
	}

	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		SeatHold sHold = null;
		int availableSeats = numSeatsAvailable();
		if(numSeats > availableSeats) {
			System.out.println("Expected seats are more than available; please try less than: "+availableSeats);
			return new SeatHold();
		}
		int holdSeats = numSeats;
		sHold = new SeatHold();
		for (String seatRow : tData.getAvailableSeats().keySet()) {
			while(! tData.getAvailableSeats().get(seatRow).isEmpty() && holdSeats>0) {
				sHold.getBookedSeatList().add(seatRow+String.valueOf( tData.getAvailableSeats().get(seatRow).get(0)) );
				 tData.getAvailableSeats().get(seatRow).remove(0);
				holdSeats--;
				if(holdSeats<1)
					break;
			}
		}
		if(holdSeats!=0) {
			System.out.println("Something wrong happens, "+holdSeats+" seats didnt get booked.");
		}
		sHold.setBookedTime(new Date());
		sHold.setCustomerEmail(customerEmail);
		sHold.setSeatHoldId(newSeatHoldId());		
		tData.getEngagedSeats().put(sHold.getSeatHoldId(), sHold);
		return sHold;
	}
	
	private int newSeatHoldId() {
		
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	   int randomNum;
	    do {
	    		randomNum = rand.nextInt((Constants.totalSeats - 1) + 1) + 1;
	    } while(seatHoldIds.containsKey(randomNum));
	    	
	    seatHoldIds.put(randomNum, 1);
	    return randomNum;

	}

	public String reserveSeats(int seatHoldId, String customerEmail) {
		if(!tData.getEngagedSeats().containsKey(seatHoldId))
		{
			return "SeatHoldId not found:"+seatHoldId;
		}
		else {
			SeatHold sHold = tData.getEngagedSeats().get(seatHoldId);
			if(!customerEmail.equals(sHold.getCustomerEmail())) {
				return "--Customer's email didn't matched.";
			}
			tData.getReservedSeats().put(sHold.getSeatHoldId(), sHold);
			tData.getEngagedSeats().remove(seatHoldId);
			return "Your reservation confimed:"+String.valueOf(seatHoldId);
		}
	}
	
	

}
