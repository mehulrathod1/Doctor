package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalSettingModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    PersonalSetting data;

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

    public PersonalSetting getData() {
        return data;
    }

    public void setData(PersonalSetting data) {
        this.data = data;
    }

    public static class PersonalSetting {
        @SerializedName("doctor_id")
        @Expose
        String doctor_id;

        @SerializedName("first_name")
        @Expose
        String first_name;


        @SerializedName("last_name")
        @Expose
        String last_name;


        @SerializedName("specialistid")
        @Expose
        String specialistid;


        @SerializedName("education")
        @Expose
        String education;


        @SerializedName("experience")
        @Expose
        String experience;


        @SerializedName("language_spoken")
        @Expose
        String language_spoken;


        @SerializedName("Address")
        @Expose
        String Address;


        @SerializedName("profile_image")
        @Expose
        String profile_image;


        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getSpecialistid() {
            return specialistid;
        }

        public void setSpecialistid(String specialistid) {
            this.specialistid = specialistid;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
        }

        public String getLanguage_spoken() {
            return language_spoken;
        }

        public void setLanguage_spoken(String language_spoken) {
            this.language_spoken = language_spoken;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            Address = address;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }
    }
}
