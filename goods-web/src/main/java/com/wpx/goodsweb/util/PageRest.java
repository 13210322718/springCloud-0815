package com.wpx.goodsweb.util;

import java.util.List;

public class PageRest<T> {
    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

    public PageRest() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> objects) {
        this.data = objects;
    }

    @Override
    public String toString() {
        return "PageRest{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }
}
