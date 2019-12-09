package com.commonlibrary.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import com.commonlibrary.R;
import com.commonlibrary.listeners.IDialogListener;
import com.commonlibrary.util.Utils;


/**
 * Created by    on 4/20/2017.
 */

public class LocationPermission extends ResourcePermission {

    public LocationPermission(Activity context) {
        super(context);
    }

    @Override
    public void Request() {
        if(Utils.getInstance().checkPlayServices(getContext()))
        {
            if (Utils.getInstance().isLocationEnabled(getContext())) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if ((ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                            (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                            (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {

                        String[] permissions = new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_FINE_LOCATION};

                        ActivityCompat.requestPermissions(getContext(), permissions, PERMISSION_REQUEST_CODE_STORAGE);

                        return;
                    }
                    grantPermission(true);
                }
                else
                {
                    grantPermission(true);
                }
            }
            else
            {
                Utils.getInstance().showDialog(getContext(), getContext().getString(R.string.app_name), getContext().getString(R.string.no_gps), new IDialogListener() {
                    @Override
                    public void onClickOk(boolean isok) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        getContext().startActivity(intent);
                    }
                });

                return;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case PERMISSION_REQUEST_CODE_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        (grantResults[0] == PackageManager.PERMISSION_GRANTED) &&
                        (grantResults[1] == PackageManager.PERMISSION_GRANTED) &&
                        (grantResults[2] == PackageManager.PERMISSION_GRANTED)) {

                    grantPermission(true);

                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (permissions != null && permissions.length == 3) {
                            boolean showRationale = getContext().shouldShowRequestPermissionRationale(permissions[2]);
                            if (! showRationale) {
                                // user also CHECKED "never ask again"
                                // you can either enable some fall back,
                                // disable features of your app
                                // or open another dialog explaining
                                // again the permission and directing to
                                // the app setting

                                Utils.getInstance().showDialog(getContext(), getContext().getString(R.string.locationpermission), getContext().getString(R.string.permission_needed_location), getContext().getString(R.string.yes), getContext().getString(R.string.cancel1), new IDialogListener() {
                                    @Override
                                    public void onClickOk(boolean isok) {

                                        if(isok)
                                        {
                                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                            Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
                                            intent.setData(uri);
                                            getContext().startActivityForResult(intent, REQUEST_PERMISSION_SETTING);
                                        }
                                    }
                                });

                            } else if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[2])) {

                                //showRationale(permissions[0], R.string.permission_denied_writestorage);
                                // user did NOT check "never ask again"
                                // this is a good place to explain the user
                                // why you need the permission and ask if he wants
                                // to accept it (the rationale)

                                Utils.getInstance().showDialog(getContext(), getContext().getString(R.string.locationpermission), getContext().getString(R.string.permission_denied_location), getContext().getString(R.string.retry), getContext().getString(R.string.cancel1), new IDialogListener() {
                                    @Override
                                    public void onClickOk(boolean isok) {
                                        if(isok)
                                        {
                                            String[] permissions = new String[]{
                                                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                                    Manifest.permission.ACCESS_FINE_LOCATION};

                                            ActivityCompat.requestPermissions(getContext(), permissions, PERMISSION_REQUEST_CODE_STORAGE);
                                        }
                                    }
                                });

                            }
                        }
                    }
                }
            }
        }
    }
}
