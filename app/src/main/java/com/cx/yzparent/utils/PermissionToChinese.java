package com.cx.yzparent.utils;

/**
 * Created by CJ on 2017/12/21
 */

public class PermissionToChinese {
    public static String change(String permission) {
        switch (permission) {
            case "android.permission.CAMERA":
                permission = "相机权限";
                break;
            case "android.permission.READ_EXTERNAL_STORAGE":
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                permission = "读取手机存储";
                break;
        }
        return permission;
    }
}
