package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.commonlibrary.R;


/**
 * Custom TextView with user fonts.
 * extends {@link android.widget.TextView}
 */
public class ExButton extends android.support.v7.widget.AppCompatButton{

    private String fontName;


    public ExButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAllCaps(false);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextView);
        boolean isBold = a.getBoolean(R.styleable.TextView_isBold, false);
        boolean isLight = a.getBoolean(R.styleable.TextView_isLight, false);
        boolean isMedium = a.getBoolean(R.styleable.TextView_isMedium,false);
        setFont(context, isBold, isLight,isMedium);
        a.recycle();
    }

    private void setFont(Context context, boolean isBold, boolean isLight, boolean isMedium)
    {
        if (fontName == null || fontName.length() == 0)
        {
            fontName = context.getString(R.string.fontRegular);
        }

        if (isBold)
        {
            fontName = context.getString(R.string.font_bold);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
        else if (isLight)
        {
            fontName = context.getString(R.string.font_light);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
        else if (isMedium)
        {
            fontName = context.getString(R.string.font_medium);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
        else
        {
            fontName = context.getString(R.string.fontRegular);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
