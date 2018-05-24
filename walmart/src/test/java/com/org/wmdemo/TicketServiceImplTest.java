package com.org.wmdemo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceImplTest {
	@Mock
	TicketData td;
	
	@InjectMocks
	TicketService ts = new TicketServiceImpl();
	
	@InjectMocks
	CleanExpiredSeatHold ces = new CleanExpiredSeatHold();
	 
	String email = "abc@xyz.com";
	SeatHold sHold = new SeatHold();
	
	@Before
    public void initMocks() throws Exception {
		 Map<String, CopyOnWriteArrayList<Integer>> tempData = new  ConcurrentHashMap<String, CopyOnWriteArrayList<Integer>>();
		 Integer[] arrayInt = {1,2,3,4,5,6,7,8,9};
		 tempData.put("A", new CopyOnWriteArrayList<Integer>(arrayInt));
		 tempData.put("B", new CopyOnWriteArrayList<Integer>(arrayInt));
		 tempData.put("C", new CopyOnWriteArrayList<Integer>(arrayInt));
		 when(td.getAvailableSeats()).thenReturn(tempData);
		 
		Map<Integer, SeatHold> engagedSeatsTest = new ConcurrentHashMap<Integer, SeatHold>();
		int tempId = 100;
		
		sHold.setSeatHoldId(tempId);
		sHold.setBookedTime(new Date());
		sHold.setCustomerEmail("abc@xyz.com");
		List<String> bookedSeatListTest = new ArrayList<String>();
		bookedSeatListTest.add("B1");
		bookedSeatListTest.add("B2");
		bookedSeatListTest.add("B3");
		bookedSeatListTest.add("B4");
		bookedSeatListTest.add("B5");
		sHold.setBookedSeatList(bookedSeatListTest);
		engagedSeatsTest.put(tempId, sHold);
		when(td.getEngagedSeats()).thenReturn(engagedSeatsTest);
	}
	
	@Test
	public void numSeatsAvailableTest() {
		 int i = ts.numSeatsAvailable();
		 assertEquals(27, i);
	}
	
	@Test
	public void findAndHoldSeatsTest() {
		 int noSeats=2;
		 List<String> expectedSeats = new ArrayList<String>();
		 expectedSeats.add("A1");
		 expectedSeats.add("A2");	 
		 SeatHold sh = ts.findAndHoldSeats(noSeats, email);
		 assertEquals(email, sh.getCustomerEmail());
		 assertEquals(expectedSeats, sh.getBookedSeatList());
	}
	
	@Test
	public void reserveSeatsTest() {
		 int expectedReservationId = 100;
		 String i = ts.reserveSeats(expectedReservationId, email);
		 assertEquals("Your reservation confimed:"+expectedReservationId, i);
	}


	
}
