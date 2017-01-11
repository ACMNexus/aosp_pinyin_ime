package com.luooh.inputmethod.pinyin.bean;

import android.graphics.drawable.Drawable;

/**
 * Key icon definition. It is defined in soft keyboard template. A soft keyboard
 * can refer to such an icon in its xml file directly to improve performance.
 */
public class KeyIconRecord {

    public int keyCode;
    public Drawable icon;
    public Drawable iconPopup;
}
