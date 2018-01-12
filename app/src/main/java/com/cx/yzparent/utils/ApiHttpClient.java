package com.cx.yzparent.utils;

import okhttp3.OkHttpClient;

/**
 * Created by cj on 2016/7/27.
 *
 */
public class ApiHttpClient {
    public static OkHttpClient okhttpClient = null;

    public static OkHttpClient getInstance() {
        if (okhttpClient == null) {
            okhttpClient = new OkHttpClient();
        }
        return okhttpClient;
    }
}
