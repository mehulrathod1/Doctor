package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class PatientPersonal{

	@SerializedName("Patient Gender")
	private String patientGender;

	@SerializedName("Patient Blood group")
	private String patientBloodGroup;

	@SerializedName("Patient Email")
	private String patientEmail;

	@SerializedName("Patient Id")
	private String patientId;

	@SerializedName("Patient Age")
	private String patientAge;

	@SerializedName("Patient Marital Status")
	private String patientMaritalStatus;

	@SerializedName("Patient Name")
	private String patientName;

	@SerializedName("Patient No")
	private String patientNo;

	@SerializedName("Patient Image")
	private String patientImage;

	@SerializedName("Patient Address")
	private String patientAddress;

	public String getPatientGender(){
		return patientGender;
	}

	public String getPatientBloodGroup(){
		return patientBloodGroup;
	}

	public String getPatientEmail(){
		return patientEmail;
	}

	public String getPatientId(){
		return patientId;
	}

	public String getPatientAge(){
		return patientAge;
	}

	public String getPatientMaritalStatus(){
		return patientMaritalStatus;
	}

	public String getPatientName(){
		return patientName;
	}

	public String getPatientNo(){
		return patientNo;
	}

	public String getPatientImage(){
		return patientImage;
	}

	public String getPatientAddress(){
		return patientAddress;
	}
}