package com.cx.yzparent.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.cx.yzparent.R;

/**
 * Created by cj on 2017/12/21
 */

public class CommonTipDialog extends Dialog implements View.OnClickListener {
    private String mTipContent;

    public CommonTipDialog(@NonNull Context context, String tipContent) {
        super(context);
        this.mTipContent = tipContent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_common_tip);
        TextView content = findViewById(R.id.dialog_content);
        TextView cancel = findViewById(R.id.dialog_cancel);
        TextView config = findViewById(R.id.dialog_config);
        cancel.setOnClickListener(this);
        config.setOnClickListener(this);
        content.setText(mTipContent);
    }

    /**
     * 确认按钮
     */
    public void setConfigListener(OnConfigClickListener listener) {
        this.mConfigListener = listener;
    }

    /**
     * 取消按钮
     */
    private void setCancelListener(OnCancelClickLister listener) {
        this.mCancelListener = listener;
    }

    /*
     * 4. 定义一个成员变量，用于保存用户设置的接听器
	 */
    private OnCancelClickLister mCancelListener;
    private OnConfigClickListener mConfigListener;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_cancel:
                setCancelListener(mCancelListener);
                dismiss();
                break;
            case R.id.dialog_config:
                if (mConfigListener != null) {
                    mConfigListener.onConfigClick();
                }
                dismiss();
                break;
        }
    }

    /*
     * 1. 定义一个确认按钮接口
	 */
    public interface OnConfigClickListener {
        /*
         * 2. 定义回调方法
         */
        void onConfigClick();
    }

    /*
     *  1. 定义一个取消按钮接口
     */
    private interface OnCancelClickLister {
        void onCancelClick();
    }
}
