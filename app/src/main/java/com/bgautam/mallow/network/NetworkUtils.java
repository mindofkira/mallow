package com.bgautam.mallow.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
;import com.bgautam.mallow.App.Application;

/**
 * Created by gautam on 15/02/17.
 */
public class NetworkUtils {


    // Check whether we have an active internet connection
    public static boolean  isConnected() {
        ConnectivityManager cm = (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}
