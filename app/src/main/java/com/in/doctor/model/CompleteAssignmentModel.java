package com.in.doctor.model;

public class CompleteAssignmentModel {

    String BookingId,AppointmentDate,AmountPaid;

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getAmountPaid() {
        return AmountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        AmountPaid = amountPaid;
    }

    public CompleteAssignmentModel(String bookingId, String appointmentDate, String amountPaid) {
        BookingId = bookingId;
        AppointmentDate = appointmentDate;
        AmountPaid = amountPaid;
    }
}
