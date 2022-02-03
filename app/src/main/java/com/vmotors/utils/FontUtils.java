package com.vmotors.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by ashutosh.kumar on 16/12/16.
 */

public class FontUtils {


    private FontUtils() {
        throw new Error("U will not able to instantiate it");
    }

    public static Typeface setFontRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    public static Typeface setFontBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Bold.ttf");

    }

    public static Typeface setFontLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");
    }


}
