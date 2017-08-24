package com.mars.higrandma.core.utils;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by Mars on 2017/2/23.
 */

public class KeyboardUtils {
    private KeyboardUtils() {

    }

    /**
     * 获取屏幕原始尺寸高度，包括虚拟功能键高度
     *
     * @param context 上下文
     * @return 屏幕高度
     */
    public static int getWindowDpi(Context context) {
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi = displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     *
     * @param context 上下文
     * @return 虚拟按键的高度
     */
    public static int getNavigationBarHeight(Context context) {
        int totalHeight = getWindowDpi(context);
        int contentHeight = ScreenUtils.getScreenHeight(context);
        return totalHeight - contentHeight;
    }

    /**
     * 获取软键盘的高度
     *
     * @param context 上下文
     * @param view    view 视图
     * @return 软键盘的高度
     */
    public static int getKeyboardHeight(Context context, View view) {
        if (context == null || view == null) {
            return 0;
        }
        Rect r = new Rect();
        // 获取控件显示的矩形区域
        view.getWindowVisibleDisplayFrame(r);

        // 控件的实际高度
        int screenHeight = view.getRootView().getHeight();

        // 实际高度-（展示区域底部和展示区域的顶部差值），得出的就是当前屏幕高度的变化
        int heightDifference = screenHeight - (r.bottom - r.top);

        // 由于还要考虑状态栏和底部虚拟菜单的高度，所以计算两者的大小
        int navigationBarHeight = KeyboardUtils.getNavigationBarHeight(context);
        int statusBarHeight = ScreenUtils.getStatusHeight(context);

        // 真实改变的距离还要再减去状态栏和底部虚拟菜单的高度
        return heightDifference - navigationBarHeight - statusBarHeight;
    }

    public static boolean isKeyboardShown(View rootView) {
        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

}
