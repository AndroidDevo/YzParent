package com.cx.yzparent.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.cx.yzparent.activity.login.SplashActivity;
import com.cx.yzparent.utils.ToastUtils;
import com.cx.yzparent.view.CommonTipDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cj on 2017/12/21
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸式
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//竖屏
        mUnBinder = ButterKnife.bind(this);
        initLayout();
    }

    protected abstract int getLayoutId();
    protected abstract void initLayout();

    protected void showToast(String tip) {
        ToastUtils.showToast(this,tip);
    }

    protected void goActivity(Intent intent) {
        startActivity(intent);
    }

    protected void showOutNotice() {
        CommonTipDialog tipDialog = new CommonTipDialog(this, "您尚未登录或登录已过期,请重新登录");
        tipDialog.setConfigListener(new CommonTipDialog.OnConfigClickListener() {
            @Override
            public void onConfigClick() {
                showToast("进入登录页面");
            }
        });
        tipDialog.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
