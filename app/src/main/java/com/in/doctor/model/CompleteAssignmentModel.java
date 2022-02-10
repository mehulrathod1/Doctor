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


        public Assignment(String booking_id, String appointment_date, String amount_paid, String patient_id, String patient_document) {
            this.booking_id = booking_id;
            this.appointment_date = appointment_date;
            this.amount_paid = amount_paid;
            this.patient_id = patient_id;
            this.patient_document = patient_document;
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
