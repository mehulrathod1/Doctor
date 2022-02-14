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
        InvoiceModel invoice;


        public Assignment(String booking_id, String appointment_date, String amount_paid, String patient_id, String patient_document, InvoiceModel invoice) {
            this.booking_id = booking_id;
            this.appointment_date = appointment_date;
            this.amount_paid = amount_paid;
            this.patient_id = patient_id;
            this.patient_document = patient_document;
            this.invoice = invoice;
        }

        public InvoiceModel getInvoice() {
            return invoice;
        }

        public void setInvoice(InvoiceModel invoice) {
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

    public static class InvoiceModel {

        @SerializedName("patient_name")
        @Expose
        String patient_name;

        @SerializedName("mobile_number")
        @Expose
        String mobile_number;

        @SerializedName("txn_id")
        @Expose
        String txn_id;

        @SerializedName("amount")
        @Expose
        String amount;

        @SerializedName("txn_date")
        @Expose
        String txn_date;


        public String getPatient_name() {
            return patient_name;
        }

        public void setPatient_name(String patient_name) {
            this.patient_name = patient_name;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getTxn_id() {
            return txn_id;
        }

        public void setTxn_id(String txn_id) {
            this.txn_id = txn_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTxn_date() {
            return txn_date;
        }

        public void setTxn_date(String txn_date) {
            this.txn_date = txn_date;
        }
    }
}
