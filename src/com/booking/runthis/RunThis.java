package com.booking.runthis;

import java.util.Scanner;

import com.booking.cinema.Rows;
import com.booking.cinema.Seats;
import com.booking.cinema.Shows;
import com.booking.person.Admin;
import com.booking.person.Buyer;

public class RunThis {
	static Admin admin = new Admin();
	
	public static String help() {
		return "For usage:\r\n" + "---Admin---\r\n"
				+ "Admin Setup <Show number> <Number of Rows> <Number of Seats per row> <Cancellation window in minutes>\r\n"
				+ "Admin View <Show number>\r\n" 
				+ "\r\n" 
				+ "---Buyer---\r\n" 
				+ "Buyer availability <show number>\r\n"
				+ "Buyer book <Show number> <Phone number> <Seats in comma separated list>\r\n"
				+ "Buyer cancel <Ticket number> <Phone number>"
				+ "\n"
				+ "--------------------------------------------------";
	}


	private static void interpretLine(String line) {
		
		String[] args = line.split("\\s+");
	
		if (args == null || args.length == 0) {
			System.out.println("No input detected. Please enter the following commands: \n\n" + help());
		} 
		
		// Admin commands
		else if (args.length > 0 && args[0].equalsIgnoreCase("Admin")) {

			if (args.length > 1 && args[1].equalsIgnoreCase("View")) {
				if (args.length == 3) {
					String showNumber = args[2];
					System.out.println(admin.view(showNumber));
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
						System.out.println("Setting up... ");
						System.out.println("showNumber="
								+ showNumber
								+ ", row="
								+ rowNumber
								+ ", seat="
								+ seatNumber
								+ ", timelimit="
								+ timelimit);
						admin.setup(showNumber, new Rows(rowNumber), new Seats(seatNumber), timelimit);
						System.out.println("Show "
								+ showNumber
								+ " finished setting up \n");
						System.out.println("current list of shows of admin: ");
						System.out.println(admin.getShowsList());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e);
						System.out.println("Error encountered during 'Admin Setup'. Please refer the following commands: \n\n" + help());
					}
					
				}else {
					System.out.println("'Admin Setup' needs 4 following parameters. Please refer the following commands: \n\n" + help());
				}
			} else {
				System.out.println("Admin only support 'Setup' or 'View'. Please refer the following commands: \n\n" + help());
			}

		} 
		
		// Buyer commands
		
		else if (args.length > 0 && args[0].equalsIgnoreCase("Buyer")) {
			if (args.length > 1 && args[1].equalsIgnoreCase("Availability")) {
				if (args.length == 3) {
					String showNumber = args[2];
					Shows show = admin.findShowByShowNumber(showNumber);
					System.out.println(show.getCurrentAvailableSeats());
				}else {
					System.out.println("'Buyer Availability' only support one parameter. Please refer the following commands: \n\n" + help());
				}
			}else
				if (args.length > 1 && args[1].equalsIgnoreCase("Book")) {
					if(args.length == 5) {
							String showNumber = args[2];
							String telNumber = args[3];
							String ticketSeats = args[4];
							System.out.println("showNumber="
									+ showNumber
									+ ", telNumber="
									+ telNumber
									+ ", ticketSeats="
									+ ticketSeats);
							Buyer buyer = new Buyer(telNumber);
							Shows show = admin.findShowByShowNumber(showNumber);
							boolean isBookingSuccessful = show.bookTickets(buyer, ticketSeats);
							if(isBookingSuccessful) {
								System.out.println("Booking successful. Buyer "+ buyer + "is created");
							}else {
								System.out.println("Booking wasn't successful");
							}
					}else {
						System.out.println("'Buyer Book' needs 3 following parameters. Please refer the following commands: \n\n" + help());
					}
				}else if (args.length > 1 && args[1].equalsIgnoreCase("Cancel")) {
					if(args.length == 4) {
						String ticketNumber = args[2];
						String telNumber = args[3];
						Shows show = admin.findShowByTicketNumber(ticketNumber);
						boolean isCancelSuccessful =show.cancelTicket(ticketNumber, telNumber);
						if(isCancelSuccessful) {
							System.out.println("Cancellation successful.");
						}else {
							System.out.println("Cancellation wasn't successful");
						}
					}else {
						System.out.println("'Buyer Cancel' needs 3 following parameters. Please refer the following commands: \n\n" + help());
					}
				} else {
					System.out.println("Buyer only support 'Availability' or 'Book' or 'Cancel'. Please refer the following commands: \n\n" + help());
				}
			
		} 
		
		
		// Not Admin / Buyer
		else {
			System.out.println("Please prefix command with either 'Admin' or 'Buyer': \n\n" + help());
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println("Please enter your data below: (send 'bye' | 'quit' | 'exit' to exit) ");
		Scanner input = null;
		try{
	    	input = new Scanner(System.in);
	    	System.out.println(help());
		    while (true) {
		        String line = input.nextLine().trim();
		        if ("bye".equalsIgnoreCase(line)) {
		            break;
		        }
		        if ("quit".equalsIgnoreCase(line)) {
		            break;
		        }
		        if ("exit".equalsIgnoreCase(line)) {
		            break;
		        }
		        interpretLine(line);
		    }
	    }catch(Exception e){
	    	System.out.println(e);
	    }finally {
	    	input.close();
	    }

	}


}
