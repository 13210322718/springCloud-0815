package com.wpx.goodsweb.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CommentsInfo {
    private Integer commentsNo;//评论编号
    private String userNo;//用户编号
    private Date bornDate;//评论日期
    private String commentsContext;
    private String goodsNo;

    public CommentsInfo() {
    }

    public Integer getCommentsNo() {
        return commentsNo;
    }

    public void setCommentsNo(Integer commentsNo) {
        this.commentsNo = commentsNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }


    public String getCommentsContext() {
        return commentsContext;
    }

    public void setCommentsContext(String commentsContext) {
        this.commentsContext = commentsContext;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }
}
