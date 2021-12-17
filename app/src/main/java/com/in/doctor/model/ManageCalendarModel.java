package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ManageCalendarModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;


    @SerializedName("data")
    @Expose
    List<CalenderModel> calenderList = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CalenderModel> getCalenderList() {
        return calenderList;
    }

    public void setCalenderList(List<CalenderModel> calenderList) {
        this.calenderList = calenderList;
    }

    public static class CalenderModel {

        @SerializedName("booing id")
        @Expose
        String booingId;

        @SerializedName("Doctor Name")
        @Expose
        String DoctorName;

        @SerializedName("Location")
        @Expose
        String Location;

        @SerializedName("Status")
        @Expose
        String Status;

        @SerializedName("Profile")
        @Expose
        String Profile;

        @SerializedName("Fees")
        @Expose
        String Fees;

        public String getBooingId() {
            return booingId;
        }

        public void setBooingId(String booingId) {
            this.booingId = booingId;
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

        public String getStatus() {
            return Status;
        }

        public void setStatus(String status) {
            Status = status;
        }

        public String getProfile() {
            return Profile;
        }

        public void setProfile(String profile) {
            Profile = profile;
        }

        public String getFees() {
            return Fees;
        }

        public void setFees(String fees) {
            Fees = fees;
        }

        public CalenderModel(String booingId, String doctorName, String location, String status, String profile, String fees) {
            this.booingId = booingId;
            DoctorName = doctorName;
            Location = location;
            Status = status;
            Profile = profile;
            Fees = fees;
        }
    }
}