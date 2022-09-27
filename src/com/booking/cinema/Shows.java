package com.booking.cinema;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.booking.person.Buyer;

public class Shows {

	private Rows row;
	private Seats seat;
	Map<String, Boolean> SeatAvailabilityMap;
	private String showNumber;
	private long cancellationWindowLimit;
	private List<Buyer> buyerList;
	private List<Buyer> exclusionList;
	
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
		exclusionList = new ArrayList<Buyer>();
	}
	
	public String view() {
		StringBuilder sb = new StringBuilder();
		if (this != null ) {
			List<Buyer> buyers = getUniqueBuyerList();
			sb.append("Show Number: " + this.getShowNumber());
			sb.append("\n");
			sb.append("Buyers:");
			sb.append("\n");
			
			int count = 1;
			if (buyers != null || !buyers.isEmpty()) {
			for(Buyer buyer: buyers) {
				List<Tickets> tickets = getTicketsBoughtByBuyerOnThisShowNumber(buyer, this.getShowNumber());
				sb.append(count + ": buyer with tel number: "+buyer.getTelNumber());
				sb.append("\n");
				sb.append("---> tickets bought:");
				sb.append("\n");
				for(Tickets ticket: tickets) {
					sb.append("- Ticket Number:" + ticket.getGeneratedTicketNumber() 
					+ ", Seat Number: "+ ticket.getTicketSeat());
					sb.append("\n");
				}
				count++;
				}
			}else {
				sb.append("No buyers found.");
			}
			sb.append("");
		}
		return sb.toString();
	}
	
	private List<Buyer> getUniqueBuyerList() {
		// TODO Auto-generated method stub
		return this.getBuyerList().stream().distinct().collect(Collectors.toList());
	}

	private List<Tickets> getTicketsBoughtByBuyerOnThisShowNumber(Buyer buyer, String showNumber) {
		
		List<Tickets> ticketList = buyer.getTicketList();
		List<Tickets> resultList = ticketList.stream().filter(t -> t.getShowNumber().equalsIgnoreCase(showNumber)).collect(Collectors.toList());
		
		return resultList;
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
		if(!ifBuyerExistsInExclusionList(buyer, this.exclusionList)) {
			String[] ticketSeatsArray = ticketSeats.split(",");
			
			for(int i=0; i<ticketSeatsArray.length; i++) {
				String ticketSeat = ticketSeatsArray[i];
				this.addBuyer(buyer, ticketSeat);
			}
			
			//if buyer managed to buy tickets, add buyer into exclusion list so they can't buy with same tel number
			//a buyer has a tel number
			if(buyer.getTicketList().size() > 0) { 
				this.exclusionList.add(buyer);
			}
		}else {
			System.out.println("buyer can only buy once with this tel number "+ buyer.getTelNumber());
			return false;
		}
		return true;
	}
	
	private boolean ifBuyerExistsInExclusionList(Buyer buyer, List<Buyer> exclusionList) {
		List<Buyer> list = exclusionList.stream().filter(b -> b.getTelNumber().equalsIgnoreCase(buyer.getTelNumber())).collect(Collectors.toList());
		return list.size() > 0;
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
		List<Buyer> buyersList = getUniqueBuyerList();
		List<Buyer> resultList = new ArrayList<Buyer>();
		for(Buyer buyer: buyersList) {
			if(buyer.getTelNumber().equalsIgnoreCase(telNumber)) {
				resultList.add(buyer);
			}
		}
		return resultList;
	}
	
	public void removeTicket(Buyer buyer, String ticketNumber) {
	
		List<Tickets> ticketsList = buyer.getTicketList();
		List<Tickets> resultList = new ArrayList<Tickets>() ;
			for(Tickets ticket: ticketsList){
				long timeLeftForCancellation = ticket.getTimeLeftForCancellation();
				if (ticketNumber.equalsIgnoreCase(ticket.getGeneratedTicketNumber())) {
					if(timeLeftForCancellation != 0) {
						setSeatToAvailable(ticket.getTicketSeat());
					}else {
						System.out.println("No cancellation allowed because timeLeftForCancellation is " + timeLeftForCancellation);
					}
				}else {
					//tickets which doesnt match ticketNumber, 
					//we add remaining tickets into a list and then they would be the new ticketList
					resultList.add(ticket);
				}
			}
		buyer.setTicketList(resultList);
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
	
	public boolean setSeatToAvailable(String seat) {
		
		Map<String, Boolean> availabilityMap = this.getSeatAvailabilityMap();
		if(!isSeatAvailable(seat)) {
			availabilityMap.put(seat, true);
			System.out.println("Seat " + seat + " has been cancelled for Show " + this.getShowNumber());
			return true;
		}else {
			System.out.println("Seat " + seat + " is not available to be cancelled for Show " + this.getShowNumber());
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
		str.append("[ <<" + getShowNumber() + ">> -- seats availability");
		str.append("\n");
		Iterator<Entry<String, Boolean>> it = this.getSeatAvailabilityMap().entrySet().iterator();
		
		for(int i = 0; i < this.getRow().getRowArray().length ; i++) {
			for(int j = 0 ; j < this.getSeat().getSeatArray().length; j++) {
				Entry<String, Boolean> e = it.next();
				str.append("{");
				str.append(e.getKey() + "=" + displayEntryAvailability(e.getValue()));
				str.append("}");
				str.append(",");
			}
			str.append("\n");
		}

		str.append("]");
			
		return str.toString();
	}
	
	private String displayEntryAvailability(Boolean value) {
		// TODO Auto-generated method stub
		return value ? "Yes" : "No";
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
			
			String buyerA_telNumber = buyerA.getTelNumber();
			String buyerA_ticketNumber = buyerA.getTicketList().get(1).getGeneratedTicketNumber();
			
			System.out.println("cancelling " + buyerA.getTicketList().get(1) + " for " + buyerA);
			show1.cancelTicket(buyerA_ticketNumber, buyerA_telNumber);
			show1.bookTickets(buyerB, "A1,A2,A3,A4");
			show1.bookTickets(buyerA, "B1,B2,B3,B4");
			System.out.println("list of available seats of show1: "+
			show1.getCurrentAvailableSeats());
			
			System.out.println(show1);
			
			System.out.println(show1.view());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

}
