package model;

import java.util.ArrayList;


public interface IBooking {

	public ArrayList<Booking> getBookingsByEvent(Event e);
	public void addBooking(Event e, int numSeats, String customerName);
	public void removeBooking(Booking b);
	
}
