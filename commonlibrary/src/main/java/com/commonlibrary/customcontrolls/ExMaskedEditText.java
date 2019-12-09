package com.commonlibrary.customcontrolls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import com.commonlibrary.R;
import com.commonlibrary.logger.Log;


public class ExMaskedEditText extends AppCompatEditText {

	private static final int PHONE_NUMBER_TOTAL_SYMBOLS = 13; // size of pattern 321-841-1417
	private static final int PHONE_NUMBER_TOTAL_DIGITS = 10; // max numbers of digits in pattern: 000 x 3
	private static final int PHONE_NUMBER_DIVIDER_MODULO = 4; // means divider position is every 4th symbol beginning with 1
	private static final int PHONE_NUMBER_DIVIDER_POSITION = PHONE_NUMBER_DIVIDER_MODULO - 1; // means divider position is every 3th symbol beginning with 0
	private static final char PHONE_NUMBER_DIVIDER = '-';


	private static final int CARD_NUMBER_TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
	private static final int CARD_NUMBER_TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
	private static final int CARD_NUMBER_DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
	private static final int CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
	private static final char CARD_NUMBER_DIVIDER = '-';

	private static final int CARD_DATE_TOTAL_SYMBOLS = 5; // size of pattern MM/YY
	private static final int CARD_DATE_TOTAL_DIGITS = 4; // max numbers of digits in pattern: MM + YY
	private static final int CARD_DATE_DIVIDER_MODULO = 3; // means divider position is every 3rd symbol beginning with 1
	private static final int CARD_DATE_DIVIDER_POSITION = CARD_DATE_DIVIDER_MODULO - 1; // means divider position is every 2nd symbol beginning with 0
	private static final char CARD_DATE_DIVIDER = '/';

	private static final int CARD_CVC_TOTAL_SYMBOLS = 3;
	private String fontName;

	private String mode;

	public ExMaskedEditText(Context context) {
		this(context, "");
	}

	public ExMaskedEditText(Context context, String mode) {
		this(context, mode, ' ');
	}

	public ExMaskedEditText(Context context, String mode, char placeholder) {
		this(context, null, mode, placeholder);
	}

	public ExMaskedEditText(Context context, AttributeSet attr) {
		this(context, attr, "");
	}

	public ExMaskedEditText(Context context, AttributeSet attr, String mode) {
		this(context, attr, "", ' ');
	}

	public ExMaskedEditText(Context context, AttributeSet attr, String mode, char placeholder) {
		super(context, attr);
		
		TypedArray a = context.obtainStyledAttributes(attr, R.styleable.ExMaskedEditText);
		final int N = a.getIndexCount();
		
		for (int i = 0; i < N; ++i)
		{
		    int at = a.getIndex(i);
			if(at == R.styleable.ExMaskedEditText_mode)
			{
				mode = (mode.length() > 0 ? mode : a.getString(at));
			}
		}

		if(!isInEditMode())
		{
			TypedArray a1 = context.obtainStyledAttributes(attr, R.styleable.TextView);
			boolean isBold = a.getBoolean(R.styleable.TextView_isBold, false);
			boolean isLight = a.getBoolean(R.styleable.TextView_isLight, false);
			boolean isMedium = a.getBoolean(R.styleable.TextView_isMedium,false);
			setFont(context, isBold, isLight,isMedium);
			a1.recycle();
		}

		this.mode = mode;
		addTextChangedListener(new MaskTextWatcher());

		if (mode.length() > 0)
			setText(getText()); // sets the text to create the mask
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

	public Editable getText(boolean removeMask) {
		if (!removeMask) {
			return getText();
		} else {
			SpannableStringBuilder value = new SpannableStringBuilder(getText());
			stripMaskChars(value);

			return value;
		}
	}


	private void stripMaskChars(Editable value) {
		PlaceholderSpan[] pspans = value.getSpans(0, value.length(), PlaceholderSpan.class);
		LiteralSpan[] lspans = value.getSpans(0, value.length(), LiteralSpan.class);

		for (int k = 0; k < pspans.length; k++) {
			value.delete(value.getSpanStart(pspans[k]), value.getSpanEnd(pspans[k]));
		}

		for (int k = 0; k < lspans.length; k++) {
			value.delete(value.getSpanStart(lspans[k]), value.getSpanEnd(lspans[k]));
		}
	}



	private class MaskTextWatcher implements TextWatcher {
		private boolean updating = false;

		@Override
		public void afterTextChanged(Editable s) {

			if (!TextUtils.isEmpty(mode)) {
				switch (mode)
                {
                    case "phone":
                        if (!isInputCorrect(s, PHONE_NUMBER_TOTAL_SYMBOLS, PHONE_NUMBER_DIVIDER_MODULO, PHONE_NUMBER_DIVIDER, "phone")) {
                            s.replace(0,
									s.length(),
									concatString(
											getDigitArray(s,
													PHONE_NUMBER_TOTAL_DIGITS),
											PHONE_NUMBER_DIVIDER_POSITION,
											PHONE_NUMBER_DIVIDER,s)
							);
                        }
                        break;
                    case "card":
                        if (!isInputCorrect(s, CARD_NUMBER_TOTAL_SYMBOLS, CARD_NUMBER_DIVIDER_MODULO, CARD_NUMBER_DIVIDER, STRING.EMPTY)) {
                            s.replace(0, s.length(), concatString(getDigitArray(s, CARD_NUMBER_TOTAL_DIGITS), CARD_NUMBER_DIVIDER_POSITION, CARD_NUMBER_DIVIDER, null));
                        }
                        break;
                    case "cvv":
                        if (s.length() > CARD_CVC_TOTAL_SYMBOLS) {
                            s.delete(CARD_CVC_TOTAL_SYMBOLS, s.length());
                        }
                        break;
                    case "exp_date":
                        if (!isInputCorrect(s, CARD_DATE_TOTAL_SYMBOLS, CARD_DATE_DIVIDER_MODULO, CARD_DATE_DIVIDER, STRING.EMPTY)) {
                            s.replace(0, s.length(), concatString(getDigitArray(s, CARD_DATE_TOTAL_DIGITS), CARD_DATE_DIVIDER_POSITION, CARD_DATE_DIVIDER, null));
                        }
                        break;
                }
			}
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
	}

	private boolean isInputCorrect(Editable s, int size, int dividerPosition, char divider, String phone) {
		boolean isCorrect = s.length() <= size;
		for (int i = 0; i < s.length(); i++) {
			if (i > 0 && (i + 1) % dividerPosition == 0) {
				isCorrect &= divider == s.charAt(i);
			} else {
				isCorrect &= Character.isDigit(s.charAt(i));
			}
		}
		/*if(!TextUtils.isEmpty(phone))
		{
			if(s.toString().indexOf('-') == 7 || s.length() == 9)
			{
				return false;
			}
		}*/
		return isCorrect;
	}

	private String concatString(char[] digits, int dividerPosition, char divider, Editable s) {
		final StringBuilder formatted = new StringBuilder();



		switch (mode)
		{
			case "phone":
				int count = 0;
				for (int i = 0; i < digits.length; i++) {
					if (digits[i] != 0) {
						formatted.append(digits[i]);
						if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {

							/*if(!TextUtils.isEmpty(s))
							{
								if(i == 9)
								{
									formatted.append(' ');
								}
								else
								{
									formatted.append(divider);
								}

							}
							else
							{
								formatted.append(divider);
							}*/

                            formatted.append(divider);

							count++;
							Log.e("phone",String.valueOf(count));
						}
					}
				}
				break;
			default:
				for (int i = 0; i < digits.length; i++) {
					if (digits[i] != 0) {
						formatted.append(digits[i]);
						if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
							formatted.append(divider);
						}
					}
				}
				break;
		}


		return formatted.toString();
	}

	private char[] getDigitArray(final Editable s, final int size) {
		char[] digits = new char[size];
		int index = 0;
		for (int i = 0; i < s.length() && index < size; i++) {
			char current = s.charAt(i);
			if (Character.isDigit(current)) {
				digits[index] = current;
				index++;
			}
		}
		return digits;
	}

	private class PlaceholderSpan {
		// this class is used just to keep track of placeholders in the text 
	}

	private class LiteralSpan {
		// this class is used just to keep track of literal chars in the text
	}
}
