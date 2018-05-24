package com.org.wmdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CleanExpiredSeatHoldTest {

	@Mock
	TicketData td;
	
	@InjectMocks
	TicketService ts = new TicketServiceImpl();
	
	@InjectMocks
	CleanExpiredSeatHold ces = new CleanExpiredSeatHold();
	 
	String email = "abc@xyz.com";
	SeatHold sHold = new SeatHold();
	
	
	@Test
	public void reserveSeatsSessionExpiredTest() throws InterruptedException {
		 ces.start();
		 Thread.sleep(31000);
		 String i = ts.reserveSeats(sHold.getSeatHoldId(), email);
		 assertEquals("SeatHoldId not found:"+sHold.getSeatHoldId(), i);
	}
}
