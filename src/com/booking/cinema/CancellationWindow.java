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
		return timeLeft < 0 ? 0 : timeLeft/1000 ;
	}
	

	private void setCancellationWindowLimit(long cancellationWindowlimit) {
		this.cancellationWindowLimit = cancellationWindowlimit*1000;
	}
	
	private long getCancellationWindowLimit() {
		return this.cancellationWindowLimit/1000;
	}

	public String toString() {
		return "[CancellationWindow: Time Left: "
				+ getTimeLeft()
				+ ", isExpired: "
				+ isExpired()
				+ ", CancelationWindowLimit: "
				+ getCancellationWindowLimit()
				+ " seconds]";
	}
	
	public static void main(String[] args) {
		CancellationWindow window = new CancellationWindow(120);
		
		while(true) {
			try {
				System.out.println(window);
				if(window.isExpired()){	break;}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
