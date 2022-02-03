/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.vmotors.ui.components;


import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vmotors.R;

import java.util.ArrayList;
import java.util.List;


public class PinEditText extends LinearLayout implements ValidatableInput<String>{

    private float fontSize = 0.0f;

    @ColorInt
    private int digitColor;

    private Drawable defaultPinBackgroundColor;

    int spacing = 0;

    public static final int DIGITS = 6;

    private final List<EditText> editTexts = new ArrayList<>();

    private final EditText hiddenEditText;
    TextView textView;

    private final List<TextWatcher> textWatchers = new ArrayList<>();
    private OnFocusChangeListener mSecFocusChangeListener = null;

    public PinEditText(final Context context) {
        this(context, null);
    }

    public PinEditText(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        parseAttributes(context, attrs, defStyleAttr);

        fontSize = context.getResources().getDimension(R.dimen.dimen_20dp);
        spacing = context.getResources().getDimensionPixelSize(R.dimen.dimen_5dp);
        hiddenEditText = new EditText(context);
        hiddenEditText.setInputType(InputType.TYPE_CLASS_NUMBER );
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(DIGITS);
        hiddenEditText.setFilters(filterArray);

        hiddenEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                for (TextWatcher watcher : textWatchers) {
                    watcher.beforeTextChanged(charSequence, start, before, count);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                updateEditTextsState(charSequence);
                for (TextWatcher watcher : textWatchers) {
                    watcher.onTextChanged(charSequence, start, before, count);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                for (TextWatcher watcher : textWatchers) {
                    watcher.afterTextChanged(editable);
                }
            }
        });


        hiddenEditText.setOnFocusChangeListener((view, hasFocus) -> updateEditTextsState(hiddenEditText.getText()));

        hiddenEditText.setLayoutParams(new ViewGroup.LayoutParams(10, LayoutParams.WRAP_CONTENT));

        for (int i = 0; i < DIGITS; i++) {

            EditText editText = new EditText(context);
            editText.setGravity(Gravity.CENTER_HORIZONTAL);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSize);
            editText.setCursorVisible(false);
            editText.setTextColor(digitColor);
            editText.setBackgroundResource(R.drawable.grey_bg_rectangle);
            editText.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT));
            addView(editText);
            editText.setOnFocusChangeListener(new FocusListener(editText));

            setDefaultPinBackground(editText);

            editTexts.add(editText);
        }

        addView(hiddenEditText);

        textView = new TextView(context);
        textView.setTextColor(getResources().getColor(R.color.red));
        textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        textView.setPadding(20,0,0,0);
        addView(textView);


        setFocusable(true);
        setOnFocusChangeListener((view, hasFocus) -> {
            if (hasFocus) {
                setFocus(hiddenEditText);
                showSoftKeyboard(hiddenEditText);
                updateEditTextsState(hiddenEditText.getText());
            }
        });
    }

    public void setFocusChangeListener(OnFocusChangeListener focusChangeListener) {
        mSecFocusChangeListener = focusChangeListener;
    }

    public List<EditText> getEditTexts() {
        return editTexts;
    }

    private void parseAttributes(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PinEditText, defStyleAttr, 0);
        try {
            digitColor = typedArray.getColor(R.styleable.PinEditText_digitColor, ContextCompat.getColor(context, R.color.black));
            final int drawableResIdDefault = typedArray.getResourceId(R.styleable.PinEditText_defaultPinBackgroundColor, -1);
            if (drawableResIdDefault != -1)
                defaultPinBackgroundColor = ContextCompat.getDrawable(context, drawableResIdDefault);
        } finally {
            typedArray.recycle();
        }
    }

    public void addTextChangedListener(final TextWatcher watcher) {
        textWatchers.add(watcher);
    }

    public void setOnEditorActionListener(final TextView.OnEditorActionListener listener) {
        hiddenEditText.setOnEditorActionListener(listener);
    }

    public String getText() {
        return hiddenEditText.getText().toString();
    }

    public void clear() {
        hiddenEditText.setText("");
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();

        int digitWidth = (width - (DIGITS - 2) * spacing) / DIGITS;

        for (int i = 0; i < DIGITS; i++) {
            EditText editText = editTexts.get(i);
            LayoutParams lp = (LayoutParams) editText.getLayoutParams();
            lp.width = digitWidth;
            lp.rightMargin = i < DIGITS - 1 ? spacing : 0;
        }
    }

    private static void setFocus(final EditText editText) {
        if (editText == null) {
            return;
        }

        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    private void showSoftKeyboard(final EditText editText) {
        if (editText == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Service.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }

    private boolean isActive() {
        return hiddenEditText.hasFocus();
    }

    private void updateEditTextsState(final CharSequence text) {

        boolean active = isActive();

        int focusesInputIndex = text.length();
        if (focusesInputIndex >= DIGITS) {
            focusesInputIndex = DIGITS - 1;
        }

        for (int i = 0; i < DIGITS; i++) {
            EditText edit = editTexts.get(i);
            if (i < text.length()) {
                edit.setText(String.valueOf(text.charAt(i)));
            } else {
                edit.setText("");
            }
        }
    }

    private void setDefaultPinBackground(final EditText editText) {
        setViewBackground(editText, defaultPinBackgroundColor == null ? getContext().getResources().getDrawable(R.drawable.edditext_white_bg_rectangle_error) : defaultPinBackgroundColor);
    }

    public void setError(String error) {
        if(error != null && textView != null && error.length() > 0){
            textView.setText(error);
            for (EditText editText : editTexts) {
                editText.setBackgroundResource(R.drawable.edditext_white_bg_rectangle_error);
            }
        }else{
            textView.setText("");
        }
    }

    @SuppressWarnings("deprecation")
    private void setViewBackground(final View view, final Drawable background) {
        if (view == null || background == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(background);
        } else {
            view.setBackgroundDrawable(background);
        }
    }

    @Override
    public void clearErrors() {

    }

    @Override
    public void showError(final String message) {

    }

    @Override
    public String getValue() {
        return getText();
    }

    private class FocusListener implements OnFocusChangeListener {

        private final EditText editText;

        FocusListener(EditText editText) {
            this.editText = editText;
        }

        @Override
        public void onFocusChange(View view, boolean hasFocus) {
            if (mSecFocusChangeListener != null) {
                mSecFocusChangeListener.onFocusChange(view, hasFocus);
            }
            if (hasFocus) {
                setFocus(hiddenEditText);
                showSoftKeyboard(hiddenEditText);
                updateEditTextsState(hiddenEditText.getText());
            }
        }
    }

}
