package com.commonlibrary.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Asif on 5/21/2016.
 */


@IntDef({DEVICE_DENSITY.ldpi, DEVICE_DENSITY.mdpi, DEVICE_DENSITY.hdpi,
        DEVICE_DENSITY.xhdpi, DEVICE_DENSITY.xxhdpi, DEVICE_DENSITY.xxxhdpi,
        DEVICE_DENSITY.UNKNOWN})
@Retention(RetentionPolicy.SOURCE)
public @interface DEVICE_DENSITY {
    int ldpi = 0;
    int mdpi = 1;
    int hdpi = 2;
    int xhdpi = 3;
    int xxhdpi = 4;
    int xxxhdpi = 5;
    int UNKNOWN = -1;
}
