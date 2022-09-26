package com.booking.cinema;

import java.text.DecimalFormat;
import com.booking.util.RandomStringUtil;

public class Tickets {

	private static long internal_counter = 0;
	private long ticketNumber;
	private String ticketNumberStr;
	private Seats seat;
	private Rows row;
			
	public Tickets(Seats seat, Rows row) {
		
		if(internal_counter > 1000000) {
			internal_counter = 0;
		}
		
		setSeat(seat);
		setRow(row);
		setTicketNumber(internal_counter);
		setTicketNumberStr(6);
		internal_counter++;	
		
	}
	
	public String generateTicketNumber() {
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
		ticketNumberStr = RandomStringUtil.randomStr(length);
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

	public Rows getRow() {
		return row;
	}

	public void setRow(Rows row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "[Ticket: "
				+ this.generateTicketNumber()
				+ ", Row/Seat: "
				+ this.getRow().toString() + this.getSeat().toString() 
				+ "]";
	}

	public static void main(String[] args) {
		Tickets ticket = new Tickets(new Seats(), new Rows());
		Tickets ticket2 = new Tickets(new Seats(), new Rows());
		Tickets ticket3 = new Tickets(new Seats(), new Rows());
		Tickets ticket4 = new Tickets(new Seats(), new Rows());
		Tickets ticket5 = new Tickets(new Seats(), new Rows());
		Tickets ticket6 = new Tickets(new Seats(), new Rows());
		System.out.println(ticket);
		System.out.println(ticket2);
		System.out.println(ticket3);
		System.out.println(ticket4);
		System.out.println(ticket5);
		System.out.println(ticket6);
		
	}

}
