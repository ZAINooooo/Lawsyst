package com.commonlibrary.logger;

/**
 * Wrapper on native Log class to enable/disable logs.
 */
public class Log {

    private static boolean isDebug = true;
    public static void e(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        android.util.Log.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        android.util.Log.w(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        android.util.Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        android.util.Log.d(tag, msg);
    }
    public static void v(String tag, String msg) {
        if (!isDebug) {
            return;
        }
        android.util.Log.v(tag, msg);
    }
}
