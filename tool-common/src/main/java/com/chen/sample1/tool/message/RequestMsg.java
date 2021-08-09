package com.chen.sample1.tool.message;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;

/**
 * 分页请求消息对象
 * @author ChenTian
 */
public class RequestMsg implements Serializable {
    public static final int DEFAULT_PAGE = 1;
    public static final int DEFAULT_SIZE = 10;
    public static final String DEFAULT_SORT = "create_time desc";

    /**
     *  查询页数，从1开始
     */
    private int pageNum = DEFAULT_PAGE;

    /**
     * 每页最大数量
     */
    private int pageSize = DEFAULT_SIZE;


    /** 查询请求参数{"key1":"value1","key2":"value2"} */
     private JSONObject params;

    public RequestMsg() {
    }

    public RequestMsg(Integer pageNumber, Integer pageSize) {
        if(pageNumber != null && pageNumber >= 0) {
            this.pageNum = pageNumber;
        }
        if(pageSize != null && pageSize >= 0) {
            this.pageSize = pageSize;
        }
    }

    public RequestMsg(Object params, Integer pageNumber, Integer pageSize) {
        this(pageNumber,pageSize);
        if(params==null){
            return;
        }
        this.params = JSONUtil.parseObj(params);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        if(pageSize==0){
            return DEFAULT_SIZE;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public JSONObject getParams() {
        return params;
    }

    public void setParams(JSONObject params) {
        this.params = params;
    }
}
