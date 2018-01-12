package com.cx.yzparent.activity;

import android.widget.ListView;

import com.cx.yzparent.R;
import com.cx.yzparent.beans.InformationListBeans;
import com.cx.yzparent.utils.LogUtils;
import com.cx.yzparent.utils.UrlUtils;
import com.cx.yzparent.utils.response.ResponseData;
import com.cx.yzparent.utils.retrofit.RetrofitHelper;
import com.cx.yzparent.utils.rx.RxJavaHelper;
import com.cx.yzparent.utils.rx.RxObserver;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SqliteActivity extends BaseActivity {

    @BindView(R.id.list_news)
    ListView mListNews;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sqlite;
    }

    @Override
    protected void initLayout() {
        doInternet();
    }

    private void doInternet() {
        UrlUtils api = RetrofitHelper.createApi(UrlUtils.class);
        api.getBannerData("1", "appIndexBanner")
                .compose(RxJavaHelper.<InformationListBeans>observeOnMainThread())
                .subscribe(new RxObserver<InformationListBeans>() {
                    @Override
                    public void onSuccess(InformationListBeans beans) {
                        InformationListBeans.DataBean data = beans.data;
                        List<InformationListBeans.DataBean.ListBean> rows = data.rows;
                        LogUtils.e(rows.size() + "日志打印");
                    }

                    @Override
                    public void onFail(ResponseData data) {
                        if (data != null) {
                            if (-1 == data.status) {
                                showOutNotice();
                            } else {
                                showToast(data.msg);
                            }
                        }
                    }
                });
    }
    @OnClick(R.id.reload)
    public void onClick() {
        doInternet();
    }
}
