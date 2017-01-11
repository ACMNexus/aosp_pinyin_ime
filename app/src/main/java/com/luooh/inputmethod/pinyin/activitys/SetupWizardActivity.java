package com.luooh.inputmethod.pinyin.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.android.inputmethod.pinyin.R;
import com.luooh.inputmethod.pinyin.utils.InputMethodUtils;
import com.luooh.inputmethod.pinyin.utils.ToastUtils;

public class SetupWizardActivity extends BaseActivity implements Handler.Callback {

    private Handler mHandler;
    private TextView mActive;
    private TextView mSwitch;
    private Message mMessage;
    private InputMethodManager mInputManager;
    private static final int MSG_POLLING_IME_SETTINGS = 0;
    private static final long IME_SETTINGS_POLLING_INTERVAL = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_wizard);

        init();
        initView();
        setListener();
    }

    private void init() {
        mHandler = new Handler(this);
        mInputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        mMessage = mHandler.obtainMessage(MSG_POLLING_IME_SETTINGS);
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
                startPollingImeSettings();
                break;
            case R.id.switch_input:
                if(!InputMethodUtils.isThisImeEnabled(this, mInputManager)) {
                    ToastUtils.show(R.string.active_inputmethod_tips);
                    return;
                }
                mInputManager.showInputMethodPicker();
                break;
        }
    }

    private void jumpLanguageSetting() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch(msg.what) {
            case MSG_POLLING_IME_SETTINGS:
                if (InputMethodUtils.isThisImeEnabled(mActivity, mInputManager)) {
                    mSwitch.setClickable(true);
                    return true;
                }
//                startPollingImeSettings();
                break;
        }
        return false;
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

    public void startPollingImeSettings() {
        mHandler.sendMessageDelayed(mMessage, IME_SETTINGS_POLLING_INTERVAL);
    }
}
