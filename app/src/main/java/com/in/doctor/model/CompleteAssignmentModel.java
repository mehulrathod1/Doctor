package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CompleteAssignmentModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;


    @SerializedName("data")
    @Expose
    List<Assignment> completeAssignment = new ArrayList<>();

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

    public List<Assignment> getCompleteAssignment() {
        return completeAssignment;
    }

    public void setCompleteAssignment(List<Assignment> completeAssignment) {
        this.completeAssignment = completeAssignment;
    }

    public static class Assignment {

        @SerializedName("booking_id")
        @Expose
        String booking_id;


        @SerializedName("appointment_date")
        @Expose
        String appointment_date;


        @SerializedName("ammount_paid")
        @Expose
        String amount_paid;

        @SerializedName("patient_id")
        @Expose
        String patient_id;


        @SerializedName("patient_document")
        @Expose
        String patient_document;

        @SerializedName("invoice")
        @Expose
        String invoice;


        @SerializedName("booking_of")
        @Expose
        String booking_of;

        @SerializedName("patient_name")
        @Expose
        String patient_name;

        @SerializedName("appointment_time")
        @Expose
        String appointment_time;



        public Assignment(String booking_id, String appointment_date, String amount_paid, String patient_id, String patient_document, String invoice, String booking_of, String patient_name, String appointment_time) {
            this.booking_id = booking_id;
            this.appointment_date = appointment_date;
            this.amount_paid = amount_paid;
            this.patient_id = patient_id;
            this.patient_document = patient_document;
            this.invoice = invoice;
            this.booking_of = booking_of;
            this.patient_name = patient_name;
            this.appointment_time = appointment_time;
        }

        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getBooking_of() {
            return booking_of;
        }

        public void setBooking_of(String booking_of) {
            this.booking_of = booking_of;
        }

        public String getAppointment_time() {
            return appointment_time;
        }

        public void setAppointment_time(String appointment_time) {
            this.appointment_time = appointment_time;
        }




        public String getInvoice() {
            return invoice;
        }

        public void setInvoice(String invoice) {
            this.invoice = invoice;
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

        public String getAmount_paid() {
            return amount_paid;
        }

        public void setAmount_paid(String amount_paid) {
            this.amount_paid = amount_paid;
        }

        public String getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(String patient_id) {
            this.patient_id = patient_id;
        }

        public String getPatient_document() {
            return patient_document;
        }

        public void setPatient_document(String patient_document) {
            this.patient_document = patient_document;
        }
    }

}
