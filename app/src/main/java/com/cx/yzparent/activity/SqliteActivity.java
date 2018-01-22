package com.cx.yzparent.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cx.yzparent.R;
import com.cx.yzparent.adapter.RecycleAdapter;
import com.cx.yzparent.beans.InformationListBeans;
import com.cx.yzparent.utils.LogUtils;
import com.cx.yzparent.utils.UrlUtils;
import com.cx.yzparent.utils.response.ResponseData;
import com.cx.yzparent.utils.retrofit.RetrofitHelper;
import com.cx.yzparent.utils.rx.RxJavaHelper;
import com.cx.yzparent.utils.rx.RxObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SqliteActivity extends BaseActivity {

    @BindView(R.id.list_news)
    RecyclerView mListNews;
    private RecycleAdapter mRecycleAdapter;
    private List<InformationListBeans.DataBean.ListBean> mList = new ArrayList<>();
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
        api.getInformationData("1", "15")
                .compose(RxJavaHelper.<InformationListBeans>observeOnMainThread())
                .subscribe(new RxObserver<InformationListBeans>() {
                    @Override
                    public void onSuccess(InformationListBeans beans) {
                        InformationListBeans.DataBean data = beans.data;
                        mList.addAll(data.rows);
                        if (mRecycleAdapter == null) {
                            mListNews.setLayoutManager(new LinearLayoutManager(SqliteActivity.this, LinearLayoutManager.VERTICAL, false));
                            mRecycleAdapter = new RecycleAdapter(SqliteActivity.this, mList);
                            mListNews.setAdapter(mRecycleAdapter);
                            mListNews.addItemDecoration(new DividerItemDecoration(SqliteActivity.this,DividerItemDecoration.VERTICAL));
                        } else {
                            mRecycleAdapter.update(mList);
                        }
                        LogUtils.e(mList.size() + "日志打印");
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

}
