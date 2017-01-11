package com.luooh.inputmethod.pinyin.bean;

import java.util.List;

/**
 * Created by Luooh on 2017/1/11.
 */
public class KeyRow {

    public static final int ALWAYS_SHOW_ROW_ID = -1;
    public static final int DEFAULT_ROW_ID = 0;

    public List<SoftKey> mSoftKeys;
    /**
     * If the row id is {@link #ALWAYS_SHOW_ROW_ID}, this row will always be
     * enabled.
     */
    public int mRowId;
    public float mTopF;
    public float mBottomF;
    public int mTop;
    public int mBottom;
}
