package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ManageBookingModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<ManageBookingModel.Booking> dataList = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Booking> getDataList() {
        return dataList;
    }

    public void setDataList(List<Booking> dataList) {
        this.dataList = dataList;
    }

    public static class Booking {

        @SerializedName("Booking Id")
        @Expose
        String BookingId;

        @SerializedName("Booking Date")
        @Expose
        String BookingDate;

        @SerializedName("Booking Time")
        @Expose
        String BookingTime;

        @SerializedName("Boking of")
        @Expose
        String BookingOf;


        public Booking(String bookingId, String bookingDate, String bookingTime, String bookingOf) {
            BookingId = bookingId;
            BookingDate = bookingDate;
            BookingTime = bookingTime;
            BookingOf = bookingOf;
        }

        public String getBookingId() {
            return BookingId;
        }

        public void setBookingId(String bookingId) {
            BookingId = bookingId;
        }

        public String getBookingDate() {
            return BookingDate;
        }

        public void setBookingDate(String bookingDate) {
            BookingDate = bookingDate;
        }

        public String getBookingTime() {
            return BookingTime;
        }

        public void setBookingTime(String bookingTime) {
            BookingTime = bookingTime;
        }

        public String getBookingOf() {
            return BookingOf;
        }

        public void setBookingOf(String bookingOf) {
            BookingOf = bookingOf;
        }
    }
}

