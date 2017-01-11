package com.luooh.inputmethod.pinyin.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.android.inputmethod.pinyin.R;
import com.luooh.inputmethod.pinyin.utils.InputMethodUtils;
import com.luooh.inputmethod.pinyin.utils.ToastUtils;

public class SetupWizardActivity extends BaseActivity {

    private TextView mActive;
    private TextView mSwitch;
    private InputMethodManager mInputManager;
    public boolean mNeedsToAdjustStepNumberToSystemState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_wizard);
        init();
        initView();
        setListener();
    }

    private void init() {
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private void initView() {
        mActive = (TextView) findViewById(R.id.active);
        mSwitch = (TextView) findViewById(R.id.switch_input);
    }

    private void setListener() {
        mActive.setOnClickListener(this);
        mSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.active:
                jumpLanguageSetting();
                break;
            case R.id.switch_input:
                if(!InputMethodUtils.isThisImeEnabled(this, mInputManager)) {
                    ToastUtils.show(mActivity, getString(R.string.active_inputmethod_tips));
                    return;
                }
                mNeedsToAdjustStepNumberToSystemState = true;
                mInputManager.showInputMethodPicker();
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && mNeedsToAdjustStepNumberToSystemState && InputMethodUtils.isThisImeCurrent(this, mInputManager)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void jumpLanguageSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
