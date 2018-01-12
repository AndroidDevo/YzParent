package com.cx.yzparent.utils.rx;


import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * RxJava封装辅助类
 */
public class RxJavaHelper {

    /**
     * 切换线程操作
     *
     * @return Observable转换器
     */
    public static <T> ObservableTransformer<T, T> observeOnMainThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(io.reactivex.Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
