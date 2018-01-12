package com.cx.yzparent.utils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cj on 2016/7/27.
 * json 工具类
 */
public class JsonUtils {
    public static <T> T toBean(Class<T> type, String body) {
        Gson gson = new Gson();
        try {
            T obj = (T) gson.fromJson(body, type);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object type) {
        Gson gson = new Gson();
        try {
            return gson.toJson(type);
        } catch (Exception e) {
            LogUtils.e(e + "对象转json出错");
        }
        return null;
    }

    /**
     * 上传图片带缩率图封装json
     *
     * @param object       json Object
     * @param jsonarray    json数组
     * @param originalUrl  原图url
     * @param thumbnailUrl 缩略图url
     * @return 返回是否操作成功
     */
    public static boolean partJson(JSONObject object, JSONArray jsonarray, String originalUrl, String thumbnailUrl) {
        try {
            JSONObject jsonObj = new JSONObject();//对象，json形式
            jsonObj.put("originalUrl", originalUrl);//向对象里面添加值
            jsonObj.put("thumbnailUrl", thumbnailUrl);
            // 把每个数据当作一对象添加到数组里
            jsonarray.put(jsonObj);//向json数组里面添加对象
            object.put("body", jsonarray);//向总对象里面添加包含对象的数组
        } catch (JSONException e) {
            return false;
        }
        return true;
    }
}
