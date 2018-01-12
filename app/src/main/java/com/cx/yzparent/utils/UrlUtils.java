package com.cx.yzparent.utils;

import com.cx.yzparent.beans.InformationListBeans;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cj on 2018/1/11
 */

public interface UrlUtils {
    @GET("ar/queryAllInformations")
    Observable<InformationListBeans> getInformationData(@Query("page") String page, @Query("rows") String rows);

    @GET("user/newQueryParentHomeInfo")
    Observable<InformationListBeans> getBannerData(@Query("token") String token, @Query("site") String site);
}
