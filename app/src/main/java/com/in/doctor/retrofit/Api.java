package com.in.doctor.retrofit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.in.doctor.model.ChatDashboardModel;
import com.in.doctor.model.ChatModel;
import com.in.doctor.model.ClinicalSettingModel;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.CompleteAssignmentModel;
import com.in.doctor.model.GetFcmTokenModel;
import com.in.doctor.model.LifestyleSettingModel;
import com.in.doctor.model.ManageBookingModel;
import com.in.doctor.model.ManageCalendarModel;
import com.in.doctor.model.MyReviewModel;
import com.in.doctor.model.OnlineConsultantModel;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.model.ReportModel;
import com.in.doctor.model.RevenueModel;
import com.in.doctor.model.SendNotificationModel;
import com.in.doctor.model.SignInModel;
import com.in.doctor.model.SignUpModel;
import com.in.doctor.model.UpcomingAppointmentModel;
import com.in.doctor.model.ViewPatientDetailModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @POST("update_doctor_lifestyle.php")
    Call<CommonModel> updateDoctorLifestyle(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("smoking") String smoking,
            @Field("alchol") String alchol,
            @Field("workout_level") String workout_level,
            @Field("sports_involvement") String sports_involvement

    );

    @FormUrlEncoded
    @POST("update_doctor_clinic.php")
    Call<CommonModel> updateDoctorClinic(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("clinic_name") String clinic_name,
            @Field("clinic_location") String clinic_location,
            @Field("open_time") String open_time,
            @Field("close_time") String close_time,
            @Field("ofline_consultancy_fees") String ofline_consultancy_fees,
            @Field("from_to_days") String from_to_days,
            @Field("doctor_availability_status") String doctor_availability_status
    );

    @Multipart
    @POST("update_doctor_personal_details.php")
    Call<CommonModel> updateDoctorPersonal(
            @Part("token") RequestBody token,
            @Part("doctor_id") RequestBody doctor_id,
            @Part("first_name") RequestBody first_name,
            @Part("last_name") RequestBody last_name,
            @Part("specialistid") RequestBody specialistid,
            @Part("education") RequestBody education,
            @Part("language_spoken") RequestBody language_spoken,
            @Part("experience") RequestBody experience,
            @Part("address") RequestBody address,
            @Part MultipartBody.Part image,
            @Part("about_me") RequestBody about

    );

    @Multipart
    @POST("upload_clinic_images.php")
    Call<CommonModel> uploadClinicImage(
            @Part("token") RequestBody token,
            @Part("doctor_id") RequestBody doctor_id,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST("update_doctor_personal_details.php")
    Call<CommonModel> updateDoctorPersonalNoImage(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("specialistid") String specialistid,
            @Field("education") String education,
            @Field("language_spoken") String language_spoken,
            @Field("experience") String experience,
            @Field("address") String address,
            @Field("about_me") String about_me
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

    @FormUrlEncoded
    @POST("deactive_account.php")
    Call<CommonModel> deleteAccount(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id

    );

    @FormUrlEncoded
    @POST("get_patient_view_booking_details.php")
    Call<ViewPatientDetailModel> patientDetail(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("booking_id") String booking_id

    );

    @FormUrlEncoded
    @POST("add_fcm_token.php")
    Call<CommonModel> addFcmToken(

            @Field("token") String token,
            @Field("doctor_id") String user_id,
            @Field("fcm_token") String fcm_token
    );

    @FormUrlEncoded
    @POST("get_fcm_token.php")
    Call<GetFcmTokenModel> getFcmToken(
            @Field("token") String token,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("http://ciam.notionprojects.tech/api/patient/send_notification.php")
    Call<SendNotificationModel> sendNotification(
            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("message") String message
    );

    @Multipart
    @POST("upload_patient_report.php")
    Call<CommonModel> uploadPatientReport(
            @Part("token") RequestBody token,
            @Part("doctor_id") RequestBody doctor_id,
            @Part("patient_id") RequestBody patient_id,
            @Part("booking_id") RequestBody booking_id,
            @Part MultipartBody.Part doctor_report

    );


    @FormUrlEncoded
    @POST("completed_assignment.php")
    Call<CompleteAssignmentModel> getCompletedAssignment(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id);


    @FormUrlEncoded
    @POST("get_patient_reports.php")
    Call<ReportModel> getPatientReport(

            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("booking_id") String booking_id
    );


    @FormUrlEncoded
    @POST("get_chat_user_list.php")
    Call<ChatModel> getChatList(

            @Field("token") String token,
            @Field("doctor_id") String user_id

    );

    @FormUrlEncoded
    @POST("get_chat_messages.php")
    Call<ChatDashboardModel> getChatMessage(

            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("add_doctor_chat.php")
    Call<CommonModel> sendMessage(

            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("user_id") String user_id,
            @Field("msg_type") String msg_type,
            @Field("message") String message
    );

    @Multipart
    @POST("add_doctor_chat.php")
    Call<CommonModel> sendImageInChat(

            @Part("token") RequestBody token,
            @Part("doctor_id") RequestBody doctor_id,
            @Part("user_id") RequestBody user_id,
            @Part("msg_type") RequestBody msg_type,
            @Part MultipartBody.Part image
    );


    @FormUrlEncoded
    @POST("get_rating_reviews.php")
    Call<MyReviewModel> getReview(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id
    );

    @FormUrlEncoded
    @POST("get_upcoming_booking.php")
    Call<UpcomingAppointmentModel> getAppointment(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id
    );

    @FormUrlEncoded
    @POST("get_completed_revenue.php")
    Call<RevenueModel> getRevenue(@Field("token") String token, @Field("doctor_id") String doctor_id);

    @FormUrlEncoded
    @POST("send_consultancy_completed.php")
    Call<CommonModel> sendConsultantConformation(
            @Field("token") String token,
            @Field("doctor_id") String doctor_id,
            @Field("booking_id") String booking_id
    );
}
