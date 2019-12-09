package com.commonlibrary.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Asif on 5/21/2016.
 */


@IntDef({NETWORK_TYPE.WiFi, NETWORK_TYPE.DATA, NETWORK_TYPE.NO_CONNECTION})
@Retention(RetentionPolicy.SOURCE)
public @interface NETWORK_TYPE {
    int WiFi = 0;
    int DATA = 1;
    int NO_CONNECTION = 2;
}
