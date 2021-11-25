package com.in.doctor.model;

public class BookedAppointmentModel {

    String BookingId,DoctorName,Location,Amount,profileImage;

    public String getBookingId() {
        return BookingId;
    }

    public void setBookingId(String bookingId) {
        BookingId = bookingId;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String doctorName) {
        DoctorName = doctorName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public BookedAppointmentModel(String bookingId, String doctorName, String location, String amount, String profileImage) {
        BookingId = bookingId;
        DoctorName = doctorName;
        Location = location;
        Amount = amount;
        this.profileImage = profileImage;
    }
}
