package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by asif.javaid on 5/2/2014.
 */
public class ExScrollView extends ScrollView
{
    private View view;

    public ExScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (view == null)
            return super.onInterceptTouchEvent(ev);

        if (inRegion(ev.getRawX(), ev.getRawY(), view))
            return false;

        return super.onInterceptTouchEvent(ev);
    }

    private boolean inRegion(float x, float y, View v)
    {
        int[] mCoordBuffer = new int[]
                { 0, 0 };

        v.getLocationOnScreen(mCoordBuffer);

        return mCoordBuffer[0] + v.getWidth() > x && // right edge
                mCoordBuffer[1] + v.getHeight() > y && // bottom edge
                mCoordBuffer[0] < x && // left edge
                mCoordBuffer[1] < y; // top edge
    }

    public void setViewToRestrictScroll(View view) {
        this.view = view;
    }
}
