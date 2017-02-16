package com.bgautam.mallow.app;

import android.content.Context;

/**
 * Helper class for getting the context
 */
public class Application extends android.app.Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }
}
