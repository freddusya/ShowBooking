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
		return now.getTime() - getInitialBookingTime().getTime() >= cancellationWindowLimit;
	}
	
	public Date getInitialBookingTime() {
		return initialBookingTime;
	}

	private void setInitialBookingTime() {
		this.initialBookingTime = new Date();
	}

	private long getCancellationWindow() {
		Date now = new Date();	
		return now.getTime() - getInitialBookingTime().getTime();
	}
	
	public long getTimeLeft() {
		long timeLeft = this.cancellationWindowLimit - getCancellationWindow();
		return timeLeft < 0 ? 0 : timeLeft ;
	}
	

	private void setCancellationWindowLimit(long cancellationWindowlimit) {
		this.cancellationWindowLimit = cancellationWindowlimit;
	}

	public String toString() {
		return "[CancellationWindow: Time Left: "
				+ getTimeLeft()
				+ ", isExpired: "
				+ isExpired()
				+ ", CancelationWindowSeconds: "
				+ this.cancellationWindowLimit
				+ "]";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CancellationWindow window = new CancellationWindow(120);
		
		for(int i = 0; i<100; i++) {
			System.out.println(window);
		}
		
	}

}
