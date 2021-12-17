package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class OnlineConsultantModel {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<Consultant> consultantList = new ArrayList<>();

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

    public List<Consultant> getConsultantList() {
        return consultantList;
    }

    public void setConsultantList(List<Consultant> consultantList) {
        this.consultantList = consultantList;
    }

    public static class Consultant {

        @SerializedName("Booking ID")
        @Expose
        String BookingID;

        @SerializedName("Patient Name")
        @Expose
        String PatientName;

        @SerializedName("booking Time")
        @Expose
        String bookingTime;

        @SerializedName("booking Date")
        @Expose
        String bookingDate;

        @SerializedName("Fees")
        @Expose
        String Fees;

        @SerializedName("Location")
        @Expose
        String Location;

        @SerializedName("Status")
        @Expose
        String Status;


        public Consultant(String bookingID, String patientName, String bookingTime, String bookingDate, String fees, String location, String status) {
            BookingID = bookingID;
            PatientName = patientName;
            this.bookingTime = bookingTime;
            this.bookingDate = bookingDate;
            Fees = fees;
            Location = location;
            Status = status;
        }

        public String getBookingID() {
            return BookingID;
        }

        public void setBookingID(String bookingID) {
            BookingID = bookingID;
        }

        public String getPatientName() {
            return PatientName;
        }

        public void setPatientName(String patientName) {
            PatientName = patientName;
        }

        public String getBookingTime() {
            return bookingTime;
        }

        public void setBookingTime(String bookingTime) {
            this.bookingTime = bookingTime;
        }

        public String getBookingDate() {
            return bookingDate;
        }

        public void setBookingDate(String bookingDate) {
            this.bookingDate = bookingDate;
        }

        public String getFees() {
            return Fees;
        }

        public void setFees(String fees) {
            Fees = fees;
        }

        public String getLocation() {
            return Location;
        }

        public void setLocation(String location) {
            Location = location;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }
    }
}
