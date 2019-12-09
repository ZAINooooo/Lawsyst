package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.commonlibrary.R;
import com.commonlibrary.listeners.OnEnableListener;

import static android.graphics.Typeface.BOLD;


/**
 * Created by asif.javaid on 2/17/14.
 */
public class ExCheckBox extends android.support.v7.widget.AppCompatCheckBox {

    private OnEnableListener _enableListner;

    public ExCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        initialize(context, attrs);
    }

    //private boolean isEnable;
    //private OnEnableListener _enableListner;
    private String fontName;

    private void initialize(Context context,AttributeSet attrs) {

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
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if(_enableListner != null)
        {
            _enableListner.onEnable(enabled);
        }
    }

    public void setOnEnableListner(OnEnableListener enableListner)
    {
        _enableListner = enableListner;
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

}
