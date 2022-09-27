# ShowBooking
Show Booking system implemented in Java

This project contains the following java files:
********************
*com.booking.cinema*
********************
Shows.java
CancellationWindow.java
Rows.java
Seats.java
Tickets.java

Shows.java is the major object which handle most of the logic flow, including booking/canceling and displaying
A new Shows object would comprises of:
1. Rows
2. Seats
3. Buyers (including the tickets they bought)
4. Cancellation Window
5. Seat availability

CancellationWindow.java handles the cancellation window timer. It initiates after a Ticket is purchased.

Rows.java handles the row dimension of the Shows object (maximum 26, from A to Z)

Seats.java handles the Seats dimension of the Shows object (maximum 10, from 1 to 10)

Tickets.java object initiates after a ticket is purchased. It handles the unique ticket number generation and also keep tracks of the cancellation timer.

********************
*com.booking.person*
********************
Admin.java
Buyer.java

Admin can perform two action:
1. Setup  <Show Number> <Number of Rows> <Number of seats per row>  <Cancellation window in minutes>  
2. View <Show Number>   

There's only one Admin and it's created as a new static instance everytime you invoke RunThis.java 

Buyer can perform three action:
1. Availability <Show Number>
2. Book <Show Number> <Phone#> <Comma separated list of seats> 
3. Cancel <Ticket#> <Phone#>

You can have as many Buyer you want, as long as you supply with different Phone# every single booking

********************
*com.booking.util*
********************
PropertiesConstant.java
StringUtil.java

********************
*com.booking.runthis*
********************
RunThis.java

RunThis is the executable to be run.
refer to #To run the code# below for more info.






#To run the code
1. Clone the project into your folder
2. navigate to /src folder
3. compile the code based on the OS you are on

# Linux / MacOS
$ find -name "*.java" > sources.txt

$ javac @sources.txt

# Windows
> dir /s /B *.java > sources.txt

> javac @sources.txt

4. Invoke RunThis.java via the following:
> java com.booking.runthis.RunThis

5. The following will appear:

 -------------------------------------------------------------------------

 Please enter your data below: (send 'bye' | 'quit' | 'exit' to exit)

 For usage:

 ---Admin---

 Admin Setup <Show number> <Number of Rows> <Number of Seats per row> <Cancellation window in minutes>

 Admin View <Show number>


 ---Buyer---

 Buyer availability <show number>

 Buyer book <Show number> <Phone number> <Seats in comma separated list>

 Buyer cancel <Ticket number> <Phone number>

--------------------------------------------------------------------------

6. The program is now up and running, you may test out the features. Use 'bye' | 'quit' | 'exit' to exit



# Assumptions/Constraints:
1. Assume max seats per row is 10 and max rows are 26. Example seat number A1,  H5 etc. 
2. Rows cannot be added beyond the upper limit of 26.
3. Rows.java and Seats.java can be invoke without any size (i.e. new Rows() and new Seats()), which will auto default to the maximum 10 and 26
4. After booking, User can cancel the seats within a time window of <Cancellation window in minutes> (configurable via Admin Setup). Cancellation after that is not allowed.
5. Only ONE booking per phone# is allowed per show. 
7. There's only one Admin, and each new Show created will be stored in the admin object during invocation of RunThis.java
8. You can have as many Buyer you want, as long as you supply with different Phone# for every single booking
