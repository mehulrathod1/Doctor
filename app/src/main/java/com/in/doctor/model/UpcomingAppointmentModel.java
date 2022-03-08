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


        @SerializedName("patient_name")
        @Expose
        String patient_name;

        @SerializedName("patient Age")
        @Expose
        String patientAge;

        @SerializedName("profile_image")
        @Expose
        String profile_image;

        @SerializedName("fees")
        @Expose
        String fees;

        @SerializedName("coupon_discount")
        @Expose
        String coupon_discount;


        @SerializedName("to_be_paid")
        @Expose
        String to_be_paid;

        @SerializedName("location")
        @Expose
        String location;

        @SerializedName("booking_of")
        @Expose
        String booking_of;

        @SerializedName("status")
        @Expose
        String status;

        @SerializedName("revanue")
        @Expose
        String revanue;


        public Upcoming(String booking_id, String appointment_date, String appointment_time, String patient_id, String patient_name, String patientAge, String profile_image, String fees, String coupon_discount, String to_be_paid, String location, String booking_of) {
            this.booking_id = booking_id;
            this.appointment_date = appointment_date;
            this.appointment_time = appointment_time;
            this.patient_id = patient_id;
            this.patient_name = patient_name;
            this.patientAge = patientAge;
            this.profile_image = profile_image;
            this.fees = fees;
            this.coupon_discount = coupon_discount;
            this.to_be_paid = to_be_paid;
            this.location = location;
            this.booking_of = booking_of;
            this.status = status;
            this.revanue = revanue;
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

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getPatientAge() {
            return patientAge;
        }

        public void setPatientAge(String patientAge) {
            this.patientAge = patientAge;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getCoupon_discount() {
            return coupon_discount;
        }

        public void setCoupon_discount(String coupon_discount) {
            this.coupon_discount = coupon_discount;
        }

        public String getTo_be_paid() {
            return to_be_paid;
        }

        public void setTo_be_paid(String to_be_paid) {
            this.to_be_paid = to_be_paid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getBooking_of() {
            return booking_of;
        }

        public void setBooking_of(String booking_of) {
            this.booking_of = booking_of;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRevanue() {
            return revanue;
        }

        public void setRevanue(String revanue) {
            this.revanue = revanue;
        }
    }
}
