package com.commonlibrary.common;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by    on 12/8/2016.
 */

@StringDef({DEVICE_TYPE.ANDRIOD})
@Retention(RetentionPolicy.SOURCE)
public @interface DEVICE_TYPE {
    String ANDRIOD = "android";
}
