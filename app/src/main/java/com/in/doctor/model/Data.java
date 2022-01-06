package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("Patient Personal")
	private PatientPersonal patientPersonal;

	@SerializedName("Patient Medical")
	private PatientMedical patientMedical;

	@SerializedName("Patient Lifestyle")
	private PatientLifestyle patientLifestyle;

	@SerializedName("Booking Details")
	private BookingDetails bookingDetails;

	public PatientPersonal getPatientPersonal(){
		return patientPersonal;
	}

	public PatientMedical getPatientMedical(){
		return patientMedical;
	}

	public PatientLifestyle getPatientLifestyle(){
		return patientLifestyle;
	}

	public BookingDetails getBookingDetails(){
		return bookingDetails;
	}
}