package com.vmotors.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.vmotors.R;
import com.vmotors.databinding.LedittextViewBinding;

public class LEditText extends LinearLayout {

    private LedittextViewBinding binding;
    private Context context;
    private int maxLength;
    private Drawable drawableLeft;
    private Drawable drawableRight;
    private int inputType;
    private boolean focusable;
    private String hint;
    private String text;

    public LEditText(Context context) {
        super(context);
        initView(context);
    }

    public LEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        parseAttributes(context, attrs, -1);
    }

    public LEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        initView(context);
        parseAttributes(context, attrs, defStyleAttr);
    }

    public void setError(String errorMessage) {
        if(errorMessage == null){
            binding.tvError.setText("");
            binding.card.setBackgroundResource(R.drawable.edditext_white_bg_rectangle);
            binding.tvError.setVisibility(View.INVISIBLE);

        }else{
            binding.tvError.setText(errorMessage);
            binding.card.setBackgroundResource(R.drawable.edditext_white_bg_rectangle_error);
            binding.tvError.setVisibility(View.VISIBLE);
        }
    }

    private void initView(final Context context) {
        this.context = context;
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.ledittext_view, this,true);
    }

    protected void parseAttributes(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LEditTextView, defStyleAttr, 0);
        try {
            hint = typedArray.getString(R.styleable.LEditTextView_android_hint);
            inputType = typedArray.getInt(R.styleable.LEditTextView_android_inputType, InputType.TYPE_CLASS_TEXT);
            maxLength = typedArray.getInt(R.styleable.LEditTextView_android_maxLength, 32);
            drawableLeft = typedArray.getDrawable(R.styleable.LEditTextView_drawableLeft);
            drawableRight = typedArray.getDrawable(R.styleable.LEditTextView_drawableRight);
            text = typedArray.getString(R.styleable.LEditTextView_android_text);
            focusable = typedArray.getBoolean(R.styleable.LEditTextView_focusable, true);
            styleInputField();
        } finally {
            typedArray.recycle();
        }
    }

    private void styleInputField() {
        binding.card.setBackgroundResource(R.drawable.edditext_white_bg_rectangle);
        binding.tvValue.setHint(hint);
        setEditTextMaxLength(maxLength);
        binding.tvValue.setFocusable(focusable);
        binding.tvValue.setInputType(inputType);
        if(drawableLeft != null)
            binding.tvValue.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null,null,null);
        if(drawableRight != null)
            binding.tvValue.setCompoundDrawablesWithIntrinsicBounds(null, null,drawableRight,null);
    }

    public void setDrawable(){
//        binding.tvValue.setCompoundDrawables(R.drawable.com_facebook_auth_dialog_background,null, null, null);
    }

    public void setEditTextMaxLength(int length) {
        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(length);
        binding.tvValue.setFilters(filterArray);
    }

    public EditText getEditText(){
        return binding.tvValue;
    }

    public String getText(){
        return binding.tvValue.getText().toString();
    }

    public void setFocusable(boolean focusable) {
        this.focusable = focusable;
        binding.tvValue.setFocusable(focusable);
    }
}
