package com.org.wmdemo;

import java.util.Collections;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 
 * @author Gaurav Rohatgi
 * This class is a thread to clean  the expired
 * EngagedSeat. and it will release up the expired
 * from engaged seats to available seat
 *
 */
public class CleanExpiredSeatHold extends Thread{
	
	private TicketData tData = TicketData.getInstance();
	
	public void run() {
		
		while(true) {
			Date now = new Date();
			for(SeatHold seatHold: tData.getEngagedSeats().values()) {
				long timeDiff=now.getTime()-seatHold.getBookedTime().getTime();
				if(timeDiff>Constants.expireTimeInSeconds) {
					CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
					tData.getEngagedSeats().remove(seatHold.getSeatHoldId());
					for(String seatsToRemove: seatHold.getBookedSeatList()) {
						String s = seatsToRemove.substring(0, 1);
						list.addIfAbsent(s);
						int sNum = Integer.parseInt(seatsToRemove.substring(1,seatsToRemove.length()));
						tData.getAvailableSeats().get(s).add(sNum);
					}
					for(String s: list)
						Collections.sort(tData.getAvailableSeats().get(s));
					TicketService ts = new TicketServiceImpl();
					System.out.println("#####################################");
					System.out.println("Session Expired for SeatHoldId::"+seatHold.getSeatHoldId());
					System.out.println("Total Available Seats now::"+ts.numSeatsAvailable());
					System.out.println("#####################################");
				}
			}
			try {
				Thread.sleep(Constants.sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
