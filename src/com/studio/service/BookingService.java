package com.studio.service;

import com.studio.bean.*;
import com.studio.dao.BookingDAO;
import com.studio.dao.CustomerDAO;
import com.studio.util.SlotAlreadyBookedException;
import com.studio.util.ValidationException;

import java.util.*;

public class BookingService {
	private CustomerDAO customerDAO = new CustomerDAO();

	public Customer viewCustomerDetails(String customerID) {

		return customerDAO.findCustomer(customerID);
	}

	public List<Customer> viewAllCustomers() {

		return customerDAO.viewAllCustomers();
	}

	public boolean addNewCustomer(Customer customer) throws ValidationException {
		if (customer == null || customer.getCustomerID() == null) {
			throw new ValidationException("Enter Not Null Values");
		}
		return customerDAO.insertCustomer(customer);
	}

	public boolean removeCustomer(String customerID) throws ValidationException {
		if (customerID == null) {
			throw new ValidationException("Enter Valid Data");
		}

		return customerDAO.deleteCustomer(customerID);
	}

	public boolean createBooking(String customerID, Date shootDate, String startTime, String endTime, String packageType) throws ValidationException, SlotAlreadyBookedException {
		if(customerID == null || shootDate == null || shootDate==null || startTime == null ||endTime ==null || packageType == null) {
			throw new ValidationException("Invalid data");
		}
		try {
			BookingDAO boo = new BookingDAO();
			int bookingIdd = boo.generateBookingID();
			Booking booking = new Booking(bookingIdd, customerID, shootDate, startTime, endTime, packageType);
			if(!boo.recordBooking(booking)) {
				throw new SlotAlreadyBookedException("Already booked");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}

	public boolean rescheduleBooking(int bookingID, Date newShootDate, String newStartTime, String newEndTime) throws ValidationException, SlotAlreadyBookedException{
		if(bookingID == 0 || newShootDate == null || newStartTime ==null || newEndTime ==null )  {
			throw new ValidationException("Not a Valid Data");
		}
		BookingDAO boo = new BookingDAO();
		if(!boo.updateBookingSlot(bookingID, (java.sql.Date) newShootDate, newStartTime, newEndTime)) {
			throw new SlotAlreadyBookedException("Already Scheduled");
		}
		return true;
	}

	public boolean cancelBooking(int bookingID) throws ValidationException{
		if(bookingID==0) {
			throw new ValidationException("NoT Found");
		}
		BookingDAO boo = new BookingDAO();
		boo.updateBookingStatus(bookingID, "Cancelled");
		return true;
	}
}
