package com.in.doctor.model;

public class ManageBookingModel {

    String BookingId,OrderDate,BookingOf,BookingTime;

    public ManageBookingModel(String bookingId, String orderDate, String bookingOf, String bookingTime) {
        BookingId = bookingId;
        OrderDate = orderDate;
        BookingOf = bookingOf;
        BookingTime = bookingTime;
    }

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String orderDate) {
        OrderDate = orderDate;
    }

    public String getBookingOf() {
        return BookingOf;
    }

    public void setBookingOf(String bookingOf) {
        BookingOf = bookingOf;
    }

    public String getBookingTime() {
        return BookingTime;
    }

    public void setBookingTime(String bookingTime) {
        BookingTime = bookingTime;
    }
}

