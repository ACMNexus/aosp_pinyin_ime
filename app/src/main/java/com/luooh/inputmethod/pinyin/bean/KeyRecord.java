package com.luooh.inputmethod.pinyin.bean;

/**
 * Default definition for a certain key. It is defined in soft keyboard
 * template. A soft keyboard can refer to a default key in its xml file. Nothing
 * of the key can be overwritten, including the size.
 */
public class KeyRecord {

    public int keyId;
    public SoftKey softKey;
}
