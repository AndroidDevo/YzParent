package com.cx.yzparent.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cx.yzparent.R;

/**
 * Created by cj on 2017/5/18.
 * 封装图片加载
 */
public class ImageLoadUtils {
    private static ImageLoadUtils imageLoadUtils = null;

    private ImageLoadUtils() {

    }

    public static ImageLoadUtils getInstance() {
        if (imageLoadUtils == null) {
            imageLoadUtils = new ImageLoadUtils();
        }
        return imageLoadUtils;
    }

    /**
     * 优先加载
     */
    public void loadImage(Context context, String imageUrl, ImageView view, boolean isHighPriority) {
        if (context == null || view == null) {
            return;
        }
        try {
            Glide.with(context).load(imageUrl).priority(Priority.HIGH).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        } catch (Exception e) {
            LogUtils.e("优先加载图片出错:"+e);
        }
    }

    /**
     *普通加载
     */
    public void loadImage(Context context, String imageUrl, ImageView view) {
        if (context == null || view == null) {
            return;
        }
        try {
            Glide.with(context).load(imageUrl).placeholder(R.mipmap.temp_head).error(R.mipmap.temp_head).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        } catch (Exception e) {
            LogUtils.e("普通加载图片出错:"+e);
        }
    }

    public void loadHeadImage(Context context, String imageUrl, ImageView view) {
        if (context == null || view == null) {
            return;
        }
        try {
            Glide.with(context).load(imageUrl).priority(Priority.HIGH).error(R.mipmap.temp_head).diskCacheStrategy(DiskCacheStrategy.ALL).into(view);
        } catch (Exception e) {
            LogUtils.e("加载头像出错:"+e);
        }
    }
}
