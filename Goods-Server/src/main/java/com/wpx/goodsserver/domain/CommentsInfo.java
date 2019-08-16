package com.wpx.goodsserver.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "commentsinfo")
public class CommentsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentsNo;//评论编号
    private String userNo;//用户编号
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    private Date bornDate;//评论日期
    private String commentsContext;
    private String goodsNo;
//    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
//    @JoinColumn(name = "goodsNo")
//    private GoodsInfo goodsInfo;

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
    //    public GoodsInfo getGoodsInfo() {
//        return goodsInfo;
//    }
//
//    public void setGoodsInfo(GoodsInfo goodsInfo) {
//        this.goodsInfo = goodsInfo;
//    }
}
