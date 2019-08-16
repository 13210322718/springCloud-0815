package com.wpx.goodsserver.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wpx.goodsserver.domain.CommentsInfo;
import com.wpx.goodsserver.domain.GoodsInfo;
import com.wpx.goodsserver.server.GoodsInfoServer;
import com.wpx.goodsserver.util.PageRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/goods")
public class GoodsInfoController {
    @Autowired
    GoodsInfoServer goodsInfoServer;

    //获取所有的商品列表
    @RequestMapping("/all/{start}")
    public PageRest<GoodsInfo> getAll(@PathVariable(name = "start") Integer start) {
        PageRest<GoodsInfo> all = new PageRest<>();
        try {
            //将所有的页面信息传递给页面
            all = goodsInfoServer.getAll(start, 9);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return all;
        }
    }

    //获取详情
    @RequestMapping("/one/{no}")
    public PageRest<GoodsInfo> findOne(@PathVariable(name = "no") String no) {
        GoodsInfo oneById = new GoodsInfo();
        //生成一个页面信息
        PageRest<GoodsInfo> goodsInfoPageRest = new PageRest<>();
        try {
            //获取一个商品
            oneById = goodsInfoServer.getOneById(no);
            System.out.println(oneById);
            //添加商品休息
            goodsInfoPageRest.addDate(oneById);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return goodsInfoPageRest;
        }
    }

    @RequestMapping("/add/{values}")
    public PageRest<String> addGoods(@PathVariable(name = "values") String values) {
        //转换
        ObjectMapper objectMapper = new ObjectMapper();
        PageRest<String> pageRest = new PageRest();
        try {
            //将传来的数据进行反序列化
            GoodsInfo goodsInfo = objectMapper.readValue(values, GoodsInfo.class);
            goodsInfoServer.addGoods(goodsInfo);
            pageRest.setCode(200);
        } catch (IOException e) {
            e.printStackTrace();
            pageRest.setCode(400);
        } finally {
            return pageRest;
        }
    }
}
