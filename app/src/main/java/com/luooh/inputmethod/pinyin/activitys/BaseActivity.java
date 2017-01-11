package com.luooh.inputmethod.pinyin.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;

/**
 * Created by Luooh on 2017/1/11.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener, AbsListView.OnItemClickListener {

    protected Activity mActivity;
    protected InputMethodManager mIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = this;
        mIm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
