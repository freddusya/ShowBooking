package com.booking.runthis;

public class RunThis {

	public static String help() {
		return "For usage:\r\n"
				+ "---Admin---\r\n"
				+ "Admin Setup <Show number> <Number of Rows> <Number of Seats per row> <Cancellation window in minutes>\r\n"
				+ "Admin View <Show number>\r\n"
				+ "\r\n"
				+ "---Buyer---\r\n"
				+ "Buyer availability <show number>\r\n"
				+ "Buyer book <Show number> <Phone number> <Seats in comma separated list>\r\n"
				+ "Buyer cancel <Ticket number> <Phone number>";
	}
	
	public static void main(String[] args) {
		
		if(args == null || args.length == 0) {
			 System.out.println("No input detected. Please enter the following commands: \n\n" + help());
		}
		if(args.length > 0 && args[0].equalsIgnoreCase("Admin")) {
			for(int i = 0; i<args.length ; i++) {
				System.out.println(args[i]);
			}
		}
		if(args.length > 0 && args[0].equalsIgnoreCase("Buyer")) {
			for(int i = 0; i<args.length ; i++) {
				System.out.println(args[i]);
			}
		}else {
			System.out.println("Please prefix command with either 'Admin' or 'Buyer': \n\n" + help());
		}
		
		
	}

}
