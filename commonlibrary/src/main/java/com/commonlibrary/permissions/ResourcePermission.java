package com.commonlibrary.permissions;

import android.app.Activity;
import android.support.annotation.NonNull;



/**
 * Created by    on 4/20/2017.
 */

public abstract class ResourcePermission
{
    public static final int PERMISSION_REQUEST_CODE_STORAGE = 240;
    public static final int REQUEST_PERMISSION_SETTING = 241;

    private final Activity context;

    public Activity getContext() {
        return context;
    }


    private onPermissionListener listener;

    public ResourcePermission(Activity context)
    {
        this.context = context;
    }

    public abstract void Request();

    public abstract void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

    public onPermissionListener getListener() {
        return listener;
    }

    public void setListener(onPermissionListener listener) {
        this.listener = listener;
    }

    public void grantPermission(boolean value) {
        listener.onPermissionGranted(value);
    }
}
