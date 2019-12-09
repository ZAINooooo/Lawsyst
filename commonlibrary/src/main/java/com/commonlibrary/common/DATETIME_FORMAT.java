package com.commonlibrary.common;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by    on 12/8/2016.
 */

@StringDef({
        DATETIME_FORMAT.YYYY_MM_DD,
        DATETIME_FORMAT.YYYY_MM_DD_HH_MM,
        DATETIME_FORMAT.DD_MM_YYYY_HH_MM,
        DATETIME_FORMAT.YYYY_MM_DD_HH_MM_SS,
        DATETIME_FORMAT.HH_MM_a,
        DATETIME_FORMAT.HH_a,
        DATETIME_FORMAT.DD_MM_YYYY_HH_MM_A,
        DATETIME_FORMAT.YYYY_MM_DD_HH_MM_SS_A,
        DATETIME_FORMAT.YYYY_MM_DD_HH_MM_A,
        DATETIME_FORMAT.MM_DD_YYYY_HH_MM_A,
        DATETIME_FORMAT.MMM_s_DD_YYYY_HH_MM_A})
@Retention(RetentionPolicy.SOURCE)
public @interface DATETIME_FORMAT {
    String YYYY_MM_DD = "yyyy-MM-dd";
    String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    String HH_MM_a = "hh:mm a";
    String HH_a = "hh a";
    String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    String YYYY_MM_DD_HH_MM_SS_A = "yyyy-MM-dd hh:mm:ss a";
    String DD_MM_YYYY_HH_MM = "dd-MM-yyyy HH:mm";
    String DD_MM_YYYY_HH_MM_A = "dd-MM-yyyy hh:mm a";
    String YYYY_MM_DD_HH_MM_A = "yyyy-MM-dd hh:mm a";
    String MM_DD_YYYY_HH_MM_A = "MM-dd-yyyy hh:mm a";
    String MMM_s_DD_YYYY_HH_MM_A = "MMM dd, yyyy - hh:mm a";
}
