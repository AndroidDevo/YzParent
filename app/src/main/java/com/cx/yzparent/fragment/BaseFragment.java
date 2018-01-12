package com.cx.yzparent.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cx.yzparent.utils.ToastUtils;

/**
 * Created by cj on 2017/12/21
 */

abstract class BaseFragment extends Fragment {
    public View mRootView;
    private boolean isLoaded;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = initView(inflater);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (!isLoaded) {
            isLoaded = true;
            initData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isLoaded) {
            isLoaded = true;
            initData();
        }
    }

    protected abstract View initView(LayoutInflater inflater);

    public abstract void initData();

    protected void showToast(int resId) {
        if (isAdded() && getActivity() != null) {
            ToastUtils.showToast(getActivity(), getString(resId));
        }
    }
}
