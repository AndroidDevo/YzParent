package com.cx.yzparent.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cj on 2017/12/21
 */

public class ToastUtils {
    private static Toast toast;

    public static void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(str);
        toast.show();
    }
}
