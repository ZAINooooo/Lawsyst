package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.commonlibrary.R;


/**
 * Created by asif.chughtai on 7/3/2015.
 */
public class ErasableAutoCompleteTextView extends android.support.v7.widget.AppCompatAutoCompleteTextView implements View.OnTouchListener, View.OnFocusChangeListener, TextWatcherAdapter.TextWatcherListener {

    private Context context;
    private String fontName;

    public interface Listener {
        void didClearText();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private Drawable xD;
    private Listener listener;

    public ErasableAutoCompleteTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ErasableAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    public ErasableAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init(attrs);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        this.l = l;
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener f) {
        this.f = f;
    }

    private OnTouchListener l;
    private OnFocusChangeListener f;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            boolean tappedX = event.getX() > (getWidth() - getPaddingRight() - xD.getIntrinsicWidth());
            if (tappedX) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    setText("");
                    if (listener != null) {
                        listener.didClearText();
                    }
                }
                return true;
            }
        }
        if (l != null) {
            return l.onTouch(v, event);
        }
        return false;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(isNotEmpty(getText().toString()));
        } else {
            setClearIconVisible(false);
        }
        if (f != null) {
            f.onFocusChange(v, hasFocus);
        }
    }

    private boolean isNotEmpty(String text) {
        if(text.isEmpty())
            return false;
        else
            return true;
    }

    @Override
    public void onTextChanged(EditText view, String text) {
        if (isFocused()) {
            setClearIconVisible(isNotEmpty(text));
        }
    }

    private void init(AttributeSet attrs) {


        if(!isInEditMode())
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TextView);
            boolean isBold = a.getBoolean(R.styleable.TextView_isBold, false);
            boolean isLight = a.getBoolean(R.styleable.TextView_isLight, false);
            boolean isMedium = a.getBoolean(R.styleable.TextView_isMedium,false);
            setFont(context, isBold, isLight,isMedium);
            a.recycle();
        }

        xD = getCompoundDrawables()[2];
        if (xD == null) {
            xD = getResources().getDrawable(android.R.drawable.presence_offline);
            //xD = getResources().getDrawable(R.drawable.cancle_cross_dark_grey);
        }
        xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
    }

    private void init() {

        xD = getCompoundDrawables()[2];
        if (xD == null) {
            xD = getResources().getDrawable(android.R.drawable.presence_offline);
            //xD = getResources().getDrawable(R.drawable.cancle_cross_dark_grey);
        }
        xD.setBounds(0, 0, xD.getIntrinsicWidth(), xD.getIntrinsicHeight());
        setClearIconVisible(false);
        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
        addTextChangedListener(new TextWatcherAdapter(this, this));
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

    protected void setClearIconVisible(boolean visible) {
        boolean wasVisible = (getCompoundDrawables()[2] != null);
        if (visible != wasVisible) {
            Drawable x = visible ? xD : null;
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], x, getCompoundDrawables()[3]);
        }
    }
}