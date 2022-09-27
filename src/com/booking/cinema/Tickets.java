package com.booking.cinema;

import java.text.DecimalFormat;
import com.booking.util.StringUtil;

public class Tickets {

	private static long internal_counter = 0;
	private long ticketNumber;
	private String ticketNumberStr;
	private String ticketSeat;
	private CancellationWindow cancelWindow;
	private String showNumber;
	private String telNumber;
	private String generatedTicketNumber;
	
	public Tickets(String showNumber, String ticketSeat, long cancellationWindowSeconds, String telNumber) {
		
		if(internal_counter > 1000000) {
			internal_counter = 0;
		}
		setShowNumber(showNumber);
		setTicketNumber(internal_counter);
		setTicketNumberStr(6);
		setTicketSeat(ticketSeat);
		setCancelWindow(new CancellationWindow(cancellationWindowSeconds));
		setTelNumber(telNumber);
		internal_counter++;	
	}
	
	private String generateTicketNumber() {
		DecimalFormat ticketnumberFormat = new DecimalFormat("000000");
		setGeneratedTicketNumber(getTicketNumberStr() + ticketnumberFormat.format(getTicketNumber()));
		return getGeneratedTicketNumber();
	}
	
	public long getTimeLeftForCancellation() {
		return getCancelWindow().getTimeLeft();
	}
	
	public CancellationWindow getCancelWindow() {
		return cancelWindow;
	}

	private void setCancelWindow(CancellationWindow cancelWindow) {
		this.cancelWindow = cancelWindow;
	}

	public String getShowNumber() {
		return showNumber;
	}

	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
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

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	private void setGeneratedTicketNumber(String generatedTicketNumber) {
		this.generatedTicketNumber = generatedTicketNumber;
	}
	
	public String getGeneratedTicketNumber() {
		return generatedTicketNumber;
	}
	

	@Override
	public String toString() {
		return "[Ticket: "
				+ this.generateTicketNumber()
				+ ", showNumber: "
				+ this.getShowNumber()
				+ ", Row/Seat: "
				+ this.getTicketSeat().toString()
				+ ", telNumber: "
				+ this.getTelNumber()
				+ ", Cancellation window time left (seconds): "
				+ this.getTimeLeftForCancellation()
				+ "]";
	}

	public static void main(String[] args) {
		
		try {
			Tickets ticket  = new Tickets("A12345", "A1", 120, "123456623");
			Tickets ticket2 = new Tickets("X12345", "B3", 300, "123456623");
			Tickets ticket3 = new Tickets("Z102131", "Z10", 100, "123456623");
			Tickets ticket4 = new Tickets("L23456", "L2", 10, "123456623");
			Tickets ticket5 = new Tickets("Q11231124", "Q5", 500, "123456623");
			Tickets ticket6 = new Tickets("R4DS", "R4", 450, "123456623");
			System.out.println(ticket);
			System.out.println(ticket2);
			System.out.println(ticket3);
			System.out.println(ticket4);
			System.out.println(ticket5);
			System.out.println(ticket6);
			System.out.println(ticket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

		
	}

}
