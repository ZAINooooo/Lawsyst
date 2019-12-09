package com.commonlibrary.common;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Asif on 5/21/2016.
 */


@IntDef(
        {
                ANIMATION_TYPE.FADE,
                ANIMATION_TYPE.SLIDE,
                ANIMATION_TYPE.POPUP_UP
        })
@Retention(RetentionPolicy.SOURCE)
public @interface ANIMATION_TYPE {
    int FADE = 0;
    int SLIDE = 1;
    int POPUP_UP = 2;
}
