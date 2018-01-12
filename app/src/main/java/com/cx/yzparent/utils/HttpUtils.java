package com.cx.yzparent.utils;


import android.text.TextUtils;
import android.util.Patterns;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by cj on 2016/7/27.
 * 基于okHttp再封装的网络访问
 */
public class HttpUtils {
    private Class mClz;
    private String mUrl;
    private static OkHttpClient mOkHttpClient;

    private HttpUtils(String url) {
        mUrl = url;
        mOkHttpClient = ApiHttpClient.getInstance();
    }

    public static HttpUtils url(String url) {
        return new HttpUtils(url);
    }

    public HttpUtils toBean(Class clz) {
        this.mClz = clz;
        return this;
    }

    public void get(Callback callback) {
        final Callback cb = callback;
        if (!(TextUtils.isEmpty(mUrl))) {
            if (!(Patterns.WEB_URL.matcher(mUrl).matches())) {
                doFail(cb);
                return;
            }
        } else {
            doFail(cb);
            return;
        }
        Request request = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").url(mUrl).build();
        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                doFail(cb);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.body() != null && 200 == response.code()) {
                    String str = response.body().string();
                    Object obj = JsonUtils.toBean(mClz, str);
                    if (obj != null) {
                        try {
                            cb.onSuccess(obj);
                        } catch (Exception e) {
                            doFail(cb);
                        }
                    } else {
                        doFail(cb);
                    }
                } else {
                    doFail(cb);
                }
            }
        });
    }


    public void get(boolean canCache, Callback callback) {
        final Callback cb = callback;
        if (!(TextUtils.isEmpty(mUrl))) {
            if (!(Patterns.WEB_URL.matcher(mUrl).matches())) {
                doFail(cb);
                return;
            }
        } else {
            doFail(cb);
            return;
        }
        Request request = new Request.Builder().addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8").url(mUrl).build();
        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                doFail(cb);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.body() != null && 200 == response.code()) {
                    String str = response.body().string();
                    Object obj = JsonUtils.toBean(mClz, str);
                    if (obj != null) {
                        try {
                            cb.onSuccess(obj);
                        } catch (Exception e) {
                            doFail(cb);
                        }
                    } else {
                        doFail(cb);
                    }
                } else {
                    doFail(cb);
                }
            }
        });
    }

    public void post(Callback callback, Map<String, String> params) {
        final Callback cb = callback;
        if (!(TextUtils.isEmpty(mUrl))) {
            if (!(Patterns.WEB_URL.matcher(mUrl).matches())) {
                doFail(cb);
                return;
            }
        } else {
            doFail(cb);
            return;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (key == null || value == null) {
                doFail(cb);
                return;
            }
            builder.add(key, value);
        }
        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(mUrl).post(requestBody).build();
        mOkHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                doFail(cb);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.body() != null && 200 == response.code()) {
                    String str = response.body().string();
                    if (mClz == null) {
                        doFail(cb);
                        return;
                    }
                    Object obj = JsonUtils.toBean(mClz, str);
                    if (obj != null) {
                        try {
                            cb.onSuccess(obj);
                        } catch (Exception e) {
                            doFail(cb);
                        }
                    } else {
                        doFail(cb);
                    }
                } else {
                    doFail(cb);
                }
            }
        });
    }

    //网络访问出现错误
    private void doFail(Callback cb) {
        if (cb == null) {
            return;
        }
        try {
            cb.onFailure(new IOException());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface Callback {
        void onFailure(Exception e);

        void onSuccess(Object object);
    }
}
