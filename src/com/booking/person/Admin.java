package com.booking.person;

import java.util.ArrayList;
import java.util.List;

import com.booking.cinema.Rows;
import com.booking.cinema.Seats;
import com.booking.cinema.Shows;
import com.booking.cinema.Tickets;

public class Admin {
	
	private static List<Shows> showsList;
	
	public Admin() {
		Admin.showsList = new ArrayList<Shows>();
	}
	
	public void setup(String showNumber, Rows row, Seats seat, long cancellationWindowSeconds) {
		Shows show = new Shows(showNumber, row, seat, cancellationWindowSeconds);
		Admin.showsList.add(show);
	}
	
	public List<Shows> getShowsList() {
		return showsList;
	}

	public String view(String showNumber) {
		Shows show = findShowByShowNumber(showNumber);
		if(show != null) {
			return show.view();
		}else {
			return "";
		}
		
	}
	
	public Shows findShowByShowNumber(String showNumber) {
		// TODO Auto-generated method stub
		if(showsList != null || !showsList.isEmpty()) {
			for(Shows show: showsList) {
				if(show.getShowNumber().equalsIgnoreCase(showNumber)) {
					return show;
				}
			}
		}else {
			System.out.println("There's no show setup yet for this showNumber: "+ showNumber);
		}
		return null;
	}
	
	public Shows findShowByTicketNumber(String ticketNumber) {
		// TODO Auto-generated method stub
		if(showsList != null || !showsList.isEmpty()) {
			for(Shows show: showsList) {
				List<Buyer> buyerlist = show.getBuyerList();
				for(Buyer buyer: buyerlist) {
					List<Tickets> ticketlist = buyer.getTicketList();
						for(Tickets ticket: ticketlist){
							if(ticket.getGeneratedTicketNumber().equalsIgnoreCase(ticketNumber)) {
									return show;
								}
						}
				}
			}
		}else {
			System.out.println("There's no show setup yet for this ticketNumber: "+ ticketNumber);
		}
		return null;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
