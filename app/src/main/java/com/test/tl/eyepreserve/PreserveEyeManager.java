package com.test.tl.eyepreserve;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
/**
 * Created by tl on 2017-11-06.
 */

public class PreserveEyeManager {
    private View mLayout;
    private boolean isAddLayout;
    private WindowManager mWindowManager;
    private static PreserveEyeManager mLockColor;

    private PreserveEyeManager(Context context) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutParams params = new LayoutParams(-1, -1, 2006, 1032, -3);
        params.flags |= 201326592;

        mLayout = new LinearLayout(context);
        mWindowManager.addView(mLayout, params);
        isAddLayout = true;
    }

    public static PreserveEyeManager instance(Context context) {
        if (mLockColor == null) {
            mLockColor = new PreserveEyeManager(context);
        }
        return mLockColor;
    }

    /**
     * 移除悬浮按钮所在的布局
     */
    public void removeLayout() {
        if (isAddLayout) {
            mWindowManager.removeView(mLayout);
            isAddLayout = false;
            mLockColor = null;
        }
    }


    /**
     * 过滤蓝光
     * @param blueFilterPercent 蓝光过滤比例[10-80]
     * */
    public void changeColor(int blueFilterPercent) {
        int realFilter = blueFilterPercent;
        if (realFilter < 10)
        {
            realFilter = 10;
        }
        else if (realFilter > 80)
        {
            realFilter = 80;
        }
        int a = (int) (realFilter / 80f * 180);
        int r = (int) (255 - (realFilter / 80f) * 190);
        int g = (int) (130 - ( realFilter / 80f) * 120);
        int b = (int) (30 - realFilter / 80f * 30);

        mLayout.setBackgroundColor(Color.argb(a, r, g, b));
    }
}
