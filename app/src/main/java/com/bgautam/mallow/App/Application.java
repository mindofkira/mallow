package com.bgautam.mallow.App;

import android.content.Context;

/**
 * Helper class for getting the context
 */
public class Application extends android.app.Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
