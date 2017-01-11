package com.luooh.inputmethod.pinyin.listener;

import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import com.luooh.inputmethod.pinyin.view.CandidatesContainer;

/**
 * Created by Luooh on 2017/1/11.
 */
public class OnGestureListener extends GestureDetector.SimpleOnGestureListener {

    /**
     * View to show candidates list.
     */
    private CandidatesContainer mCandidatesContainer;

    /**
     * When user presses and drags, the minimum x-distance to make a
     * response to the drag event.
     */
    private static final int MIN_X_FOR_DRAG = 60;

    /**
     * When user presses and drags, the minimum y-distance to make a
     * response to the drag event.
     */
    private static final int MIN_Y_FOR_DRAG = 40;

    /**
     * Velocity threshold for a screen-move gesture. If the minimum
     * x-velocity is less than it, no gesture.
     */
    static private final float VELOCITY_THRESHOLD_X1 = 0.3f;

    /**
     * Velocity threshold for a screen-move gesture. If the maximum
     * x-velocity is less than it, no gesture.
     */
    static private final float VELOCITY_THRESHOLD_X2 = 0.7f;

    /**
     * Velocity threshold for a screen-move gesture. If the minimum
     * y-velocity is less than it, no gesture.
     */
    static private final float VELOCITY_THRESHOLD_Y1 = 0.2f;

    /**
     * Velocity threshold for a screen-move gesture. If the maximum
     * y-velocity is less than it, no gesture.
     */
    static private final float VELOCITY_THRESHOLD_Y2 = 0.45f;

    /** If it false, we will not response detected gestures. */
    private boolean mReponseGestures;

    /** The minimum X velocity observed in the gesture. */
    private float mMinVelocityX = Float.MAX_VALUE;

    /** The minimum Y velocity observed in the gesture. */
    private float mMinVelocityY = Float.MAX_VALUE;

    /** The first down time for the series of touch events for an action. */
    private long mTimeDown;

    /** The last time when onScroll() is called. */
    private long mTimeLastOnScroll;

    /** This flag used to indicate that this gesture is not a gesture. */
    private boolean mNotGesture;

    /** This flag used to indicate that this gesture has been recognized. */
    private boolean mGestureRecognized;

    public OnGestureListener(boolean reponseGestures) {
        mReponseGestures = reponseGestures;
    }

    /**
     * Warning:if mReponseGestures is true, the mCandidatesContainer must not be null
     * @param mCandidatesContainer
     */
    public void setCandidatesContainer(CandidatesContainer mCandidatesContainer) {
        this.mCandidatesContainer = mCandidatesContainer;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        mMinVelocityX = Integer.MAX_VALUE;
        mMinVelocityY = Integer.MAX_VALUE;
        mTimeDown = e.getEventTime();
        mTimeLastOnScroll = mTimeDown;
        mNotGesture = false;
        mGestureRecognized = false;
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {
        if (mNotGesture) return false;
        if (mGestureRecognized) return true;

        if (Math.abs(e1.getX() - e2.getX()) < MIN_X_FOR_DRAG
                && Math.abs(e1.getY() - e2.getY()) < MIN_Y_FOR_DRAG)
            return false;

        long timeNow = e2.getEventTime();
        long spanTotal = timeNow - mTimeDown;
        long spanThis = timeNow - mTimeLastOnScroll;
        if (0 == spanTotal) spanTotal = 1;
        if (0 == spanThis) spanThis = 1;

        float vXTotal = (e2.getX() - e1.getX()) / spanTotal;
        float vYTotal = (e2.getY() - e1.getY()) / spanTotal;

        // The distances are from the current point to the previous one.
        float vXThis = -distanceX / spanThis;
        float vYThis = -distanceY / spanThis;

        float kX = vXTotal * vXThis;
        float kY = vYTotal * vYThis;
        float k1 = kX + kY;
        float k2 = Math.abs(kX) + Math.abs(kY);

        if (k1 / k2 < 0.8) {
            mNotGesture = true;
            return false;
        }
        float absVXTotal = Math.abs(vXTotal);
        float absVYTotal = Math.abs(vYTotal);
        if (absVXTotal < mMinVelocityX) {
            mMinVelocityX = absVXTotal;
        }
        if (absVYTotal < mMinVelocityY) {
            mMinVelocityY = absVYTotal;
        }

        if (mMinVelocityX < VELOCITY_THRESHOLD_X1
                && mMinVelocityY < VELOCITY_THRESHOLD_Y1) {
            mNotGesture = true;
            return false;
        }

        if (vXTotal > VELOCITY_THRESHOLD_X2
                && absVYTotal < VELOCITY_THRESHOLD_Y2) {
            if (mReponseGestures) onDirectionGesture(Gravity.RIGHT);
            mGestureRecognized = true;
        } else if (vXTotal < -VELOCITY_THRESHOLD_X2
                && absVYTotal < VELOCITY_THRESHOLD_Y2) {
            if (mReponseGestures) onDirectionGesture(Gravity.LEFT);
            mGestureRecognized = true;
        } else if (vYTotal > VELOCITY_THRESHOLD_Y2
                && absVXTotal < VELOCITY_THRESHOLD_X2) {
            if (mReponseGestures) onDirectionGesture(Gravity.BOTTOM);
            mGestureRecognized = true;
        } else if (vYTotal < -VELOCITY_THRESHOLD_Y2
                && absVXTotal < VELOCITY_THRESHOLD_X2) {
            if (mReponseGestures) onDirectionGesture(Gravity.TOP);
            mGestureRecognized = true;
        }

        mTimeLastOnScroll = timeNow;
        return mGestureRecognized;
    }

    @Override
    public boolean onFling(MotionEvent me1, MotionEvent me2,
                           float velocityX, float velocityY) {
        return mGestureRecognized;
    }

    public void onDirectionGesture(int gravity) {
        if (Gravity.NO_GRAVITY == gravity) {
            return;
        }

        if (Gravity.LEFT == gravity || Gravity.RIGHT == gravity) {
            if (mCandidatesContainer.isShown()) {
                if (Gravity.LEFT == gravity) {
                    mCandidatesContainer.pageForward(true, true);
                } else {
                    mCandidatesContainer.pageBackward(true, true);
                }
                return;
            }
        }
    }
}
