package com.wpx.goodsserver.server;

import com.wpx.goodsserver.domain.CommentsInfo;

import java.util.List;

public interface CommentsInfoServer {

    List<CommentsInfo> findAllByGoodsNo(String no)throws Exception;

    void addComments(CommentsInfo commentsInfo)throws Exception;

    

}
