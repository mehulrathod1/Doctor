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

    public static class LifestyleSetting {

        @SerializedName("status")
        @Expose
        String status;


    }
}
