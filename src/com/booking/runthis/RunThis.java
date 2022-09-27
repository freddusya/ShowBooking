package com.booking.runthis;

import com.booking.cinema.CancellationWindow;
import com.booking.cinema.Rows;
import com.booking.cinema.Seats;
import com.booking.cinema.Shows;

public class RunThis {

	public static String help() {
		return "For usage:\r\n" + "---Admin---\r\n"
				+ "Admin Setup <Show number> <Number of Rows> <Number of Seats per row> <Cancellation window in minutes>\r\n"
				+ "Admin View <Show number>\r\n" 
				+ "\r\n" 
				+ "---Buyer---\r\n" 
				+ "Buyer availability <show number>\r\n"
				+ "Buyer book <Show number> <Phone number> <Seats in comma separated list>\r\n"
				+ "Buyer cancel <Ticket number> <Phone number>";
	}

	public static void main(String[] args) {

		if (args == null || args.length == 0) {
			System.out.println("No input detected. Please enter the following commands: \n\n" + help());
		} else if (args.length > 0 && args[0].equalsIgnoreCase("Admin")) {

			if (args.length > 1 && args[1].equalsIgnoreCase("View")) {
				if (args.length == 3) {
					String showNumber = args[2];
					System.out.println(showNumber);
				}else {
					System.out.println("'Admin View' only support one parameter. Please refer the following commands: \n\n" + help());
				}
			} else
			if (args.length > 1 && args[1].equalsIgnoreCase("Setup")) {
				System.out.println("Verifying parameters...");
				if(args.length == 6) {
					try {
						String showNumber = args[2];
						int rowNumber = Integer.valueOf(args[3]);
						int seatNumber = Integer.valueOf(args[4]);
						long timelimit = Integer.valueOf(args[5]);
						System.out.println("Setting up...");
						Shows newShow = new Shows(showNumber, new Rows(rowNumber), new Seats(seatNumber), timelimit);
						
						System.out.println(newShow);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e);
					}
					
				}else {
					System.out.println("'Admin Setup' needs 4 parameters. Please refer the following commands: \n\n" + help());
				}
			} else {
				System.out.println("Admin only support 'Setup' or 'View'. Please refer the following commands: \n\n" + help());
			}

		} else if (args.length > 0 && args[0].equalsIgnoreCase("Buyer")) {
			for (int i = 0; i < args.length; i++) {
				System.out.println(args[i]);
			}
		} else {
			System.out.println("Please prefix command with either 'Admin' or 'Buyer': \n\n" + help());
		}

	}

}
