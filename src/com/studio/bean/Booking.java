package com.studio.bean;

import java.util.*;

public class Booking {
	private int bookingID;
	private String customerId;
	private Date shootDate;
	private String startTime;
	private String endTime;
	private String packageType;
	
	public Booking(int bookingID, String customerId, Date shootDate, String startTime, String endTime,
			String packageType) {
		super();
		this.bookingID = bookingID;
		this.customerId = customerId;
		this.shootDate = shootDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.packageType = packageType;
	}
	public Booking() {
	}
	public int getBookingId() {
		return bookingID;
	}
	public void setBookingId(int bookingId) {
		this.bookingID = bookingId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getShootDate() {
		return shootDate;
	}
	public void setShootDate(Date shootDate) {
		this.shootDate = shootDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPackageType() {
		return packageType;
	}
	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	}
