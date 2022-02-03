//package com.vmotors.utils;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.content.ContextCompat;
//import android.text.SpannableString;
//import android.text.Spanned;
//import android.text.TextPaint;
//import android.text.TextUtils;
//import android.text.method.LinkMovementMethod;
//import android.text.style.ClickableSpan;
//import android.view.View;
//import android.widget.TextView;
//
//import com.vmotors.R;
//import com.vmotors.ui.enroll.login.LoginActivity;
//
//public class TextViewBuilder {
//
//
//    public static void prepareTncText(Context context, TextView textView) {
//        String tncBaseText = context.getString(R.string.terms_and_condition_text);
//        String tncText = context.getString(R.string.terms_and_condition_link);
//        String and = context.getString(R.string.and);
//        String privacytext = context.getString(R.string.privacy_policy_link);
//
//        SpannableString spannableBaseText = new SpannableString(tncBaseText);
//        SpannableString tncLink = new SpannableString(tncText);
//        SpannableString andText = new SpannableString(and);
//        SpannableString privacyLink = new SpannableString(tncLink);
//        final int linkColor = ContextCompat.getColor(context, R.color.terms_and_condition_text);
//
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(final View view) {
////                mEnrollBasePresenter.onTnCClick();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                ds.setColor(linkColor);
//            }
//        };
//
//        ClickableSpan privacyClickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(final View view) {
////                mEnrollBasePresenter.onTnCClick();
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                ds.setColor(linkColor);
//            }
//        };
//
//        tncLink.setSpan(clickableSpan, 0, tncLink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        privacyLink.setSpan(privacyClickableSpan, 0, privacyLink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView.setText(TextUtils.concat(spannableBaseText, tncLink, andText, privacytext));
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//    }
//
//    public static void prepareAlreadyMember(Context context, TextView textView, String text) {
//        String tncBaseText = context.getString(R.string.already_a_member);
//        String tncText = text;
//
//        SpannableString spannableBaseText = new SpannableString(tncBaseText);
//        SpannableString tncLink = new SpannableString(tncText);
//        final int linkColor = ContextCompat.getColor(context, R.color.terms_and_condition_text);
//
//        ClickableSpan clickableSpan = new ClickableSpan() {
//            @Override
//            public void onClick(final View view) {
//                if (text.equals(context.getString(R.string.login))){
//                    context.startActivity(new Intent(context, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }else{
//                    context.startActivity(new Intent(context, RegisterActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                }
//            }
//
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                ds.setColor(linkColor);
//            }
//        };
//
//
//        tncLink.setSpan(clickableSpan, 0, tncLink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        textView.setText(TextUtils.concat(spannableBaseText, tncLink));
//        textView.setMovementMethod(LinkMovementMethod.getInstance());
//    }
//}
