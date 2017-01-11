package com.luooh.inputmethod.pinyin.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.android.inputmethod.pinyin.R;
import com.luooh.inputmethod.pinyin.utils.InputMethodUtils;

/**
 * Created by Luooh on 2017/1/11.
 */
public class SplashActivity extends BaseActivity {

    private View mContentView;
    private int mDurationTime = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = LayoutInflater.from(this).inflate(R.layout.activity_splash, null);
        setContentView(mContentView);
        startAnimation();
    }

    private void startAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1.0f);
        alphaAnimation.setDuration(mDurationTime);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(mActivity, SetupWizardActivity.class);
                if (InputMethodUtils.isThisImeCurrent(mActivity, mIm)) {
                    intent.setClass(mActivity, MainActivity.class);
                }
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.in_right_left, R.anim.out_right_left);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mContentView.startAnimation(alphaAnimation);
    }
}
