package com.in.doctor.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CommonModel;
import com.in.doctor.model.PersonalSettingModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileSettingPersonal extends Fragment {

    EditText edtFirstName, edtLastName, edtSpeciality, edtSubSpeciality, edtEducation, edtLanguageSpoken, edtExperience, edtAddress, edtAbout;
    TextView txtChoosePhoto;
    Button btnSubmit;
    ImageView profileImage;
    View view;


    File photoFile, img_file;
    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    public String photoFileName = "IMG_" + timeStamp + ".jpg";
    Uri img_url;
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static final int MY_Gallery_REQUEST_CODE = 101;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_profile_setting_personal, container, false);
        init();
        doctorPersonal(Glob.Token, Glob.user_id);
        return view;
    }

    public void init() {
        edtFirstName = view.findViewById(R.id.edtFirstName);
        edtLastName = view.findViewById(R.id.edtLastName);
        edtSpeciality = view.findViewById(R.id.edtSpeciality);
        edtSubSpeciality = view.findViewById(R.id.edtSubSpeciality);
        edtEducation = view.findViewById(R.id.edtEducation);
        edtLanguageSpoken = view.findViewById(R.id.edtLanguageSpoken);
        edtExperience = view.findViewById(R.id.edtExperience);
        edtAddress = view.findViewById(R.id.edtAddress);
        edtAbout = view.findViewById(R.id.edtAbout);
        profileImage = view.findViewById(R.id.profile_image);
        txtChoosePhoto = view.findViewById(R.id.txtChoosePhoto);
        btnSubmit = view.findViewById(R.id.btnSubmit);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new
                        Date());
                img_file = new File(getActivity().getCacheDir(), "IMG_" + timeStamp + ".jpg");

                Bitmap bitmap = null;
                try {
                    if (img_url != null) {
                        bitmap = getBitmap(img_url);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (bitmap != null) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();
                    try {
                        FileOutputStream fos = new FileOutputStream(img_file);
                        fos.write(bitmapdata);
                        fos.flush();
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("image_file", "onClick: " + img_file);
                    updateDoctorPersonal("123456789", Glob.user_id, edtFirstName.getText().toString(),
                            edtLastName.getText().toString(), "1",
                            edtEducation.getText().toString(), edtLanguageSpoken.getText().toString(),
                            edtExperience.getText().toString(), edtAddress.getText().toString(), img_file, edtAbout.getText().toString());
                } else {


                    updateDoctorPersonalNoImage("123456789", Glob.user_id, edtFirstName.getText().toString(),
                            edtLastName.getText().toString(), "1",
                            edtEducation.getText().toString(), edtLanguageSpoken.getText().toString(),
                            edtExperience.getText().toString(), edtAddress.getText().toString(), edtAbout.getText().toString());


                }

            }
        });
        Glob.progressDialog(getContext());

        txtChoosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] options = {"Camera", "Add From Gallery", "Cancel"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(false);
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (options[i].equals("Camera")) {
                            onLaunchCamera();
                        } else if (options[i].equals("Add From Gallery")) {
                            openMediaContent();
                        }
                    }
                });
                builder.show();
            }
        });

    }


    public void updateDoctorPersonal(String token, String doctor_id, String first_name, String last_name,
                                     String specialistid, String education, String language_spoken,
                                     String experience, String address, File Profile_image, String about) {



        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        RequestBody requestBody_token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody requestBody_doctor_id = RequestBody.create(MediaType.parse("multipart/form-data"), doctor_id);
        RequestBody requestBody_first_name = RequestBody.create(MediaType.parse("multipart/form-data"), first_name);
        RequestBody requestBody_last_name = RequestBody.create(MediaType.parse("multipart/form-data"), last_name);
        RequestBody requestBody_specialistid = RequestBody.create(MediaType.parse("multipart/form-data"), specialistid);
        RequestBody requestBody_education = RequestBody.create(MediaType.parse("multipart/form-data"), education);
        RequestBody requestBody_language_spoken = RequestBody.create(MediaType.parse("multipart/form-data"), language_spoken);
        RequestBody requestBody_experience = RequestBody.create(MediaType.parse("multipart/form-data"), experience);
        RequestBody requestBody_address = RequestBody.create(MediaType.parse("multipart/form-data"), address);
        RequestBody requestBody_about = RequestBody.create(MediaType.parse("multipart/form-data"), about);

        MultipartBody.Part requestBody_profile_image = null;
        RequestBody requestBody_req_img = RequestBody.create(MediaType.parse("multipart/form-data"), Profile_image);
        requestBody_profile_image = MultipartBody.Part.createFormData("image", img_file.getName(), requestBody_req_img);


        call.updateDoctorPersonal(requestBody_token, requestBody_doctor_id, requestBody_first_name, requestBody_last_name, requestBody_specialistid,
                requestBody_education, requestBody_language_spoken, requestBody_experience, requestBody_address, requestBody_profile_image, requestBody_about).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel model = response.body();
                Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();
            }
        });


    }

    public void updateDoctorPersonalNoImage(String token, String doctor_id, String first_name, String last_name,
                                            String specialistid, String education, String language_spoken,
                                            String experience, String address, String about) {


        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();


        call.updateDoctorPersonalNoImage(token, doctor_id, first_name, last_name,
                specialistid, education, language_spoken, experience, address, about).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {
                CommonModel model = response.body();
                Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Glob.dialog.dismiss();

            }
        });
    }


    public void doctorPersonal(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.doctorPersonal(token, user_id).enqueue(new Callback<PersonalSettingModel>() {
            @Override
            public void onResponse(Call<PersonalSettingModel> call, Response<PersonalSettingModel> response) {
                PersonalSettingModel profileSettingPersonal = response.body();
                Toast.makeText(getContext(), profileSettingPersonal.getMessage(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(getContext(), profileSettingPersonal.getData().getFirst_name(), Toast.LENGTH_SHORT).show();


                Log.e("jhgfdsa", "onResponse: " + profileSettingPersonal.getData().getProfile_image());
                Glide.with(getContext())
                        .load(profileSettingPersonal.getData().getProfile_image())
                        .into(profileImage);

                edtFirstName.setText(profileSettingPersonal.getData().getFirst_name());
                edtLastName.setText(profileSettingPersonal.getData().getLast_name());
                edtSpeciality.setText(profileSettingPersonal.getData().getSpecialistid());
                edtEducation.setText(profileSettingPersonal.getData().getEducation());
                edtExperience.setText(profileSettingPersonal.getData().getExperience());
                edtLanguageSpoken.setText(profileSettingPersonal.getData().getLanguage_spoken());
                edtAddress.setText(profileSettingPersonal.getData().getAddress());
                Glob.dialog.dismiss();
            }

            @Override
            public void onFailure(Call<PersonalSettingModel> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {



        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onLaunchCamera();
            } else {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == MY_Gallery_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openMediaContent();
            } else {
                Toast.makeText(getContext(), "Don't have permission to access file location", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onLaunchCamera() {


        // create Intent to take a picture and return control to the calling application

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Create a File reference for future access
            photoFile = getPhotoFileUri(photoFileName);
            img_url = Uri.fromFile(photoFile);

            Uri fileProvider = FileProvider.getUriForFile(getContext(), "com.in.doctor.provider", photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                // Start the image capture intent to take photo
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }
    }

    public File getPhotoFileUri(String fileName) {

        File mediaStorageDir = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "APP_TAG");
        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d("APP_TAG", "failed to create directory");
        }
        File file = new File(mediaStorageDir.getPath() + File.separator + fileName);
        return file;
    }

    public void openMediaContent() {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        photoFile = getPhotoFileUri(photoFileName);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri tempFileUri = FileProvider.getUriForFile(getContext(),
                    "com.in.doctor.provider", // As defined in Manifest
                    photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        } else {
            Uri tempFileUri = Uri.fromFile(photoFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, tempFileUri);
        }

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2);
    }

    public Bitmap getBitmap(final Uri selectedimg) throws IOException {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        AssetFileDescriptor fileDescriptor = null;

        fileDescriptor =
                getActivity().getContentResolver().openAssetFileDescriptor(selectedimg, "r");
        Bitmap bitmap
                = BitmapFactory.decodeFileDescriptor(
                fileDescriptor.getFileDescriptor(), null, options);

        options.inSampleSize = calculateInSampleSize(options, 1024, 1024);
        options.inJustDecodeBounds = false;

        Bitmap original
                = BitmapFactory.decodeFileDescriptor(
                fileDescriptor.getFileDescriptor(), null, options);
        System.gc();
        return original;
    }

    public int calculateInSampleSize(@NonNull BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;

        if ((height > reqHeight) && (width > reqWidth)) {
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            inSampleSize++;
        }
        return inSampleSize;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;

        if (resultCode == Activity.RESULT_OK) {
            try {
                switch (requestCode) {
                    case 1034:

                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new
                                Date());
                        img_file = new File(getActivity().getCacheDir(), "IMG_" + timeStamp + ".jpg");

                        bitmap = null;

                        try {
                            if (img_url != null) {
                                bitmap = getBitmap(img_url);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (bitmap != null) {
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                            byte[] bitmapdata = bos.toByteArray();

                            try {
                                FileOutputStream fos = new FileOutputStream(img_file);
                                fos.write(bitmapdata);
                                fos.flush();
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Log.e("img__file", "onClick: " + img_file);

//                            updateDoctorPersonal("123456789", Glob.user_id, edtFirstName.getText().toString(),
//                                    edtLastName.getText().toString(), "1",
//                                    edtEducation.getText().toString(), edtLanguageSpoken.getText().toString(),
//                                    edtExperience.getText().toString(), edtAddress.getText().toString(), img_file);
                            Uri temporary_Image = Uri.fromFile(new File(String.valueOf(img_file)));
                            profileImage.setImageURI(temporary_Image);
                        }
                        break;
                    case 2:
                        if (data.getData() != null) {
//                            post();
                            bitmap = getBitmap(data.getData());
                            img_url = data.getData();

                            timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new
                                    Date());
                            img_file = new File(getContext().getApplicationContext().getCacheDir(), "IMG_" + timeStamp + ".jpg");

                            bitmap = null;

                            try {
                                if (img_url != null) {
                                    bitmap = getBitmap(img_url);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (bitmap != null) {
                                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
                                byte[] bitmapdata = bos.toByteArray();

                                try {
                                    FileOutputStream fos = new FileOutputStream(img_file);
                                    fos.write(bitmapdata);
                                    fos.flush();
                                    fos.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Log.e("img_file", "onClick: " + img_file);
                                Uri temporary_Image = Uri.fromFile(new File(String.valueOf(img_file)));
                                profileImage.setImageURI(temporary_Image);


//                                updateDoctorPersonal("123456789", Glob.user_id, edtFirstName.getText().toString(),
//                                        edtLastName.getText().toString(), "1",
//                                        edtEducation.getText().toString(), edtLanguageSpoken.getText().toString(),
//                                        edtExperience.getText().toString(), edtAddress.getText().toString(), img_file);
                            }

                        }
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}