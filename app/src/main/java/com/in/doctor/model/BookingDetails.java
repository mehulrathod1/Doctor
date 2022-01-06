package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class BookingDetails{

	@SerializedName("Booking ID")
	private String bookingID;

	@SerializedName("Booking Date")
	private String bookingDate;

	@SerializedName("Payment Status")
	private String paymentStatus;

	@SerializedName("Booking Time")
	private String bookingTime;

	@SerializedName("Booking Status")
	private String bookingStatus;

	@SerializedName("Fees")
	private String fees;

	public String getBookingID(){
		return bookingID;
	}

	public String getBookingDate(){
		return bookingDate;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public String getBookingTime(){
		return bookingTime;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public String getFees(){
		return fees;
	}
}