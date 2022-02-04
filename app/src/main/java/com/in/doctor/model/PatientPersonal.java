package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class PatientPersonal {


    @SerializedName("Patient Document")
    private String PatientDocument;

    @SerializedName("Patient Comments")
    private String PatientComments;


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

    public String getPatientDocument() {
        return PatientDocument;
    }

    public void setPatientDocument(String patientDocument) {
        PatientDocument = patientDocument;
    }

    public String getPatientComments() {
        return PatientComments;
    }

    public void setPatientComments(String patientComments) {
        PatientComments = patientComments;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientBloodGroup() {
        return patientBloodGroup;
    }

    public void setPatientBloodGroup(String patientBloodGroup) {
        this.patientBloodGroup = patientBloodGroup;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(String patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientMaritalStatus() {
        return patientMaritalStatus;
    }

    public void setPatientMaritalStatus(String patientMaritalStatus) {
        this.patientMaritalStatus = patientMaritalStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(String patientImage) {
        this.patientImage = patientImage;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }
}