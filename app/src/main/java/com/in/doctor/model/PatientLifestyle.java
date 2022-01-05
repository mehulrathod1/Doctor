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

	public void setPatientSportsInvolvement(String patientSportsInvolvement){
		this.patientSportsInvolvement = patientSportsInvolvement;
	}

	public String getPatientSportsInvolvement(){
		return patientSportsInvolvement;
	}

	public void setPatientAlchol(String patientAlchol){
		this.patientAlchol = patientAlchol;
	}

	public String getPatientAlchol(){
		return patientAlchol;
	}

	public void setPatientSmoking(String patientSmoking){
		this.patientSmoking = patientSmoking;
	}

	public String getPatientSmoking(){
		return patientSmoking;
	}

	public void setPatientWorkoutLevel(String patientWorkoutLevel){
		this.patientWorkoutLevel = patientWorkoutLevel;
	}

	public String getPatientWorkoutLevel(){
		return patientWorkoutLevel;
	}

	@Override
 	public String toString(){
		return 
			"PatientLifestyle{" + 
			"patient Sports Involvement = '" + patientSportsInvolvement + '\'' + 
			",patient Alchol = '" + patientAlchol + '\'' + 
			",patient Smoking = '" + patientSmoking + '\'' + 
			",patient Workout level = '" + patientWorkoutLevel + '\'' + 
			"}";
		}
}