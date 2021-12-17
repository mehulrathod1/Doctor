package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClinicalSettingModel {


    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    ClinicalSetting data;


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

    public ClinicalSetting getData() {
        return data;
    }

    public void setData(ClinicalSetting data) {
        this.data = data;
    }

    public static class ClinicalSetting {
        @SerializedName("doctor_id")
        @Expose
        String doctor_id;

        @SerializedName("clinic_name")
        @Expose
        String clinic_name;

        @SerializedName("clinic_location")
        @Expose
        String clinic_location;

        @SerializedName("open_time")
        @Expose
        String open_time;

        @SerializedName("close_time")
        @Expose
        String close_time;

        @SerializedName("ofline_consultancy_fees")
        @Expose
        String ofline_consultancy_fees;

        @SerializedName("doctor_availability_status")
        @Expose
        String doctor_availability_status;

        @SerializedName("from_to_days")
        @Expose
        String from_to_days;

        public String getDoctor_id() {
            return doctor_id;
        }

        public void setDoctor_id(String doctor_id) {
            this.doctor_id = doctor_id;
        }

        public String getClinic_name() {
            return clinic_name;
        }

        public void setClinic_name(String clinic_name) {
            this.clinic_name = clinic_name;
        }

        public String getClinic_location() {
            return clinic_location;
        }

        public void setClinic_location(String clinic_location) {
            this.clinic_location = clinic_location;
        }

        public String getOpen_time() {
            return open_time;
        }

        public void setOpen_time(String open_time) {
            this.open_time = open_time;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public String getOfline_consultancy_fees() {
            return ofline_consultancy_fees;
        }

        public void setOfline_consultancy_fees(String ofline_consultancy_fees) {
            this.ofline_consultancy_fees = ofline_consultancy_fees;
        }

        public String getDoctor_availability_status() {
            return doctor_availability_status;
        }

        public void setDoctor_availability_status(String doctor_availability_status) {
            this.doctor_availability_status = doctor_availability_status;
        }

        public String getFrom_to_days() {
            return from_to_days;
        }

        public void setFrom_to_days(String from_to_days) {
            this.from_to_days = from_to_days;
        }
    }
}
