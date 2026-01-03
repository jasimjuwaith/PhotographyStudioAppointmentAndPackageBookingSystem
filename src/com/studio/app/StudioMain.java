package com.studio.app;

import java.util.*;
import com.studio.service.*;
import com.studio.util.*;

public class StudioMain {
	private static BookingService bookingService;

	public static void main(String[] args) throws Exception{
		bookingService = new BookingService();
		Scanner sc = new Scanner(System.in);
		System.out.println("--- Photography Studio Booking Console ---");
// TEST 1: Create a booking in a free slot 
		try {
			boolean r = bookingService.createBooking("CUST1001", new Date(), "10:00", "12:00", "PORTRAIT");
			System.out.println(r ? "BOOKING SUCCESS" : "BOOKING FAILED");
		} catch (SlotAlreadyBookedException e) {
			System.out.println("Slot Error: " + e.toString());
		} catch (ValidationException e) {
			System.out.println("Validation Error: " + e.toString());
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
// TEST 2: Reschedule an existing booking 
		try {
			boolean r = bookingService.rescheduleBooking(510001, new Date(), "14:00", "16:00");
			System.out.println(r ? "RESCHEDULE SUCCESS" : "RESCHEDULE FAILED");
		} catch (SlotAlreadyBookedException e) {
			System.out.println("Slot Error: " + e.toString());
		} catch (ValidationException e) {
			System.out.println("Validation Error: " + e.toString());
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
// TEST 3: Cancel a booking 
		try {
			boolean r = bookingService.cancelBooking(510003);
			System.out.println(r ? "CANCEL SUCCESS" : "CANCEL FAILED");
		} catch (ValidationException e) {
			System.out.println("Validation Error: " + e.toString());
		} catch (Exception e) {
			System.out.println("System Error: " + e.getMessage());
		}
		sc.close();
	}
}
