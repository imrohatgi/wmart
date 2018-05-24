package com.org.wmdemo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Gaurav Rohatgi
 * This class is a Main for Demo purpose only
 * Based on menu it will execute the different 
 * functions and it will start the thread to 
 * cleanup the expired seatholds.
 *
 */
public class MainBookTickets {
	static Scanner scanner = new Scanner(System.in);
	private static MainBookTickets mbt = new MainBookTickets();
	private static TicketService ts = new TicketServiceImpl();
	private static TicketData tData = TicketData.getInstance();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		CleanExpiredSeatHold cesh = new CleanExpiredSeatHold();
		cesh.start();
		while(true) {
			try {
				System.out.println("---------------------------------------------------------------------\nPlease note at any stage you can get to main menu @email:'cancel' , @HoldId/@Seats:0 \n---------------------------------------------------------------------");
				System.out.println("Total Available Seats now:"+ts.numSeatsAvailable() +"\nMain Menu:: \n1-New booking\n2-Reserve booking\n3-SummaryView\n0-Exit");
		        int input =scanner.nextInt();
		        switch(input) {
		        case 0: 
		        		System.exit(0);
		        case 1:
		        		mbt.newBooking();
		        		break;
		        case 2: 
		        		mbt.reserveBooking();
		        		break;
		        case 3:
		        		tData.getView();
		        		break;
		        	default:
		        		System.out.println("Wrong Option chosen.");
		        		break;
		        }
				System.out.println("---------------------------------------------------------\n");	        
			}
			catch(Exception e) {
				scanner.nextLine();
				System.out.println("Please enter only numbers based on provided menu.");
			}
		}
	}
	
	/**
	 * it will take input from console for no of seats 
	 * & email of cusotmer for new booking.
	 */
	public void newBooking() {
		System.out.print("Enter no of seats:");
        int noSeats=scanner.nextInt();
        scanner.nextLine(); //This is needed to pick up the new line
        if(noSeats==0)
        		return;
        String email = getEmail();
        if(email==null)
        		return;
        System.out.println("Holding seats....");
        SeatHold sh = ts.findAndHoldSeats(noSeats, email);
        if(!sh.getBookedSeatList().isEmpty()) {
	        System.out.println("Your seats hold with following details. \nPlease confirm with in "+Constants.expireTimeInSeconds/1000+" seconds to reserve else it will expire.\n"+sh.toString());
	        System.out.println("Hold @ "+sh.getBookedTime());
        }
	}
	
	/**
	 * it will take input from console for seatHoldId  
	 * & email of customer to confirm the hold booking.
	 */
	public void reserveBooking() {
		System.out.print("Enter HoldId:");
        int holdId=scanner.nextInt();
        scanner.nextLine(); //This is needed to pick up the new line
        if(holdId==0)
    			return;
        String email = getEmail();
        if(email==null)
    			return;
        System.out.println(ts.reserveSeats(holdId, email));
	}
	
	/**
	 * its  common method which take email from console an d
	 * @return back to the caller.
	 */
	private String getEmail() {
		String email;
		while(true) {
	        System.out.print("Enter email:");
	        email=scanner.nextLine();
	        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
	        Matcher mat = pattern.matcher(email);
	
	        if(!mat.matches()){
	        		if(email.equalsIgnoreCase("cancel")) {
	        			return null;
	        		}
	            System.out.println("Not a valid email address");
	        }
	        else 
	        		break;
        }
        return email;
	}
	
}
