package com.luooh.inputmethod.pinyin.activitys;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.android.inputmethod.pinyin.R;
import com.luooh.inputmethod.pinyin.utils.InputMethodUtils;

public class MainActivity extends BaseActivity {

    private TextView mInputState;
    private InputMethodManager mInputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputState = (TextView) findViewById(R.id.input_state);

        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(!InputMethodUtils.isThisImeEnabled(this, mInputManager)) {
            mInputState.setText(R.string.inputmethod_not_active);
        }

        if(!InputMethodUtils.isThisImeCurrent(this, mInputManager)) {
            mInputState.setText(R.string.inputmethod_not_switch);
        }

    }
}
