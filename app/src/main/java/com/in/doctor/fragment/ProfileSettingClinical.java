package com.in.doctor.fragment;

import static com.in.doctor.global.Glob.Token;

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
import android.widget.Toast;

import com.in.doctor.R;
import com.in.doctor.global.Glob;
import com.in.doctor.model.ClinicalSettingModel;
import com.in.doctor.model.CommonModel;
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


public class ProfileSettingClinical extends Fragment {

    EditText edtClinicName, edtClinicLocation, edtFromToDays, edtOpenTime, edtCloseTime, edtConsultancyFees, edtAvailabilityStatus;
    Button btnSubmit, btn_upload_image;
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
        view = inflater.inflate(R.layout.fragment_profile_setting_clinical, container, false);

        init();
        doctorClinic(Token, Glob.user_id);
        return view;
    }

    public void init() {

        edtClinicName = view.findViewById(R.id.edtClinicName);
        edtClinicLocation = view.findViewById(R.id.edtClinicLocation);
        edtFromToDays = view.findViewById(R.id.edtFromToDays);
        edtOpenTime = view.findViewById(R.id.edtOpenTime);
        edtCloseTime = view.findViewById(R.id.edtCloseTime);
        edtConsultancyFees = view.findViewById(R.id.edtConsultancyFees);
        edtAvailabilityStatus = view.findViewById(R.id.edtAvailabilityStatus);
        btnSubmit = view.findViewById(R.id.btnSubmit);
        btn_upload_image = view.findViewById(R.id.btn_upload_image);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDoctorClinic(Token, Glob.user_id, edtClinicName.getText().toString(),
                        edtClinicLocation.getText().toString(),
                        edtOpenTime.getText().toString(),
                        edtCloseTime.getText().toString(),
                        edtConsultancyFees.getText().toString(),
                        edtFromToDays.getText().toString(),
                        edtAvailabilityStatus.getText().toString());
            }
        });

        btn_upload_image.setOnClickListener(new View.OnClickListener() {
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


    public void doctorClinic(String token, String user_id) {


        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);


        call.doctorClinic(token, user_id).enqueue(new Callback<ClinicalSettingModel>() {
            @Override
            public void onResponse(Call<ClinicalSettingModel> call, Response<ClinicalSettingModel> response) {

                ClinicalSettingModel model = response.body();

                Log.e("TAG", "onResponse: " + model.getData().getDoctor_id());

                Log.e("TAG", "onResponse: " + model.getData().getClinic_name());

                edtClinicName.setText(model.getData().getClinic_name());
                edtClinicLocation.setText(model.getData().getClinic_location());
                edtFromToDays.setText(model.getData().getFrom_to_days());

                edtOpenTime.setText(model.getData().getOpen_time());
                edtCloseTime.setText(model.getData().getClose_time());
                edtConsultancyFees.setText(model.getData().getOfline_consultancy_fees());
                edtAvailabilityStatus.setText(model.getData().getDoctor_availability_status());

            }

            @Override
            public void onFailure(Call<ClinicalSettingModel> call, Throwable t) {

            }
        });
    }

    public void updateDoctorClinic(String token, String doctor_id,
                                   String clinic_name, String clinic_location,
                                   String open_time, String close_time, String ofline_consultancy_fees,
                                   String from_to_days, String doctor_availability_status) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        call.updateDoctorClinic(token, doctor_id, clinic_name, clinic_location, open_time, close_time,
                ofline_consultancy_fees, from_to_days, doctor_availability_status).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel model = response.body();
                Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

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
//                            Uri temporary_Image = Uri.fromFile(new File(String.valueOf(img_file)));
//                            profileImage.setImageURI(temporary_Image);
                            uploadClinicImage(Token, Glob.user_id, img_file);
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

                                uploadClinicImage(Token, Glob.user_id, img_file);
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

    public void uploadClinicImage(String token, String doctor_id, File Clinic_image) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);



        RequestBody requestBody_token = RequestBody.create(MediaType.parse("multipart/form-data"), token);
        RequestBody requestBody_doctor_id = RequestBody.create(MediaType.parse("multipart/form-data"), doctor_id);

        MultipartBody.Part requestBody_clinic_image = null;
        RequestBody requestBody_req_img = RequestBody.create(MediaType.parse("multipart/form-data"), Clinic_image);
        requestBody_clinic_image = MultipartBody.Part.createFormData("image", img_file.getName(), requestBody_req_img);


        call.uploadClinicImage(requestBody_token, requestBody_doctor_id, requestBody_clinic_image).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel model = response.body();

                Toast.makeText(getContext(), "" + model.getMessage(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}