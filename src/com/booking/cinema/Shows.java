package com.booking.cinema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.booking.person.Buyer;

public class Shows {

	private Rows row;
	private Seats seat;
	Map<String, Boolean> SeatAvailabilityMap;
	private String showNumber;
	private long cancellationWindowLimit;
	private List<Buyer> buyerList; 
	
	public Shows(String showNumber, Rows row, Seats seat, long cancellationWindowSeconds) {
		setRow(row);
		setSeat(seat);
		setShowNumber(showNumber);
		setCancellationWindowLimit(cancellationWindowSeconds);
		SeatAvailabilityMap = new LinkedHashMap<String, Boolean>
																(row.getRowArray().length 
																	* 
																	seat.getSeatArray().length);
		
		for(int i = 0; i < this.getRow().getRowArray().length ; i++) {
			for(int j = 0 ; j < this.getSeat().getSeatArray().length; j++) {
				String rowStr = this.getRow().getRowArray()[i];
				String seatStr = this.getSeat().getSeatArray()[j];
				SeatAvailabilityMap.put(rowStr+seatStr, true);
			}			
		}
		
		buyerList = new ArrayList<Buyer>();
		
	}
	
	private boolean addBuyer(Buyer buyer, String ticketSeat) {
		String telNumber = buyer.getTelNumber();
		Boolean isTicketAvailable = this.setSeatToUnavailable(ticketSeat);
		Tickets ticket = new Tickets(this.getShowNumber(), ticketSeat, this.getCancellationWindowLimit(), telNumber);
		if(isTicketAvailable) {
			buyer.addTicket(ticket);
			buyerList.add(buyer);	
		}
		return true;
	}
	
	public boolean bookTickets(Buyer buyer, String ticketSeats) {
		
		String[] ticketSeatsArray = ticketSeats.split(",");
		
		for(int i=0; i<ticketSeatsArray.length; i++) {
			String ticketSeat = ticketSeatsArray[i];
			this.addBuyer(buyer, ticketSeat);
		}
		
		return true;
	}
	
	public boolean cancelTicket(String ticketNumber, String telNumber) {
		List<Buyer> buyerList = findBuyersByTelNumber(telNumber);
		if(buyerList != null || !buyerList.isEmpty()) {
			for(Buyer buyer: buyerList) {
				removeTicket(buyer, ticketNumber);
			}
			return true;
		}else {
			return false;
		}
	}
	
	
	private List<Buyer> findBuyersByTelNumber(String telNumber) {
		List<Buyer> buyersList = this.getBuyerList();
		List<Buyer> resultList = new ArrayList<Buyer>();
		for(Buyer buyer: buyersList) {
			if(buyer.getTelNumber().equalsIgnoreCase(telNumber)) {
				resultList.add(buyer);
			}
		}
		return resultList;
	}
	
	public boolean removeTicket(Buyer buyer, String ticketNumber) {
		List<Tickets> ticketsList = buyer.getTicketList();
			for(Tickets ticket: ticketsList){
				long timeLeftForCancellation = ticket.getTimeLeftForCancellation();
				if (ticketNumber.equalsIgnoreCase(ticket.getGeneratedTicketNumber()) && timeLeftForCancellation != 0) {
					buyer.removeTicket(ticket);
				}else {
					System.out.println("No cancellation allowed because timeLeftForCancellation is " + timeLeftForCancellation);
					return false;
				}
			}
		return true;
	}
	
	public long getCancellationWindowLimit() {
		return cancellationWindowLimit;
	}

	private void setCancellationWindowLimit(long cancellationWindowLimit) {
		this.cancellationWindowLimit = cancellationWindowLimit;
	}


	//check if Seat is available, available = true, not available / null value is false
	public boolean isSeatAvailable(String seat) {
		return this.getSeatAvailabilityMap().get(seat) != null ? 
				this.getSeatAvailabilityMap().get(seat) : false
				;
	}
	
	public boolean setSeatToUnavailable(String seat) {
		
		Map<String, Boolean> availabilityMap = this.getSeatAvailabilityMap();
		if(isSeatAvailable(seat)) {
			availabilityMap.put(seat, false);
			System.out.println("Seat " + seat + " has been purchased for Show " + this.getShowNumber());
			return true;
		}else {
			System.out.println("Seat " + seat + " is not available for Show " + this.getShowNumber());
			return false;
		}
	}
	
	public Rows getRow() {
		return row;
	}

	public void setRow(Rows row) {
		this.row = row;
	}

	public Seats getSeat() {
		return seat;
	}

	public void setSeat(Seats seat) {
		this.seat = seat;
	}

	
	public Map<String, Boolean> getSeatAvailabilityMap() {
		return SeatAvailabilityMap;
	}

	
	public String getShowNumber() {
		return showNumber;
	}


	public void setShowNumber(String showNumber) {
		this.showNumber = showNumber;
	}
	
	public List<String> getCurrentAvailableSeats() {
		
		List<String> availableSeats = new ArrayList<String>();
		Iterator<Entry<String, Boolean>> it = this.getSeatAvailabilityMap().entrySet().iterator();
		while(it.hasNext()) {
			Entry<String, Boolean> e = it.next();
			if(e.getValue()) {
				availableSeats.add(e.getKey());
			}
		}
		
		return availableSeats;
	}
	
	
	public List<Buyer> getBuyerList() {
		return buyerList;
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[ <<" + getShowNumber() + ">>");
		str.append("\n");
		Iterator<Entry<String, Boolean>> it = this.getSeatAvailabilityMap().entrySet().iterator();
		
		for(int i = 0; i < this.getRow().getRowArray().length ; i++) {
			for(int j = 0 ; j < this.getSeat().getSeatArray().length; j++) {
				Entry<String, Boolean> e = it.next();
				str.append("{");
				str.append(e.toString());
				str.append("}");
				str.append(",");
			}
			str.append("\n");
		}

		str.append("]");
			
		return str.toString();
	}
	
	public static void main(String[] args) {
		
		try {
			Shows show1 = new Shows("123", new Rows(15), new Seats(8), 120);
			Shows show2 = new Shows("12345", new Rows(20), new Seats(10), 150);
			Shows show3 = new Shows("1234567", new Rows(), new Seats(), 180);
			
			Buyer buyerA = new Buyer("12345678");
			Buyer buyerB = new Buyer("11345678");
			Buyer buyerC = new Buyer("22345678");
			
			
			show1.bookTickets(buyerA, "A1,A2,A3,A4");
			show1.bookTickets(buyerB, "A1,A2,A3,A4");
			System.out.println(show1.getCurrentAvailableSeats());
			
			System.out.println(show1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

}
