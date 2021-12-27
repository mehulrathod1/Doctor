package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.in.doctor.activity.SignIn;

import java.util.ArrayList;
import java.util.List;

public class SignInModel {

    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    SignInId signInId;

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

    public SignInId getSignInId() {
        return signInId;
    }

    public void setSignInId(SignInId signInId) {
        this.signInId = signInId;
    }

    public class SignInId {
        @SerializedName("id")
        @Expose
        String id;

        @SerializedName("email")
        @Expose
        String email;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}