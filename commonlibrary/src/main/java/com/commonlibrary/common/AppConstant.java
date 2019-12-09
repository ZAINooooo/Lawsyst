package com.commonlibrary.common;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import java.io.File;

/**
 * Created by Asif on 5/21/2016.
 */
public final class AppConstant {
    public static String BASE_URL2 = STRING.EMPTY;
    public static String API_FOLDER = STRING.EMPTY;
    public static String BASE_URL = STRING.EMPTY;
    public static String BASE_URL_IMAGES = STRING.EMPTY;
    public static Boolean updateTag = false;
    public static int correct_question = 0;
    public static File mImagePath;
    public static final String FOLDER_NAME = "EQPRO";
    public static final String FOLDER_PATH = Environment.getExternalStorageDirectory().getPath() + "/" + FOLDER_NAME;
    public static Boolean tag_pic = false;
    public static Uri uri;

    public static class BUNDLE {

        public static final String DEVICE_ID = "DEVICE_ID";

        public static final String CATEGORY = "CATEGORY";
        public static final String TOTAL_SCORE = "TOTAL_SCORE";
        public static final String TEST_NAME = "TEST_NAME";
        public static final String QUIZ_ID = "QUIZ_ID";
        public static final String TOTAL_TIME = "TOTAL_TIME";
        public static final String LONGEST_STREAK = "LONGEST_STREAK";
        public static final String CODE_EXPIRED = "CODE_EXPIRED";
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    public static void checkPermission(Activity activity, String permissionString, int permissionCode) {
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M)) return;
        int existingPermissionStatus = ContextCompat.checkSelfPermission(activity,
                permissionString);
        if (existingPermissionStatus == PackageManager.PERMISSION_GRANTED) return;
        ActivityCompat.requestPermissions(activity, new String[]{permissionString}, permissionCode);
    }

    public static void checkPermission(Fragment fragment, String permissionString, int permissionCode) {
        if ((Build.VERSION.SDK_INT < Build.VERSION_CODES.M) || fragment.getContext() == null)
            return;
        int existingPermissionStatus = ContextCompat.checkSelfPermission(fragment.getContext(),
                permissionString);
        if (existingPermissionStatus == PackageManager.PERMISSION_GRANTED) return;
        fragment.requestPermissions(new String[]{permissionString}, permissionCode);
    }

    public static boolean isReadStorageGranted(Context context) {
        int storagePermissionGranted = ContextCompat.checkSelfPermission(context,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        return storagePermissionGranted == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isWriteStorageGranted(Context context) {
        int storagePermissionGranted = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return storagePermissionGranted == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isCameraGranted(Context context) {
        int cameraPermissionGranted = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        return cameraPermissionGranted == PackageManager.PERMISSION_GRANTED;
    }

}
