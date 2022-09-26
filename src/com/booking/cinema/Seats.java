package com.booking.cinema;

import com.booking.util.PropertiesConstant;

public class Seats {
	
	private String[] seatArray = {};
	private int limit;
	
	public Seats() throws Exception {
		this(PropertiesConstant.SEATS_MAX);
	}
	
	public Seats(int size) throws Exception {
		setLimit(PropertiesConstant.SEATS_MAX);
		if (size > limit) {
			throw new Exception ("Seat Size too large. The limit of Seats is :" + limit);
		}
		setSeatArray(new String[size]);
		int start = 1; // letter 'A'
		for (int i = 0; i < size; i++) {
			seatArray[i] = String.valueOf(start);
			start++;
		}
	}
	
	public String[] getSeatArray() {
		return seatArray;
	}

	private void setSeatArray(String[] seatArray) {
		this.seatArray = seatArray;
	}

	public int getLimit() {
		return limit;
	}

	private void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append("[");
		for(int i = 0; i < this.seatArray.length ; i++) {
			str.append(seatArray[i]);
			if(i+1 != this.seatArray.length) {
				str.append(",");
			}
		}
		str.append("]");
		
		return str.toString();
	}

	
	public static void main(String[] args) {
		
		try {
			System.out.println(new Seats());
			System.out.println(new Seats(3));
			System.out.println(new Seats(4));
			System.out.println(new Seats(15));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}

}
