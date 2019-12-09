package com.commonlibrary.common;

/**
 * Created by    on 11/18/2016.
 */
public class AppCommon {
    public static String CURRENT_SESSION = "";
    private static AppCommon ourInstance = new AppCommon();
    public static long TOTAL_TIME = 0;
    private String previousRequestTime;

    public static AppCommon getInstance() {
        return ourInstance;
    }

    private AppCommon() {
    }

    public void setPreviousRequestTime(String previousRequestTime) {
        this.previousRequestTime = previousRequestTime;
    }

    public String getPreviousRequestTime() {
        return previousRequestTime;
    }
}
