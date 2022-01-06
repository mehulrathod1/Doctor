package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class PatientMedical{

	@SerializedName("Patient Chronic Disease")
	private String patientChronicDisease;

	@SerializedName("Patient Surgery Injury")
	private String patientSurgeryInjury;

	@SerializedName("Patient Allergies")
	private String patientAllergies;

	@SerializedName("Patient medication")
	private String patientMedication;

	public String getPatientChronicDisease(){
		return patientChronicDisease;
	}

	public String getPatientSurgeryInjury(){
		return patientSurgeryInjury;
	}

	public String getPatientAllergies(){
		return patientAllergies;
	}

	public String getPatientMedication(){
		return patientMedication;
	}
}