package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UpcomingAppointmentModel {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;


    @SerializedName("data")
    @Expose
    List<Upcoming> upcomingList = new ArrayList<>();

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

    public List<Upcoming> getUpcomingList() {
        return upcomingList;
    }

    public void setUpcomingList(List<Upcoming> upcomingList) {
        this.upcomingList = upcomingList;
    }

    public static class Upcoming {

        @SerializedName("booking_id")
        @Expose
        String booking_id;

        @SerializedName("appointment_date")
        @Expose
        String appointment_date;

        @SerializedName("appointment_time")
        @Expose
        String appointment_time;

        @SerializedName("patient_id")
        @Expose
        String patient_id;


        @SerializedName("booking_of")
        @Expose
        String booking_of;

        public Upcoming(String booking_id, String appointment_date, String appointment_time, String patient_id, String booking_of) {
            this.booking_id = booking_id;
            this.appointment_date = appointment_date;
            this.appointment_time = appointment_time;
            this.patient_id = patient_id;
            this.booking_of = booking_of;
        }

        public String getBooking_of() {
            return booking_of;
        }

        public void setBooking_of(String booking_of) {
            this.booking_of = booking_of;
        }

        public String getBooking_id() {
            return booking_id;
        }

        public void setBooking_id(String booking_id) {
            this.booking_id = booking_id;
        }

        public String getAppointment_date() {
            return appointment_date;
        }

        public void setAppointment_date(String appointment_date) {
            this.appointment_date = appointment_date;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }

        public String getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(String patient_id) {
            this.patient_id = patient_id;
        }
    }
}
