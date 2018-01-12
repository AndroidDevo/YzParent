package com.cx.yzparent.utils.rx;

import com.cx.yzparent.R;
import com.cx.yzparent.utils.AppUtil;
import com.cx.yzparent.utils.LogUtils;
import com.cx.yzparent.utils.ResourceUtil;
import com.cx.yzparent.utils.ToastUtils;
import com.cx.yzparent.utils.response.ResponseData;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * RxJava订阅者Observer封装扩展
 */
public abstract class RxObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        // 忽略操作，需要可覆写该方法
    }

    @Override
    public void onError(Throwable e) {
        String errNetwork = ResourceUtil.getString(R.string.err_network);
        LogUtils.e("onError: " + errNetwork);
        ToastUtils.showToast(AppUtil.getContext(), errNetwork);
    }

    @Override
    public void onNext(T t) {
        if (t instanceof ResponseData) {
            ResponseData response = (ResponseData) t;
            // 判断是否请求错误，出错直接转到onError()
            if (0 != response.status) {
                onFail(response);
                return;
            }
        }
        onSuccess(t);
    }

    /**
     * 请求成功回调
     *
     * @param t 最终响应结果
     */
    public abstract void onSuccess(T t);

    public abstract void onFail(ResponseData data);
}
