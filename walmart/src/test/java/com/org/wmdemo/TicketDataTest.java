package com.org.wmdemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;

public class TicketDataTest {

	@Mock
	TicketData td = TicketData.getInstance();

	@Test
	public void singletonTest() {
		TicketData tdTemp = TicketData.getInstance();
		assertEquals(td, tdTemp);
	}
	
}
