package com.cx.yzparent.activity.login;

import android.Manifest;
import android.content.Intent;

import com.cx.yzparent.R;
import com.cx.yzparent.activity.BaseActivity;
import com.cx.yzparent.activity.MainActivity;
import com.cx.yzparent.view.CommonTipDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initLayout() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean isAccept) throws Exception {
                        if (isAccept) {
                            goActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        } else {
                            CommonTipDialog tipDialog = new CommonTipDialog(SplashActivity.this, "需要权限");
                            tipDialog.setConfigListener(new CommonTipDialog.OnConfigClickListener() {
                                @Override
                                public void onConfigClick() {
                                    showToast("进入引用详情页面");
                                }
                            });
                            tipDialog.show();
                        }
                    }
                });
    }
}
