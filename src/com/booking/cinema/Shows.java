package com.booking.cinema;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Shows {

	private Rows row;
	private Seats seat;
	Map<String, Boolean> SeatAvailabilityMap;
	
	
	public Shows(Rows row, Seats seat) {
		setRow(row);
		setSeat(seat);
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

	}
	
	
	//check if Seat is available, available = true, not available / null value is false
	public boolean isSeatAvailable(String seat) {
		return this.getSeatAvailabilityMap().get(seat) != null ? 
				this.getSeatAvailabilityMap().get(seat) : false
				;
	}
	
	
	public String buySeat(String seat) {
		
		Map<String, Boolean> availabilityMap = this.getSeatAvailabilityMap();
		if(isSeatAvailable(seat)) {
			availabilityMap.put(seat, false);
			return seat + " has been purchased";
		}else {
			return seat + " is not available";
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

	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
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
			Shows show = new Shows(new Rows(15), new Seats(8));
			
			
			System.out.println(show.buySeat("J3"));
			System.out.println(show.buySeat("Q2"));
			System.out.println(show.buySeat("F6"));
			System.out.println(show.buySeat("C8"));
			System.out.println(show.isSeatAvailable("Z10"));
			System.out.println(show.isSeatAvailable("A3"));
			System.out.println(show.isSeatAvailable("J3"));
			
			System.out.println(show);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}

	}

}
