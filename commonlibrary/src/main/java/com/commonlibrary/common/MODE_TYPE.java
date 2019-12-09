package com.commonlibrary.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Asif on 5/21/2016.
 */


@IntDef(
        {
                MODE_TYPE.SERVICE,
                MODE_TYPE.ADDON
        })
@Retention(RetentionPolicy.SOURCE)
public @interface MODE_TYPE {
    int SERVICE = 0;
    int ADDON = 1;
}
