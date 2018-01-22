package com.cx.yzparent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cx.yzparent.R;
import com.cx.yzparent.beans.InformationListBeans;
import com.cx.yzparent.utils.ImageLoadUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cj on 2018/1/22
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
    private Context mContext;
    private List<InformationListBeans.DataBean.ListBean> mList;

    public RecycleAdapter(Context context,List<InformationListBeans.DataBean.ListBean> listBean) {
        this.mContext = context;
        this.mList = listBean;
    }

    public void update(List<InformationListBeans.DataBean.ListBean> listBean) {
        this.mList = listBean;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //初始化viewHolder
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据
        InformationListBeans.DataBean.ListBean item = mList.get(position);
        if (item != null) {
            String title = item.name;
            String content = item.synopsis;
            String infoDate = item.startTime;
            String coverImage = item.coverImage;
            ImageLoadUtils.getInstance().loadImage(mContext, coverImage, holder.mInfoImg);
            holder.mInfoTitle.setText(title);
            holder.mInfoContent.setText(content);
            holder.mInfoDate.setText(infoDate);
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.info_img)
        ImageView mInfoImg;
        @BindView(R.id.info_title)
        TextView mInfoTitle;
        @BindView(R.id.info_content)
        TextView mInfoContent;
        @BindView(R.id.info_date)
        TextView mInfoDate;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
