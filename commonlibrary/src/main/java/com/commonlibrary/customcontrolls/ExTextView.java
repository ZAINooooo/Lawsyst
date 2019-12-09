package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.commonlibrary.R;

import static android.graphics.Typeface.BOLD;


/**
 * Custom TextView with user fonts.
 * extends {@link android.widget.TextView}
 */
public class ExTextView extends android.support.v7.widget.AppCompatTextView {

    private String fontName;

    public ExTextView(Context context, String fontName) {
        super(context);
        this.fontName = fontName;
    }

    public ExTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.TextView);

        if (!isInEditMode()) {
            boolean isBold = a.getBoolean(R.styleable.TextView_isBold, false);
            boolean isLight = a.getBoolean(R.styleable.TextView_isLight, false);
            boolean isMedium = a.getBoolean(R.styleable.TextView_isMedium, false);
            boolean isGothic = a.getBoolean(R.styleable.TextView_isGothic, false);
            boolean isItalic = a.getBoolean(R.styleable.TextView_isItalic, false);
            boolean isItalicOblique = a.getBoolean(R.styleable.TextView_isItalicOblique, false);
            setFont(context, isBold, isLight, isMedium,isGothic,isItalic,isItalicOblique);
            a.recycle();
        }
    }

    public void setFont(Context context, boolean isBold, boolean isLight, boolean isMedium, boolean isGothic, boolean isItalic, boolean isItalicOblique)
    {
        if (fontName == null || fontName.length() == 0)
        {
            fontName = context.getString(R.string.fontRegular);
        }

        if (isBold)
        {
            fontName = context.getString(R.string.font_bold);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf, BOLD);
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
        else if (isGothic)
        {
            fontName = context.getString(R.string.font_gothic);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
        else if (isItalic)
        {
            fontName = context.getString(R.string.font_itatlic);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/" + fontName);
            setTypeface(tf);
        }
        else if (isItalicOblique)
        {
            fontName = context.getString(R.string.font_light_Oblique);
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
