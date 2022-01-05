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

	public void setPatientGender(String patientGender){
		this.patientGender = patientGender;
	}

	public String getPatientGender(){
		return patientGender;
	}

	public void setPatientBloodGroup(String patientBloodGroup){
		this.patientBloodGroup = patientBloodGroup;
	}

	public String getPatientBloodGroup(){
		return patientBloodGroup;
	}

	public void setPatientEmail(String patientEmail){
		this.patientEmail = patientEmail;
	}

	public String getPatientEmail(){
		return patientEmail;
	}

	public void setPatientId(String patientId){
		this.patientId = patientId;
	}

	public String getPatientId(){
		return patientId;
	}

	public void setPatientAge(String patientAge){
		this.patientAge = patientAge;
	}

	public String getPatientAge(){
		return patientAge;
	}

	public void setPatientMaritalStatus(String patientMaritalStatus){
		this.patientMaritalStatus = patientMaritalStatus;
	}

	public String getPatientMaritalStatus(){
		return patientMaritalStatus;
	}

	public void setPatientName(String patientName){
		this.patientName = patientName;
	}

	public String getPatientName(){
		return patientName;
	}

	public void setPatientNo(String patientNo){
		this.patientNo = patientNo;
	}

	public String getPatientNo(){
		return patientNo;
	}

	public void setPatientImage(String patientImage){
		this.patientImage = patientImage;
	}

	public String getPatientImage(){
		return patientImage;
	}

	public void setPatientAddress(String patientAddress){
		this.patientAddress = patientAddress;
	}

	public String getPatientAddress(){
		return patientAddress;
	}

	@Override
 	public String toString(){
		return 
			"PatientPersonal{" + 
			"patient Gender = '" + patientGender + '\'' + 
			",patient Blood group = '" + patientBloodGroup + '\'' + 
			",patient Email = '" + patientEmail + '\'' + 
			",patient Id = '" + patientId + '\'' + 
			",patient Age = '" + patientAge + '\'' + 
			",patient Marital Status = '" + patientMaritalStatus + '\'' + 
			",patient Name = '" + patientName + '\'' + 
			",patient No = '" + patientNo + '\'' + 
			",patient Image = '" + patientImage + '\'' + 
			",patient Address = '" + patientAddress + '\'' + 
			"}";
		}
}