package com.commonlibrary.common;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by    on 12/8/2016.
 */

@StringDef({STRING.EMPTY})
@Retention(RetentionPolicy.SOURCE)
public @interface STRING {
    String EMPTY = "";
}
