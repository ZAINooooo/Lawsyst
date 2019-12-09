package com.commonlibrary.collections;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by    on 12/8/2016.
 */

@StringDef({SEARCH_OPERATOR.NOT,SEARCH_OPERATOR.EQUALS})
@Retention(RetentionPolicy.SOURCE)
public @interface SEARCH_OPERATOR {
    String NOT = "not";
    String EQUALS = "equals";
}
