package com.luooh.inputmethod.pinyin.utils;

import android.widget.Toast;

import com.luooh.inputmethod.pinyin.PinyinIME;

/**
 * Created by lenvo on 2017/1/11.
 */
public class ToastUtils {

    public static void show(String msg) {
        Toast.makeText(PinyinIME.getInstance(), msg, Toast.LENGTH_LONG).show();
    }

    public static void show(int resId) {
        Toast.makeText(PinyinIME.getInstance(), resId, Toast.LENGTH_LONG).show();
    }
}
