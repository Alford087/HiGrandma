package com.mars.higrandma.core.utils;

import android.util.Log;

/**
 * isDebug=true情况下，日志输出与工厂无关，切任何等级日志都将输出，若为false，则按照工程的日志输出
 * Created by TC on 2015/12/25.
 */
public class SmartDebugUtils {

    final private static String TAG = "NDLiveComponent";

    private static String MODULE_NAME = "APP";

    private static boolean isDebug = false;//开发模式

    public static void logw(String className, String info) {
        Log.w(getTag(className), info != null ? info : "info is null");
    }

    public static void logd(String className, String info) {
        if (isDebug) {
            Log.d(getTag(className), info != null ? info : "info is null");
        }
    }

    public static void loges(String className, Exception e) {
        loges(className, "", e);
    }

    public static void loges(String className, String method, Exception e) {
        if (e == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        if (stackTraceElements == null || stackTraceElements.length == 0) {
            return;
        }
        for (StackTraceElement element : stackTraceElements) {
            if (element != null) {
                sb.append(element.toString()).append("\n");
            }
        }
        loges(className, method + " : " + sb.toString());
    }

    public static void loges(String className, String info) {
        loges(className, info, true);
    }

    private static void loges(String className, String info, boolean save) {
        if (save) {
        }
        if (isDebug) {
            Log.e(getTag(className), info != null ? info : "info is null");
        }
    }

    public static String getTag(String className) {
        return className != null ? (TAG + " | " + MODULE_NAME + " | " + className) : TAG;
    }

    public static boolean isSmartLiveDebug() {
        return isDebug;
    }

    public static void setDebug(boolean debug) {
        isDebug = debug;
    }
}
