package com.luooh.inputmethod.pinyin.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by Luooh on 2017/1/11.
 */
public class SoftKeyType {

    public static final int KEYTYPE_ID_NORMAL_KEY = 0;

    public int mKeyTypeId;
    public Drawable mKeyBg;
    public Drawable mKeyHlBg;
    public int mColor;
    public int mColorHl;
    public int mColorBalloon;

    public SoftKeyType(int id, Drawable bg, Drawable hlBg) {
        mKeyTypeId = id;
        mKeyBg = bg;
        mKeyHlBg = hlBg;
    }

    public void setColors(int color, int colorHl, int colorBalloon) {
        mColor = color;
        mColorHl = colorHl;
        mColorBalloon = colorBalloon;
    }
}
