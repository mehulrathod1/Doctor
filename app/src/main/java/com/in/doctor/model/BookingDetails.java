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

	public void setBookingID(String bookingID){
		this.bookingID = bookingID;
	}

	public String getBookingID(){
		return bookingID;
	}

	public void setBookingDate(String bookingDate){
		this.bookingDate = bookingDate;
	}

	public String getBookingDate(){
		return bookingDate;
	}

	public void setPaymentStatus(String paymentStatus){
		this.paymentStatus = paymentStatus;
	}

	public String getPaymentStatus(){
		return paymentStatus;
	}

	public void setBookingTime(String bookingTime){
		this.bookingTime = bookingTime;
	}

	public String getBookingTime(){
		return bookingTime;
	}

	public void setBookingStatus(String bookingStatus){
		this.bookingStatus = bookingStatus;
	}

	public String getBookingStatus(){
		return bookingStatus;
	}

	public void setFees(String fees){
		this.fees = fees;
	}

	public String getFees(){
		return fees;
	}

	@Override
 	public String toString(){
		return 
			"BookingDetails{" + 
			"booking ID = '" + bookingID + '\'' + 
			",booking Date = '" + bookingDate + '\'' + 
			",payment Status = '" + paymentStatus + '\'' + 
			",booking Time = '" + bookingTime + '\'' + 
			",booking Status = '" + bookingStatus + '\'' + 
			",fees = '" + fees + '\'' + 
			"}";
		}
}