package mars.core.utils;

import android.util.Log;

/**
 * Created by mars on 2017/8/24.
 */

public class Logger {
    private static final String HEAD = "Logger";

    public static void logi(String tag, String msg) {
        Log.i(HEAD + tag, msg);
    }

    public static void logv(String tag, String msg) {
        Log.v(HEAD + tag, msg);
    }

    public static void logd(String tag, String msg) {
        Log.d(HEAD + tag, msg);
    }

    public static void logw(String tag, String msg) {
        Log.w(HEAD + tag, msg);
    }

    public static void loge(String tag, String detail, Exception e) {
        loge(HEAD + tag, detail + getErrorDetail(e));
    }

    public static void loge(String tag, String msg) {
        Log.e(HEAD + tag, msg);
    }


    private static String getErrorDetail(Throwable e) {
        if (e == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stackTraceElements = e.getStackTrace();
        if (stackTraceElements == null || stackTraceElements.length == 0) {
            return "";
        }
        for (StackTraceElement element : stackTraceElements) {
            if (element != null) {
                sb.append(element.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
