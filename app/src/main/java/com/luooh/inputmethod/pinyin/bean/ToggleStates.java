package com.luooh.inputmethod.pinyin.bean;

/**
 * Created by Luooh on 2017/1/11.
 */
public class ToggleStates {

    /**
     * Maximum toggle states for a soft keyboard.
     */
    public static final int MAX_TOGGLE_STATES = 4;

    /**
     * If it is true, this soft keyboard is a QWERTY one.
     */
    public boolean mQwerty;

    /**
     * If {@link #mQwerty} is true, this variable is used to decide the
     * letter case of the QWERTY keyboard.
     */
    public boolean mQwertyUpperCase;

    /**
     * The id of enabled row in the soft keyboard. Refer to
     * {@link SoftKeyboard.KeyRow} for
     * details.
     */
    public int mRowIdToEnable;

    /**
     * Used to store all other toggle states for the current input mode.
     */
    public int mKeyStates[] = new int[MAX_TOGGLE_STATES];

    /**
     * Number of states to toggle.
     */
    public int mKeyStatesNum;
}
