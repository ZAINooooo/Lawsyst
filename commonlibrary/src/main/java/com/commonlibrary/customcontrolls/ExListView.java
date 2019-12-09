package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by    on 8/31/2016.
 */

public class ExListView extends ListView
{
    public ExListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExListView(Context context) {
        super(context);
    }

    public ExListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    private int old_count = 0;
    private android.view.ViewGroup.LayoutParams params;
    public void scrollToLastElement()
    {
        final ListAdapter adapter = getAdapter();
        if(adapter != null)
        {
            if(adapter.getCount() > 0)
            {
                post(new Runnable() {
                    @Override
                    public void run() {
                        //smoothScrollToPosition(adapter.getCount() - 1);
                        smoothScrollToPositionFromTop(adapter.getCount() - 1,0);
                    }
                });
            }
            else
            {
                Log.e("ExListView","Adapter is empty");
            }
        }
        else
        {
            Log.e("ExListView","Adapter is null");
        }
    }

    /*@Override
    protected void onDraw(Canvas canvas) {
        if (getCount() != old_count) {
            old_count = getCount();
            params = getLayoutParams();
            int height = getChildAt(0).getHeight();
            //height = height - 100;

            params.height = getCount() * (old_count > 0 ? height : 0);
            setLayoutParams(params);
        }

        super.onDraw(canvas);
    }*/

}
