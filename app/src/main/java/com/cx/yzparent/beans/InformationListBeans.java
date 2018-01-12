package com.cx.yzparent.beans;

import com.cx.yzparent.utils.response.ResponseData;

import java.util.List;

/**
 * Created by cj on 2017/11/10
 * 热门资讯列表
 */
public class InformationListBeans extends ResponseData {
    public DataBean data;

    public static class DataBean {
        public int total;
        public List<ListBean> rows;

        public static class ListBean {
            public int id;
            public String created;
            public String updated;
            public String jmId;
            public String name;
            public String coverImage;
            public String synopsis;
            public String author;
            public Object url;
            public String content;
            public int source;
            public Object remark;
            public String startTime;
            public String showUrl;
        }
    }
}
