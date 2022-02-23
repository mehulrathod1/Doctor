package com.in.doctor.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MyReviewModel {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<ReviewData> reviewDataList = new ArrayList<>();

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

    public List<ReviewData> getReviewDataList() {
        return reviewDataList;
    }

    public void setReviewDataList(List<ReviewData> reviewDataList) {
        this.reviewDataList = reviewDataList;
    }

    public static class ReviewData {


        @SerializedName("review_id")
        @Expose
        String review_id;

        @SerializedName("patient_id")
        @Expose
        String patient_id;

        @SerializedName("userName")
        @Expose
        String userName;

        @SerializedName("review")
        @Expose
        String review;


        @SerializedName("rating")
        @Expose
        String rating;

        @SerializedName("date")
        @Expose
        String date;

        @SerializedName("profile_image")
        @Expose
        String profile_image;


        public ReviewData(String review_id, String patient_id, String userName, String review, String rating, String date, String profile_image) {
            this.review_id = review_id;
            this.patient_id = patient_id;
            this.userName = userName;
            this.review = review;
            this.rating = rating;
            this.date = date;
            this.profile_image = profile_image;
        }

        public String getReview_id() {
            return review_id;
        }

        public void setReview_id(String review_id) {
            this.review_id = review_id;
        }

        public String getPatient_id() {
            return patient_id;
        }

        public void setPatient_id(String patient_id) {
            this.patient_id = patient_id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }
    }
}
