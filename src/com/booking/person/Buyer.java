package com.booking.person;

import java.util.ArrayList;
import java.util.List;

import com.booking.cinema.Tickets;

public class Buyer {

	private List<Tickets> ticketList;
	private String telNumber;
	
	public Buyer(String telNumber) {
		this.ticketList = new ArrayList<Tickets>();
		this.telNumber = telNumber;
	}
	
	public List<Tickets> getTicketList() {
		return ticketList;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public void addTicket(Tickets ticket) {
		// TODO Auto-generated method stub
		this.ticketList.add(ticket);
		System.out.println("buyer with telNumber "+this.getTelNumber() + " trying to purchase "+ ticket.getTicketSeat());
	}
	
	public void removeTicket(Tickets ticket) {
		this.ticketList.remove(ticket);
		System.out.println("buyer with telNumber "+this.getTelNumber() + " has cancelled "+ ticket.getTicketSeat());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
