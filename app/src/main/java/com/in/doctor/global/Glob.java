package com.in.doctor.global;

import android.app.ProgressDialog;
import android.content.Context;

public class Glob {

    public static ProgressDialog dialog;
    public static String Base_Url = "http://ciam.notionprojects.tech/api/doctor/";
    public static String url = "-http://ciam.notionprojects.tech/api/patient/";
    public static String Token = "123456789";
    public static String user_id;
    public static String channel_name;

    public static void progressDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false); // set cancelable to false
        dialog.setMessage("Please Wait"); // set message

    }


}
