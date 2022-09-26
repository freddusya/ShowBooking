package com.booking.cinema;

import java.text.DecimalFormat;
import com.booking.util.StringUtil;

public class Tickets {

	private static long internal_counter = 0;
	private long ticketNumber;
	private String ticketNumberStr;
	private String ticketSeat;
			
	public Tickets(String ticketSeat) {
		
		if(internal_counter > 1000000) {
			internal_counter = 0;
		}
		setTicketNumber(internal_counter);
		setTicketNumberStr(6);
		setTicketSeat(ticketSeat);
		internal_counter++;	
		
	}
	
	private String generateTicketNumber() {
		DecimalFormat ticketnumberFormat = new DecimalFormat("000000");
		return getTicketNumberStr() + ticketnumberFormat.format(getTicketNumber());
	}

	public long getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTicketNumberStr() {
		return ticketNumberStr;
	}

	public void setTicketNumberStr(int length) {	    
		ticketNumberStr = StringUtil.randomStr(length);
	}

	public String getTicketSeat() {
		return ticketSeat;
	}

	public void setTicketSeat(String ticketSeat) {
		this.ticketSeat = ticketSeat;
	}

	@Override
	public String toString() {
		return "[Ticket: "
				+ this.generateTicketNumber()
				+ ", Row/Seat: "
				+ this.getTicketSeat().toString()
				+ "]";
	}

	public static void main(String[] args) {
		
		try {
			Tickets ticket  = new Tickets("A1");
			Tickets ticket2 = new Tickets("B3");
			Tickets ticket3 = new Tickets("Z10");
			Tickets ticket4 = new Tickets("L2");
			Tickets ticket5 = new Tickets("Q1");
			Tickets ticket6 = new Tickets("R4");
			System.out.println(ticket);
			System.out.println(ticket2);
			System.out.println(ticket3);
			System.out.println(ticket4);
			System.out.println(ticket5);
			System.out.println(ticket6);
			System.out.println(ticket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
