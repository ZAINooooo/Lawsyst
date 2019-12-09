package com.commonlibrary.common;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by    on 12/8/2016.
 */

@StringDef({ACCESS_CODE_TYPE.COMPANY, ACCESS_CODE_TYPE.TOURNAMENT})
@Retention(RetentionPolicy.SOURCE)
public @interface ACCESS_CODE_TYPE {
    String COMPANY = "company";
    String TOURNAMENT = "tournament";
    String QUIZ = "quiz";
}
