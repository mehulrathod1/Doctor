package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LifestyleSettingModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    LifestyleSetting data;


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

    public LifestyleSetting getData() {
        return data;
    }

    public void setData(LifestyleSetting data) {
        this.data = data;
    }

    public static class LifestyleSetting {

        @SerializedName("doctor_id")
        @Expose
        String doctor_id;


        @SerializedName("smoking")
        @Expose
        String smoking;

        @SerializedName("alchol")
        @Expose
        String alchol;

        @SerializedName("workout_level")
        @Expose
        String workout_level;

        @SerializedName("sports_involvement")
        @Expose
        String sports_involvement;

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getAlchol() {
            return alchol;
        }

        public void setAlchol(String alchol) {
            this.alchol = alchol;
        }

        public String getWorkout_level() {
            return workout_level;
        }

        public void setWorkout_level(String workout_level) {
            this.workout_level = workout_level;
        }

        public String getSports_involvement() {
            return sports_involvement;
        }

        public void setSports_involvement(String sports_involvement) {
            this.sports_involvement = sports_involvement;
        }
    }
}
