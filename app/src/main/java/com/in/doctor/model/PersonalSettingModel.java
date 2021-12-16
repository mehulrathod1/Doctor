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
        @SerializedName("first_name")
        @Expose
        String first_name;

        public PersonalSetting(String first_name) {
            this.first_name = first_name;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }
    }
}
