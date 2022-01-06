package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class PatientLifestyle{

	@SerializedName("Patient Sports Involvement")
	private String patientSportsInvolvement;

	@SerializedName("Patient Alchol")
	private String patientAlchol;

	@SerializedName("Patient Smoking")
	private String patientSmoking;

	@SerializedName("Patient Workout level")
	private String patientWorkoutLevel;

	public String getPatientSportsInvolvement(){
		return patientSportsInvolvement;
	}

	public String getPatientAlchol(){
		return patientAlchol;
	}

	public String getPatientSmoking(){
		return patientSmoking;
	}

	public String getPatientWorkoutLevel(){
		return patientWorkoutLevel;
	}
}