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

	public void setPatientChronicDisease(String patientChronicDisease){
		this.patientChronicDisease = patientChronicDisease;
	}

	public String getPatientChronicDisease(){
		return patientChronicDisease;
	}

	public void setPatientSurgeryInjury(String patientSurgeryInjury){
		this.patientSurgeryInjury = patientSurgeryInjury;
	}

	public String getPatientSurgeryInjury(){
		return patientSurgeryInjury;
	}

	public void setPatientAllergies(String patientAllergies){
		this.patientAllergies = patientAllergies;
	}

	public String getPatientAllergies(){
		return patientAllergies;
	}

	public void setPatientMedication(String patientMedication){
		this.patientMedication = patientMedication;
	}

	public String getPatientMedication(){
		return patientMedication;
	}

	@Override
 	public String toString(){
		return 
			"PatientMedical{" + 
			"patient Chronic Disease = '" + patientChronicDisease + '\'' + 
			",patient Surgery Injury = '" + patientSurgeryInjury + '\'' + 
			",patient Allergies = '" + patientAllergies + '\'' + 
			",patient medication = '" + patientMedication + '\'' + 
			"}";
		}
}