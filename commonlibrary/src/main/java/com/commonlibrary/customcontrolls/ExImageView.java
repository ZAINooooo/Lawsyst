package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Asif on 7/8/2016.
 */
public class ExImageView extends android.support.v7.widget.AppCompatImageView {
    public ExImageView(Context context) {
        super(context);
    }

    public ExImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
