package com.in.doctor.retrofit;

import com.in.doctor.model.ClinicalSettingModel;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.LifestyleSettingModel;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.model.ManageCalendarModel;
import com.in.doctor.model.OnlineConsultantModel;
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
    Call<ClinicalSettingModel> doctorClinic(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id

    );

    @FormUrlEncoded
    @POST("get_doctor_lifestyle.php")
    Call<LifestyleSettingModel> doctorLifestyle(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id
    );

    @FormUrlEncoded
    @POST("manage_calendar.php")
    Call<ManageCalendarModel> getCalender(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id
    );

    @FormUrlEncoded
    @POST("my_booking_request.php")
    Call<ManageBookingModel> getBookingRequest(

            @Field("token") String token,
            @Field("doctor_id") String doctor_id
    );

    @FormUrlEncoded
    @POST("booking_request_accept.php")
    Call<CommonModel> bookingRequestAccept(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("booking_id") String booking_id
    );

    @FormUrlEncoded
    @POST("booking_request_cancle.php")
    Call<CommonModel> bookingRequestCancel(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("booking_id") String booking_id
    );

    @FormUrlEncoded
    @POST("my_online_consultants.php")
    Call<OnlineConsultantModel> getOnlineConsultant(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id);


    @FormUrlEncoded
    @POST("account_setting.php")
    Call<CommonModel> accountSetting(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("old_password") String old_password,
            @Field("new_password") String new_password
    );


}
