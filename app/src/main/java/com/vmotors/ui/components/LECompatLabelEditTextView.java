package com.vmotors.ui.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.vmotors.R;

public class LECompatLabelEditTextView extends LinearLayout {

    private Context context;

    private int maxLength;
    private Drawable drawable;
    private int inputType;
    private String hint;
    private String text;

    public LECompatLabelEditTextView(Context context) {
        super(context);
        initView(context);
    }

    public LECompatLabelEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        parseAttributes(context, attrs , -1);
    }

    public LECompatLabelEditTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        parseAttributes(context, attrs, defStyleAttr);
    }

    private void initView(final Context context) {
        this.context = context;
//        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.le_conpat_label_edittext, this,true);

    }
    protected void parseAttributes(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LECompatLabelEditText, defStyleAttr, 0);
        try {
            hint = typedArray.getString(R.styleable.LECompatLabelEditText_android_hint);
            inputType = typedArray.getInt(R.styleable.LECompatLabelEditText_android_inputType, InputType.TYPE_CLASS_TEXT);
            maxLength = typedArray.getInt(R.styleable.LECompatLabelEditText_android_maxLength, 32);
//            drawable = typedArray.getDrawable(R.styleable.LECompatLabelEditText_android_drawable);
            text = typedArray.getString(R.styleable.LECompatLabelEditText_android_text);
            styleInputField();
        } finally {
            typedArray.recycle();
        }
    }

    private void styleInputField() {
//        binding.card.setBackgroundResource(R.drawable.edditext_white_bg_rectangle);
//        binding.tvValue.setHint(hint);
//        setEditTextMaxLength(maxLength);
//        binding.tvValue.setInputType(inputType);
//        if(drawableLeft != null)
//            binding.tvValue.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null,null,null);
    }

}
