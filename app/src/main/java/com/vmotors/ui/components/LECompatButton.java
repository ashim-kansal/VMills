package com.vmotors.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.vmotors.R;
import com.vmotors.databinding.ViewCompatButtonBinding;

public class LECompatButton extends FrameLayout {

    private ViewCompatButtonBinding binding;


    private CharSequence text;

    private Drawable activeButtonBackground;
    private int progressbarColor;
    private int inactiveButtonTextColor;
    private boolean isEnabled;
    private boolean textAllCaps;

    public LECompatButton(final Context context) {
        this(context, null);
    }

    public LECompatButton(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LECompatButton(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        parseAttributes(context, attrs, defStyleAttr);
        prepareButton();
        setUpButton();
    }

    public Button getButton(){
        return binding.btnAction;
    }

    private void setUpButton() {
        progressbarColor=ContextCompat.getColor(getContext(),R.color.white);
        binding.progressBar.getIndeterminateDrawable().setColorFilter(progressbarColor, PorterDuff.Mode.SRC_IN);
        binding.progressBar.bringToFront();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding.progressBar.setElevation(getContext().getResources().getDimension(R.dimen.dimen_3dp));
        }
        binding.btnAction.setBackground(activeButtonBackground);
        binding.btnAction.setText(text);
        binding.btnAction.setTransformationMethod(null);
        binding.btnAction.setAllCaps(false);
        binding.btnAction.setTextColor(this.getResources().getColor(R.color.white)); //TAKE DEFAULT COLOR
        setRippleOverButton();
        setEnabled(isEnabled);
    }

    private void prepareButton() {
        activeButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.edditext_orange_bg_rectangle);
    }

    private void setRippleOverButton() {
        TypedValue outValue = new TypedValue();
        getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, outValue, true);
        setForeground(ContextCompat.getDrawable(getContext(), outValue.resourceId));
    }

    private void setError(String error){
        if(error != null){
            binding.error.setText(error);
            binding.error.setVisibility(VISIBLE);
        }else{
            binding.error.setText("");
            binding.error.setVisibility(GONE);
        }
    }

    private void initView(final Context context) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.view_compat_button, this,true);

        setClickable(true);
        setFocusable(true);
    }

    private void parseAttributes(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LECompatButton, defStyleAttr, 0);
        try {
            text = typedArray.getString(R.styleable.LECompatButton_android_text);
            isEnabled = typedArray.getBoolean(R.styleable.LECompatButton_android_enabled, Boolean.TRUE);
            textAllCaps = typedArray.getBoolean(R.styleable.LECompatButton_android_textAllCaps, Boolean.FALSE);
            activeButtonBackground = ContextCompat.getDrawable(getContext(), R.drawable.edditext_orange_bg_rectangle);

        } finally {
            typedArray.recycle();
        }
    }

    public void setLoadingVisible(final boolean loading) {
        setClickable(!loading);
        binding.progressBar.setVisibility(loading ? View.VISIBLE : View.GONE);
        binding.btnAction.setText(loading ? "" : text);
    }

    public void setText(final CharSequence text) {
        this.text = text;
        binding.btnAction.setText(text);
    }

    @Override
    public void setEnabled(boolean enabled) {
        setClickable(enabled);
        // remove ripple effect
        if (enabled) {
            setRippleOverButton();
        } else {
            setForeground(null);
        }
    }

    public CharSequence getText() {
        return this.text;
    }


}
