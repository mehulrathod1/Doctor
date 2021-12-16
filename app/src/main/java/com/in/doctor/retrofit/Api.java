package com.in.doctor.retrofit;

import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.model.SignInModel;
import com.in.doctor.model.SignUpModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("signup.php")
    Call<SignUpModel> signUp(

            @Field("token") String token,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("country_code") String country_code,
            @Field("mobile_no") String mobile_no,
            @Field("password") String password,
            @Field("confirm_password") String confirm_password
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<SignInModel> SignIn(
            @Field("token") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("get_doctor_personal_details.php")
    Call<PersonalSettingModel> doctorPersonal(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id

    );

    @FormUrlEncoded
    @POST("get_doctor_clinic.php")
    Call<ClinicSettingModel> doctorClinical(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id

    );

    @FormUrlEncoded
    @POST("get_doctor_lifestyle.php")
    Call<PersonalSettingModel> doctorLifestyle(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id

    );
}
