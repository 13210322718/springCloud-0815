package com.wpx.goodsserver.server;

import com.wpx.goodsserver.domain.GoodsInfo;
import com.wpx.goodsserver.util.PageRest;
import org.springframework.data.domain.Page;

public interface GoodsInfoServer {
    PageRest<GoodsInfo> getAll(Integer start, Integer end) throws Exception;

    GoodsInfo getOneById(String no) throws Exception;

    boolean addGoods(GoodsInfo goodsInfo);
}
