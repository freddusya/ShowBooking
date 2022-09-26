package com.booking.cinema;

import java.util.Date;

public class CancellationWindow {

	private Date initialBookingTime;
	private Long cancellationWindowLimit;
	
	
	public CancellationWindow(long seconds){
		this.setInitialBookingTime();
		this.setCancellationWindowLimit(seconds);		
	}
		
	public boolean isExpired() {
		
		Date now = new Date();		
		return now.getTime() - getInitialBookingTime().getTime() <= cancellationWindowLimit;
	}
	
	public Date getInitialBookingTime() {
		return initialBookingTime;
	}

	private void setInitialBookingTime() {
		this.initialBookingTime = new Date();
	}

	public long getCancellationWindow() {
		Date now = new Date();	
		return now.getTime() - getInitialBookingTime().getTime();
	}
	
	public long getTimeLeft() {
		return this.cancellationWindowLimit - getCancellationWindow();
	}
	

	private void setCancellationWindowLimit(long cancellationWindowlimit) {
		this.cancellationWindowLimit = cancellationWindowlimit;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CancellationWindow window = new CancellationWindow(120);
		
		for(int i = 0; i<1000; i++) {
			System.out.println(window.getCancellationWindow());
			System.out.println(window.getTimeLeft());
			System.out.println(window.isExpired());
		}
		
	}

}
