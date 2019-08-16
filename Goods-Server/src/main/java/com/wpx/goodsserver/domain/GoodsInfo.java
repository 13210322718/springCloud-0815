package com.wpx.goodsserver.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "goodsinfo")
public class GoodsInfo {
    @Id
    private String goodsNo;//商品编号
    private String goodsName;
    private double goodsPrice;//商品价格
    private String goodsDesc;//商品描述
    private String goodsPiceture;//该产品的缩略图
    private Integer brandNo;//品牌编号

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "goodsNo")
    private Set<CommentsInfo> commentsInfos = new HashSet<>();//该商品所有的评论

    public GoodsInfo() {
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public Set<CommentsInfo> getCommentsInfos() {
        return commentsInfos;
    }

    public void setCommentsInfos(Set<CommentsInfo> commentsInfos) {
        this.commentsInfos = commentsInfos;
    }

    public Integer getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(Integer brandNo) {
        this.brandNo = brandNo;
    }

    public String getGoodsPiceture() {
        return goodsPiceture;
    }

    public void setGoodsPiceture(String goodsPiceture) {
        this.goodsPiceture = goodsPiceture;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public void addComments(CommentsInfo commentsInfo) {
        this.commentsInfos.add(commentsInfo);
    }
}
