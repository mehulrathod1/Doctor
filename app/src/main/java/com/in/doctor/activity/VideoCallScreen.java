package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.FrameLayout;

import com.in.doctor.R;

public class VideoCallScreen extends AppCompatActivity {



    public String token = "006e62c5cf058664ddfb48a36ec3d51e6d8IAAujV4xC21M4NsWgScxLVIpDFMWFsPIL6WptjdXZowFU6DfQtYAAAAAEAB/8F67xTzYYQEAAQDDPNhh";
    public String appId = "e62c5cf058664ddfb48a36ec3d51e6d8";
    public String channelName = "demo";
    private RtcEngine mRtcEngine;
    private static final int PERMISSION_REQ_ID = 22;

    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
        @Override
        // Listen for the remote user joining the channel to get the uid of the user.
        public void onUserJoined(int uid, int elapsed) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Call setupRemoteVideo to set the remote video view after getting uid from the onUserJoined callback.
                    setupRemoteVideo(uid);
                }
            });
        }
    };

    private static final String[] REQUESTED_PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };

    private boolean checkSelfPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, requestCode);
            return false;
        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_call_screen);


        if (checkSelfPermission(REQUESTED_PERMISSIONS[0], PERMISSION_REQ_ID) &&
                checkSelfPermission(REQUESTED_PERMISSIONS[1], PERMISSION_REQ_ID)) {
            initializeAndJoinChannel();
        }

    }
    // Java
    private void setupRemoteVideo(int uid) {

        FrameLayout container = findViewById(R.id.remote_video_view_container);
        SurfaceView surfaceView = RtcEngine.CreateRendererView(getBaseContext());
        surfaceView.setZOrderMediaOverlay(true);
        container.addView(surfaceView);
        mRtcEngine.setupRemoteVideo(new VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_FIT, uid));

    }

    protected void onDestroy() {
        super.onDestroy();
        mRtcEngine.leaveChannel();
        mRtcEngine.destroy();

    }
}
}