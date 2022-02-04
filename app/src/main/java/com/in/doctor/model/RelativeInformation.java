package com.in.doctor.model;

import com.google.gson.annotations.SerializedName;

public class RelativeInformation {
    @SerializedName("Relative Name")
    private String RelativeName;

    @SerializedName("Relative Age")
    private String RelativeAge;

    @SerializedName("Relative Gender")
    private String RelativeGender;

    @SerializedName("Relative Blood group")
    private String RelativeBloodGroup;

    @SerializedName("Relative Marital Status")
    private String RelativeMaritalStatus;

    @SerializedName("Relation")
    private String Relation;

    public String getRelativeName() {
        return RelativeName;
    }

    public void setRelativeName(String relativeName) {
        RelativeName = relativeName;
    }

    public String getRelativeAge() {
        return RelativeAge;
    }

    public void setRelativeAge(String relativeAge) {
        RelativeAge = relativeAge;
    }

    public String getRelativeGender() {
        return RelativeGender;
    }

    public void setRelativeGender(String relativeGender) {
        RelativeGender = relativeGender;
    }

    public String getRelativeBloodGroup() {
        return RelativeBloodGroup;
    }

    public void setRelativeBloodGroup(String relativeBloodGroup) {
        RelativeBloodGroup = relativeBloodGroup;
    }

    public String getRelativeMaritalStatus() {
        return RelativeMaritalStatus;
    }

    public void setRelativeMaritalStatus(String relativeMaritalStatus) {
        RelativeMaritalStatus = relativeMaritalStatus;
    }

    public String getRelation() {
        return Relation;
    }

    public void setRelation(String relation) {
        Relation = relation;
    }
}
