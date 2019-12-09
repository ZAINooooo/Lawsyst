package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by    on 8/31/2016.
 */

public class ExGridView extends GridView
{
    public ExGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExGridView(Context context) {
        super(context);
    }

    public ExGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
